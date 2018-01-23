package com.zwj.blog.repository;

import com.zwj.blog.domain.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<ArticleTag,String>{

    Long countByAuthorName(String authorName);
}
