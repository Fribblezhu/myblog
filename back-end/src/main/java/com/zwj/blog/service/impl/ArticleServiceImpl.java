package com.zwj.blog.service.impl;

import com.zwj.blog.domain.Article;
import com.zwj.blog.repository.ArticleRepository;
import com.zwj.blog.service.ArticleSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleSerivice {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Long countByAuthorName(String authorName) {
        return this.articleRepository.countByAuthorName(authorName);
    }
}
