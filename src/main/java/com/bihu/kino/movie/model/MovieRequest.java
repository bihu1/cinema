package com.bihu.kino.movie.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    List<String> movieTypes;
    String title;
    String description;
    String image;
    String trailer;
    String cast;
    int minutes;
    int price;
    String director;
    String country;
}
