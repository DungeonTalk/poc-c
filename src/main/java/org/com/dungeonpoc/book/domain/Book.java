package org.com.dungeonpoc.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
@Getter
@Setter
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;

    // Constructors, Getters, Setters
    public Book() {}
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

}
