package com.zwj.blog.service.impl;

import com.zwj.blog.repository.TagRepository;
import com.zwj.blog.service.TagSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagSerivice {

    @Autowired
    private TagRepository tagRepository;
    @Override
    public Long CountByAuthorName(String authorName){
        return this.tagRepository.countByAuthorName(authorName);
    }
}
