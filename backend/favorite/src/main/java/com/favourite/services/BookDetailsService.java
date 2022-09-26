package com.favourite.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.favourite.model.BookDetailsDto;
import com.favourite.model.BookList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class BookDetailsService {
    public BookDetailsDto getAllArtsBooks() {
        return getBookDetails("http://openlibrary.org/subjects/arts.json?limit=10");
    }

    public BookDetailsDto getAllProgrammingBooks(){
        return getBookDetails("http://openlibrary.org/subjects/programming.json?limit=10");
    }

    public BookDetailsDto getAllHistoryBooks(){
        return getBookDetails("http://openlibrary.org/subjects/history.json?limit=10");
    }

    public BookDetailsDto getAllEducationBooks(){
        return getBookDetails("http://openlibrary.org/subjects/education.json?limit=10");
    }

    public BookDetailsDto getBookDetails(String bookDetailsUrl) {
        String url = bookDetailsUrl;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BookDetailsDto bookDetailsDto = null ;
        RestTemplate restTemplate = new RestTemplate();
        try{
            String bookSearch = restTemplate.getForObject(url, String.class);
            bookDetailsDto = mapper.readValue(bookSearch, BookDetailsDto.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bookDetailsDto;
    }

    public BookList getBooks(String searchString) {
        String url="https://openlibrary.org/search.json?q="+searchString;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BookList books = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            String bookSearch = restTemplate.getForObject(url, String.class);
            books = mapper.readValue(bookSearch, BookList.class);
        } catch (RestClientException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return books;
    }
}
