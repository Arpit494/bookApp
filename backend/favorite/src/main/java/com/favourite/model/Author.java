package com.favourite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {

    private String numFound;
    private String start;
    private List<Docs> docs;

}
