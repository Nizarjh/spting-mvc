package az.niarjh.springcource.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import az.niarjh.springcource.models.Person;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> showAllPeople() {
        return jdbcTemplate.query("SELECT * FROM person",
                new PersonMapper());
    }

    public Person showPerson(UUID id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?",
                new PersonMapper(),
                id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name,age) VALUES(?,?)",
                person.getName(),
                person.getAge());
    }

    public void update(Person updatedPerson, UUID id) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}