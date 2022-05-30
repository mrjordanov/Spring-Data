package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
       /*  seedData();
         01_Ex
        String restriction="MinOr";
          bookService.titlesWithGivenAgeRestriction(restriction).forEach(System.out::println);
        02
        bookService.printTitlesOfGoldenEditionBooks(EditionType.GOLD,5000).forEach(System.out::println);
        03
        bookService.printBookTitleAndPriceInGivenRanger(new BigDecimal(5),new BigDecimal(40))
              .forEach(b-> System.out.printf("%s - $%.2f",b.getTitle(),b.getPrice()).println());
        04
        bookService.getBooksNotReleasedInGivenYear(2000).forEach(System.out::println);
        05
        bookService.BooksReleasedBefore("12-04-1992")
                .forEach(b-> System.out.printf("%s %s %.2f"
                ,b.getTitle(),b.getEditionType(),b.getPrice()).println());
        06
        authorService.getAuthorsWithGivenFirstLetter("dy").forEach(System.out::println);
        07
        bookService.printTitleOfBookWitchContainsGivenString("WOR").forEach(System.out::println);
        08
        bookService.printTitlesByGivenLastNameAuthorString("gr").forEach(System.out::println);
        09
        System.out.println(bookService.printCountOfBooksWithLongerTitleFromInput(40));
        10!!! with added Interface with properties for the desiredColumns!!!
        authorService.getAllAuthorsNumberOfBookCopies()
                .forEach(a-> System.out.printf("%s %s - %d",a.getFirstName(),a.getLastName(),a.getTotalCopies()).println());
        11 with interface
        bookService.getInformationForTitle("Things Fall Apart").forEach(b-> System.out.printf("%s %s %s %.2f",
                b.getTitle(),b.getEditionType(),b.getAgeRestriction(),b.getPrice()).println());
        12
        System.out.println(bookService.increaseBookCopiesAfterGivenDateWithGivenNumOfCopies("12 Oct 2005", 100));

        13
        System.out.println(bookService.countOfRemovedBooksWithLessCopiesThanInput(10000));*/

        bookService.BooksReleasedBefore("12-04-1992")
                .forEach(b-> System.out.printf("%s %s %.2f"
                        ,b.getTitle(),b.getEditionType(),b.getPrice()).println());
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
