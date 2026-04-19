package az.niarjh.springcource.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import az.niarjh.springcource.models.Book;
import az.niarjh.springcource.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private BookRepository bookRepository;

    @Transactional
    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    public Book showBook(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + id));
    }

    @Transactional
    public List<Book> showBooksByPersonId(UUID ownerId) {
        return bookRepository.showBooksByPersonId(ownerId);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Book updatedBook, UUID id) {
        Book bookToBeUpdated = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        bookToBeUpdated.setOwner(updatedBook.getOwner());
        bookToBeUpdated.setTitle(updatedBook.getTitle());
        bookToBeUpdated.setPublishDate(updatedBook.getPublishDate());
        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
    }

    @Transactional
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void freeBookById(UUID id) {
        bookRepository.freeBookById(id);
    }
}
