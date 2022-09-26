package com.favourite.services;

import com.favourite.model.BookRecommendation;
import com.favourite.repository.BookRecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookRecommendationService {
    @Autowired
    private BookRecommendRepository bookRecommendRepository;

    //Add search Keys
    public BookRecommendation addUserSearchKey(BookRecommendation bookRecommend) {
        String un=bookRecommend.getUserEmail();
        if(bookRecommendRepository.existsById(un)) {
            BookRecommendation br=bookRecommendRepository.findById(un).get();
            List<String> sk=br.getWorkUrl();
            sk.add(bookRecommend.getWorkUrl().get(0));
            //remove duplicity
            Set<String> set= new HashSet<>(sk);
            //set toList
            List<String> list=set.stream().collect(Collectors.toList());
            bookRecommend.setWorkUrl(list);
            return bookRecommendRepository.save(bookRecommend);
        }
        else {
            return bookRecommendRepository.save(bookRecommend);
        }
    }

    //get recommended keys
    public List<String> getRecommendedKeys(String userName){
        if(bookRecommendRepository.existsById(userName)) {
            BookRecommendation br=bookRecommendRepository.findById(userName).get();
            List<String> sk=br.getWorkUrl();
            return sk;
        }else {
            return null;
        }
    }
}
