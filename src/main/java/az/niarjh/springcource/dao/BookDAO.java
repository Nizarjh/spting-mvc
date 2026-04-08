package az.niarjh.springcource.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import az.niarjh.springcource.models.Book;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookDAO {


    public List<Book> showAllBooks() {

    }

    public Book showBook(UUID id) {

    }

    public List<Book> showBooksByPerson(UUID ownerId) {

    }

    public void saveBook(Book book) {

    }

    public void updateBook(Book updatedbook, UUID id) {

    }

    public void deleteBook(UUID id) {

    }

    public void freeBook(UUID id) {

    }

}