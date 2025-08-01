package org.com.dungeonpoc.book.service;

import lombok.RequiredArgsConstructor;
import org.com.dungeonpoc.book.domain.Book;
import org.com.dungeonpoc.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> create(Book book) {
        return bookRepository.save(book);
    }

    public Mono<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
            .flatMap(existing -> {
                existing.setTitle(book.getTitle());
                existing.setAuthor(book.getAuthor());
                return bookRepository.save(existing);
            });
    }

    public Mono<Void> delete(Long id) {
        return bookRepository.deleteById(id);
    }

}
