package example.repositories;

import example.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByReleaseDateAfter(LocalDate releaseDate);
    int  countBookByReleaseDateAfter(LocalDate releaseDate);
}
