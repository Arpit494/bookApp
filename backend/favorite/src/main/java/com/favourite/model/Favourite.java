package com.favourite.model;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="favourite")
public class Favourite {

    @Id
    private String userEmail;
    private List<String> workUrl;
}
