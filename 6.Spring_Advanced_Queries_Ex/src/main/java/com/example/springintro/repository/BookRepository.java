package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookTitleTypeRestrictionPrice;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    Set<Book>findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price, BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate releaseDate, LocalDate releaseDate2);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContaining(String title);

    List<Book> findAllByAuthorLastNameStartsWith(String author_lastName);

    @Query("SELECT count(b.id) from Book b where length(b.title)>:param")
    int countBookByTitleLengthGreaterThan(int param);

    @Query("SELECT b.title as title,b.editionType as editionType, b.ageRestriction as ageRestriction, b.price as price" +
            " from Book b " +
            "where b.title=:param")
    List<BookTitleTypeRestrictionPrice> getBookByTitleAndAgeRestrictionAndPrice(String param);

    @Modifying
    @Transactional
    @Query("UPDATE Book as b set b.copies=b.copies+ :increase where b.releaseDate> :releaseDate")
    int countBookByReleaseDateAfter(int increase,LocalDate releaseDate);

    @Modifying
    @Transactional
    @Query("DELETE from Book where copies<:copies")
    int countOfDeletedBooksWithCopiesLessThan(int copies);


}
