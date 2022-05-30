package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookTitleTypeRestrictionPrice;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> titlesWithGivenAgeRestriction(String ageRestriction);

    List<String> printTitlesOfGoldenEditionBooks(EditionType type, int copies);

    Set<Book> printBookTitleAndPriceInGivenRanger(BigDecimal valueOf, BigDecimal valueOf1);

    List<String> getBooksNotReleasedInGivenYear(int i);

    List<Book> BooksReleasedBefore(String s);

    List<String> printTitleOfBookWitchContainsGivenString(String input);

    List<String> printTitlesByGivenLastNameAuthorString(String lastName);

    int printCountOfBooksWithLongerTitleFromInput(int number);

    List<BookTitleTypeRestrictionPrice> getInformationForTitle(String things_fall_apart);

    int increaseBookCopiesAfterGivenDateWithGivenNumOfCopies(String date, int number);

    int countOfRemovedBooksWithLessCopiesThanInput(int copies);
}
