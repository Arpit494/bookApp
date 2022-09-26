package com.favourite.controller;
import com.favourite.model.*;
import com.favourite.services.AuthorService;
import com.favourite.services.BookDetailsService;
import com.favourite.services.BookRecommendationService;
import com.favourite.services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class AllFeaturesController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookDetailsService bookDetailsService;
    @Autowired
    private FavouriteService favouriteService;
    @Autowired
    private BookRecommendationService bookRecommendService;

    @GetMapping("/books/arts")
    public BookDetailsDto getAllArtsBooks(){
        return bookDetailsService.getAllArtsBooks();
    }

    @GetMapping("/books/programming")
    public BookDetailsDto getAllProgrammingBooks(){
        return bookDetailsService.getAllProgrammingBooks();
    }

    @GetMapping("/books/history")
    public BookDetailsDto getAllHistoryBooks(){
        return bookDetailsService.getAllHistoryBooks();
    }

    @GetMapping("/books/education")
    public BookDetailsDto getAllEducationBooks(){
        return bookDetailsService.getAllEducationBooks();
    }

    @GetMapping("/author")
    public Author getAllAuthors(){
        return authorService.getAllAuthor();
    }

    @PostMapping("/favourite")
    public Favourite addFavourite(@RequestBody() Favourite fav) {
        return favouriteService.addFavourite(fav);
    }

    @GetMapping("/favourite/{userName}")
    public List<String> getAllFav(@PathVariable("userName") String userName){
        return favouriteService.getAllFav(userName);
    }

    @DeleteMapping("/favourite/{userName}/{url}*")
    public List<String> deleteFav(@PathVariable("userName") String userName , @PathVariable String url){
        return favouriteService.deleteFav(userName,url);
    }

    @RequestMapping({ "/getBooks/{searchString}" })
    public BookList getBooks(@PathVariable String searchString) {
        return bookDetailsService.getBooks(searchString);
    }


    @PostMapping("/recommendation")
    public BookRecommendation addUserSearchKey(@RequestBody() BookRecommendation bookRecommend) {
        return bookRecommendService.addUserSearchKey(bookRecommend);
    }

    @GetMapping("/recommendation/{userName}")
    public List<String> getRecommendedKeys(@PathVariable("userName") String userName){
        return bookRecommendService.getRecommendedKeys(userName);
    }

}
