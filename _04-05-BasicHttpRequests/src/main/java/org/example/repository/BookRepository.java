/**
 * This code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example.repository;

import org.example.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}