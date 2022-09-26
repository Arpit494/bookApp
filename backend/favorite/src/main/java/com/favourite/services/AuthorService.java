package com.favourite.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.favourite.model.Author;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorService {

    public Author getAllAuthor() {
        String url = "https://openlibrary.org/search/authors.json?q=j%40k%40rowling";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Author authors = null ;
        RestTemplate restTemplate = new RestTemplate();
        try{
            String authorSearch = restTemplate.getForObject(url, String.class);
            authors = mapper.readValue(authorSearch, Author.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return authors;

    }
}
