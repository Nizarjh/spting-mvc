package az.niarjh.springcource.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import az.niarjh.springcource.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Book b set b.owner = null where b.id = :id")
    void freeBookById(@Param("id") UUID id);

    @Query("select b from Book b where b.owner.id = :ownerid")
    List<Book> showBooksByPersonId(@Param("ownerid") UUID ownerid);

}
