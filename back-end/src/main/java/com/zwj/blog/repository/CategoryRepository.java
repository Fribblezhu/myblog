package com.zwj.blog.repository;

import com.zwj.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String>{

}
