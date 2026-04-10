package az.niarjh.springcource.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import az.niarjh.springcource.models.Book;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> showAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book", Book.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Book showBook(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Book.class, id);
    }

    @Transactional
    public List<Book> showBooksByPerson(UUID ownerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                "from Book b where b.owner.id = :ownerId", Book.class)
                .setParameter("ownerId", ownerId)
                .list();
    }

    @Transactional
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void updateBook(Book updatedBook, UUID id) {
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.find(Book.class, id);
        if (bookToBeUpdated == null) {
            throw new RuntimeException("Person not found");
        }
        bookToBeUpdated.setOwner(updatedBook.getOwner());
        bookToBeUpdated.setTitle(updatedBook.getTitle());
        bookToBeUpdated.setPublishDate(updatedBook.getPublishDate());
        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
    }

    @Transactional
    public void deleteBook(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.find(Book.class, id));
    }

    @Transactional
    public void freeBook(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.find(Book.class, id);
        bookToBeUpdated.setOwner(null);
    }

}