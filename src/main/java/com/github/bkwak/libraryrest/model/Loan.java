package com.github.bkwak.libraryrest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
}
