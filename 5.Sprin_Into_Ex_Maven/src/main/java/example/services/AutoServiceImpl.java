package example.services;

import example.models.Author;
import example.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AutoServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;


    @Autowired
    public AutoServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();
        Random random = new Random();
        int authorId = random.nextInt((int) size) + 1;

        return authorRepository.findById(authorId).get();
    }
}
