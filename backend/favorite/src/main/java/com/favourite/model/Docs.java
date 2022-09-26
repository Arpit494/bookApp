package com.favourite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Docs {

    private String key;
    private String name;
    private String birth_date;
    private String death_date;
    private String top_work;

}
