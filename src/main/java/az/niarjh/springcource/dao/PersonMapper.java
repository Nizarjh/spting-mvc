package az.niarjh.springcource.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import az.niarjh.springcource.models.Person;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public  Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getObject("id", UUID.class));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        return person;
    }

}
