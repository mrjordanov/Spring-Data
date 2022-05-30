package example.services;

import example.models.*;
import example.repositories.BookRepository;
import example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.repositories.AuthorRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;



@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "src/main/resources/files";
    private static final String AUTHOR_FILE_PATH = RESOURCE_PATH + "/authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "/categories.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "/books.txt";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHOR_FILE_PATH)).stream()
                .map(s -> s.split(" "))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH)).stream()
                .filter(s -> !s.isBlank()).map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH)).stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);
    }




    private Book getBookObject(String line) {
        String[] array = line.split(" ");

        int index = Integer.parseInt(array[0]);
        EditionType editionType = EditionType.values()[index];

        LocalDate releaseDate = LocalDate.parse(array[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int numberOfCopies = Integer.parseInt(array[2]);

        BigDecimal price = new BigDecimal(array[3]);

        int restrictionIndex = Integer.parseInt(array[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[restrictionIndex];

        String title = Arrays.stream(array).skip(5).collect(Collectors.joining(" "));

       Author author= authorService.getRandomAuthor();
       Set<Category> categories=categoryService.getRandomCategories();



        return new Book(title,editionType,price,numberOfCopies,releaseDate,ageRestriction,author,categories);
    }


}
