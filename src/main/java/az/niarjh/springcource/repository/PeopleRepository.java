package az.niarjh.springcource.repository;

import org.springframework.stereotype.Repository;
import az.niarjh.springcource.models.Person;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, UUID> {

}
