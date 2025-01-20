package com.secure.notes.models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Note {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Lob
    private String content;

    private String ownerUsername;
}
