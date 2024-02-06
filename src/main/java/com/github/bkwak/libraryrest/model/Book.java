package com.github.bkwak.libraryrest.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
}
