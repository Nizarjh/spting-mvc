package az.niarjh.springcource.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import az.niarjh.springcource.models.Person;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> showAllPeople() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class)
                .getResultList();

    }

    @Transactional(readOnly = true)
    public Person showPerson(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(Person updatedPerson, UUID id) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.find(Person.class, id);
    if (personToBeUpdated == null) {
        throw new RuntimeException("Person not found");
    }

    personToBeUpdated.setName(updatedPerson.getName());
    personToBeUpdated.setAge(updatedPerson.getAge());
    personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(UUID id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.find(Person.class, id));
    }
}
