package com.intuit.editor.repository;

import com.intuit.editor.entity.ContentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRespository extends MongoRepository<ContentEntity,String> {
    @Query(value = "{}", fields = "{ 'dTitle' : 1, 'id' : 1}")
    List<ContentEntity> findAllTitlesAndIds();
}
