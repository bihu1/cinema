package com.bihu.kino.movie.model;

import com.bihu.kino.seance.model.Seance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {

    @Id
    private long id;

    @ElementCollection
    List<String> movieTypes;

    @OneToMany(cascade = CascadeType.ALL)
    List<Seance> seances;

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
