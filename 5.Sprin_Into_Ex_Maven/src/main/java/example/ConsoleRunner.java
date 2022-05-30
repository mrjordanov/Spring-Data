package example;

import example.models.Author;
import example.models.Book;
import example.repositories.AuthorRepository;
import example.repositories.BookRepository;
import example.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void booksAfter2000() {
         /*  bookRepository.findAll().stream()
                    .filter(b->b.getReleaseDate().isAfter(LocalDate.of(2000,1,1)))
                    .forEach(b-> System.out.println(b.getTitle()+" "+b.getReleaseDate()));*/
        LocalDate yearAfter = LocalDate.of(2000, 1, 1);
        List<Book> books = bookRepository.findByReleaseDateAfter(yearAfter);
        books.forEach(b -> System.out.println(b.getTitle() + " " + b.getReleaseDate()));

        int count = bookRepository.countBookByReleaseDateAfter(yearAfter);
        System.out.println("Total count: " + count);
    }

    private void authorsWithAtLeastOneBookBefore1990() {
        LocalDate year = LocalDate.of(1990, 1, 1);
        List<Author> authors = authorRepository.findDistinctByBookReleaseDateBefore(year);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

    }

    private void getAllAuthorsAndHisBooks() {
        List<Author> author = authorRepository.findAll();

        author.stream()
                .sorted((s1, s2) -> s2.getBook().size() - s1.getBook().size())
                .forEach(a -> System.out.printf("%s %s with books count: %d",
                        a.getFirstName(), a.getLastName(), a.getBook().size()).println());
    }

    @Override
    public void run(String... args) throws Exception {
        // this.seedService.seedAuthors();
        // this.seedService.seedCategories();
        // this.seedService.seedAll();
        //booksAfter2000();
        // authorsWithAtLeastOneBookBefore1990();
        getAllAuthorsAndHisBooks();

    }
}
