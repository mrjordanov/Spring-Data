package example_for_enums;

public enum AccountType {
    FREE("FREE"), TRAIL("TRAIL"), SILVER("SILVER"), GOLD("GOLD");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
