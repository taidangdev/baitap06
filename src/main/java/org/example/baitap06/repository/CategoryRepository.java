package org.example.baitap06.repository;

import org.example.baitap06.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Phương thức tìm kiếm Category theo tên hoặc mô tả chứa keyword (phân trang)
    Page<Category> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
