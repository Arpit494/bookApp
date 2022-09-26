package com.favourite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Docs1 {

    private String key;
    private String title;
    private String first_publish_year;
    private String type;
    private List<String> edition_key;
    private List<String> author_name;
    private List<String> publisher;
}
