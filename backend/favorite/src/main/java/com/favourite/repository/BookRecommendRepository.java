package com.favourite.repository;

import com.favourite.model.BookRecommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRecommendRepository extends MongoRepository<BookRecommendation,String> {

}
