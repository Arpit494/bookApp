package com.favourite.repository;

import com.favourite.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavouriteRepository extends MongoRepository<Favourite,String> {
}
