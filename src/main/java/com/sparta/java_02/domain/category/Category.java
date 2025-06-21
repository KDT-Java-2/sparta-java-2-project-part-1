package com.sparta.java_02.domain.category;

import com.sparta.java_02.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @Column(nullable = false)
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Category(String name, String description, Category parent, Integer displayOrder) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.displayOrder = displayOrder != null ? displayOrder : 0;
        
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void updateInfo(String name, String description, Integer displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder != null ? displayOrder : this.displayOrder;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void addChild(Category child) {
        this.children.add(child);
    }

    public void removeChild(Category child) {
        this.children.remove(child);
        child.parent = null;
    }

    public boolean isRootCategory() {
        return this.parent == null;
    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    public int getDepth() {
        if (isRootCategory()) {
            return 0;
        }
        return parent.getDepth() + 1;
    }

    public String getFullPath() {
        if (isRootCategory()) {
            return this.name;
        }
        return parent.getFullPath() + " > " + this.name;
    }
} 