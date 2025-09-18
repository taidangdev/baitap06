package org.example.baitap06.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Category", schema = "dbo") // khớp đúng tên bảng & schema
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Long userId; // để đơn giản ta giữ FK dạng Long

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(length = 50)
    private String icon;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }


    // getters/setters...
    // === GETTERS / SETTERS ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }       // <-- KHÔNG để rỗng nữa

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
