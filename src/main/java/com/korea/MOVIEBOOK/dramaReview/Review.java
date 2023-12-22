package com.korea.MOVIEBOOK.dramaReview;

import com.korea.MOVIEBOOK.Drama.Drama;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String comment;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "drama_id")
    private Drama drama;
}
