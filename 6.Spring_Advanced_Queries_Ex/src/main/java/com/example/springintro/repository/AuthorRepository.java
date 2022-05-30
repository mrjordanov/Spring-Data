package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAllByFirstNameEndingWith(String firstName);

    @Query("SELECT a.firstName as firstName, a.lastName as lastName, sum(d.copies) as totalCopies from Author a join a.books as d" +
            " GROUP BY a.id" +
            " order by totalCopies DESC ")
    List<AuthorNamesWithTotalCopies> findAllBySumCopies();
}
