package com.example.backend.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
