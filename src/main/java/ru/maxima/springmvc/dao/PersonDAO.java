package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.*;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age)  values( ?, ?)", person.getName(),
                person.getAge());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update person set name = ?, age = ? where id = ?", updatedPerson.getName(),
                updatedPerson.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }


//    public void testWithoutBatch() {
//        long start = System.currentTimeMillis();
//
//        List<Person> people = create1000Persons();
//        for (Person person : people) {
//            jdbcTemplate.update("insert into person values (?, ?, ?, ?)", person.getId(), person.getName(),
//                    person.getAge(), person.getEmail());
//        }
//        long end = System.currentTimeMillis();
//
//        System.out.println("Without BatchUpdate - " + (end - start));
//    }
//
//
//    public void testWithBatch() {
//        long start = System.currentTimeMillis();
//        List<Person> people = create1000Persons();
//
//        jdbcTemplate.batchUpdate("insert into person values (?, ?, ?, ?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                preparedStatement.setInt(1, people.get(i).getId());
//                preparedStatement.setString(2, people.get(i).getName());
//                preparedStatement.setInt(3, people.get(i).getAge());
//                preparedStatement.setString(4, people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        }) ;
//        long end = System.currentTimeMillis();
//
//        System.out.println("With BatchUpdate - " + (end - start));
//    }
//
//    public List<Person> create1000Persons() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, "Name" + i, i, "test" + i + "@mail.ru"));
//        }
//        return people;
//    }

}
