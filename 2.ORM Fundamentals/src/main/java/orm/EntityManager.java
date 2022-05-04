package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public void doCreate(Class<E> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);

        String createQuery = String.format("CREATE TABLE %s (" + "id INT PRIMARY KEY AUTO_INCREMENT, %s)", tableName, fieldsWithTypes);

        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.execute();
    }

    public void doAlter(Class<E> entity) throws SQLException {

        String tableName = getTableName(entity);
        String addColumnsStatement = getAddColumnStatementsForNewFields(entity);

        String alterQuery = String.format("ALTER TABLE %s %s", tableName, addColumnsStatement);

        PreparedStatement statement = connection.prepareStatement(alterQuery);
        statement.execute();
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idColumn = getIdColumn(entity.getClass());
        Object idValue = getFieldValue(idColumn, entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity);
        }
        return doUpdate(entity, (long) idValue);

    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return baseFind(table, where, null);
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> result = baseFind(table, where, "LIMIT 1");

        return result.get(0);
    }

    @Override
    public boolean delete(E toDelete) throws IllegalAccessException, SQLException {
        String tableName = getTableName(toDelete.getClass());

        Field idColumn = getIdColumn(toDelete.getClass());
        String idColumName = getSQLColumnName(idColumn.getAnnotationsByType(Column.class));
        Object idColumnValue = getFieldValue(idColumn, toDelete);

        String query = String.format("DELETE FROM %s WHERE %s = %s", tableName, idColumName, idColumnValue);
        return connection.prepareStatement(query).execute();
    }

    private boolean doInsert(E entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass());
        List<String> tableValues = getColumnsValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, String.join(",", tableFields), String.join("", tableValues));
        return connection.prepareStatement(insertQuery).execute();
    }

    private boolean doUpdate(E entity, long id) throws SQLException, IllegalAccessException {

        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass());
        List<String> tableValues = getColumnsValuesWithoutId(entity);

        List<String> setStatements = new ArrayList<>();
        for (int i = 0; i < tableFields.size(); i++) {
            String statement = tableFields.get(i) + "=" + tableValues.get(i);
            setStatements.add(statement);
        }

        String query = String.format("UPDATE %s SET %s WHERE id=%d", tableName, String.join(",", setStatements), id);

        return connection.prepareStatement(query).execute();
    }

    private List<E> baseFind(Class<E> table, String where, String limit) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String tableName = getTableName(table);

        String query = String.format("SELECT * FROM %s %s %s", tableName, where != null ? "WHERE " + where : "", limit != null ? limit : "");

        List<E> finish = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            E result = table.getDeclaredConstructor().newInstance();
            fillEntity(table, resultSet, result);

            finish.add(result);
        }

        return finish;
    }

    private void fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] fields = table.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            fillField(field, resultSet, entity);
        }

    }

    private void fillField(Field field, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Class<?> type = field.getType();
        String fieldName = getSQLColumnName(field.getAnnotationsByType(Column.class));

        if (type == int.class || type == Integer.class) {
            int value = resultSet.getInt(fieldName);
            field.set(entity, value);
        } else if (type == LocalDate.class) {
            LocalDate value = LocalDate.parse(resultSet.getString(fieldName));
            field.set(entity, value);
        } else if (type == long.class || type == Long.class) {
            long value = resultSet.getLong(fieldName);
            field.set(entity, value);
        } else {
            String value = resultSet.getString(fieldName);
            field.set(entity, value);
        }
    }

    private Field getIdColumn(Class<?> clazz) {

        return Arrays.stream(clazz.getDeclaredFields()).filter(x -> x.isAnnotationPresent(Id.class)).findFirst().orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    private List<String> getColumnsValuesWithoutId(E entity) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();
        List<Field> fields = getEntityColumnFieldsWithoutId(clazz.getDeclaredFields());

        List<String> values = new ArrayList<>();

        for (Field field : fields) {
            Object o = getFieldValue(field, entity);
            if (o instanceof String || o instanceof LocalDate) {
                values.add("'" + o + "'");
            } else {
                values.add(o.toString());
            }
        }

        return values;
    }

    private Object getFieldValue(Field idColumn, E entity) throws IllegalAccessException {
        idColumn.setAccessible(true);

        return idColumn.get(entity);
    }

    private List<Field> getEntityColumnFieldsWithoutId(Field[] clazz) {
        return Arrays.stream(clazz).filter(f -> !f.isAnnotationPresent(Id.class)).filter(f -> f.isAnnotationPresent(Column.class)).toList();
    }

    private List<String> getColumnsWithoutId(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name()).collect(Collectors.toList());
    }

    private String getTableName(Class<?> clazz) {
        Entity[] annotationsByType = clazz.getAnnotationsByType(Entity.class);

        if (annotationsByType.length == 0) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationsByType[0].name();

    }

    private String getSQLFieldsWithTypes(Class<E> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields()).filter(f -> !f.isAnnotationPresent(Id.class)).filter(f -> f.isAnnotationPresent(Column.class)).map(field -> {
            String fieldName = getSQLColumnName(field.getAnnotationsByType(Column.class));

            String sqlType = getSqlType(field.getType());

            return fieldName + " " + sqlType;
        }).collect(Collectors.joining(","));

    }

    private String getAddColumnStatementsForNewFields(Class<E> entity) throws SQLException {
        Set<String> sqlColumns = getSQLColumnNames(entity);

        List<Field> fields = getEntityColumnFieldsWithoutId(entity.getDeclaredFields());

        List<String> allAddStatements = new ArrayList<>();

        for (Field field : fields) {
            String fieldName = getSQLColumnName(field.getAnnotationsByType(Column.class));

            if (sqlColumns.contains(fieldName)) {
                continue;
            }

            String sqlType = getSqlType(field.getType());

            String addStatement = String.format("ADD COLUMN %s %s", fieldName, sqlType);
            allAddStatements.add(addStatement);
        }

        return String.join(",", allAddStatements);
    }

    private Set<String> getSQLColumnNames(Class<E> entity) throws SQLException {
        Set<String> result = new LinkedHashSet<>();

        String schemaQuery = "SELECT COLUMN_NAME FROM information_schema.COLUMNS c\n" + "WHERE c.TABLE_SCHEMA='custom-orm' AND c.COLUMN_NAME!='id' AND  c.TABLE_NAME='users';";

        PreparedStatement statement = connection.prepareStatement(schemaQuery);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            result.add(columnName);
        }

        return result;
    }

    private String getSqlType(Class<?> type) {
        String sqlType = "UNKNOWN";
        if (type == Integer.class || type == int.class) {
            sqlType = "INT";
        } else if (type == String.class) {
            sqlType = "VARCHAR(200)";
        } else if (type == LocalDate.class) {
            sqlType = "DATE";
        }
        return sqlType;
    }

    private String getSQLColumnName(Column[] idColumn) {
        return idColumn[0].name();
    }

}
