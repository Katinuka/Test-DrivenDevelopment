/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Book {
    private String id;
    private String name;
    private String author;
    private String code;
    private String description;

    public Book(String name, String author, String code, String description) {
        this.name = name;
        this.author = author;
        this.code = code;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}