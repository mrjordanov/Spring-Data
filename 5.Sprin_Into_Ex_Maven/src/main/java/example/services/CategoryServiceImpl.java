package example.services;

import example.models.Category;
import example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = this.categoryRepository.count();
        Random random = new Random();
        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categories = new HashSet<>();

        for (int i = 0; i < size; i++) {
            int nextId = random.nextInt((int) size) + 1;
            categories.add(nextId);
        }

        List<Category> allById = categoryRepository.findAllById(categories);

        return new HashSet<>(allById);
    }
}
