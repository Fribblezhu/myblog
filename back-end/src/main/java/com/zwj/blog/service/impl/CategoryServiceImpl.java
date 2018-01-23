package com.zwj.blog.service.impl;

import com.zwj.blog.repository.CategoryRepository;
import com.zwj.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


}
