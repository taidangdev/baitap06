package org.example.baitap06.service;

import org.example.baitap06.entity.Category;
import org.example.baitap06.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Page<Category> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Category> search(String keyword, Pageable pageable) {
        return repo.findByNameContainingOrDescriptionContaining(keyword, keyword, pageable);
    }

    public Category get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
    }

    public Category save(Category c) { return repo.save(c); }

    public void delete(Long id) { repo.deleteById(id); }
}
