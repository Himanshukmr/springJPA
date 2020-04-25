package com.in30minutes.springJPA.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in30minutes.springJPA.entity.Person;

@Repository
public class PersonJdbcDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirth_date(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}
	
	//Select * from person
	// Method : To find all Person Object from DataBase
	public List<Person> findAll()
	{
		
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
	}
	// Method : To find a Person Object on basis of id from DataBase
	public Person findById(int id)
	{
		
		return (Person) jdbcTemplate.queryForObject("select * from person where id =?",new Object[] {id},
				new BeanPropertyRowMapper(Person.class));
	}
	
	// Method : To find a Person Object on basis of name from DataBase
	public Person findByName(String name)
	{
		
		return (Person) jdbcTemplate.queryForObject("select * from person where name =?",new Object[] {name},
				new BeanPropertyRowMapper(Person.class));
	}

	// Method : To delete a Person Object on basis of id and name to DataBase
	public int deleteById(int id,String name)
	{
		
		return  jdbcTemplate.update("Delete from person where id =? AND name=?",new Object[] {id,name});
	}
	
	// Method : To Insert new Person Object to DataBase
	public int insertPerson(Person person)
	{
		
		return  jdbcTemplate.update("insert into person (id, name, location, birth_date ) values(? , ? , ? , ?)",
				new Object[] {person.getId(),person.getName(),person.getLocation(),new Timestamp(person.getBirth_date().getTime())});
	}
	
	public int updatePerson(int id,String name)
	{
		
		return  jdbcTemplate.update("update person set name = ? where id = ?",
				new Object[] {name,id});
	}
	
	
	//Example to show use of Custom RowMapper
	//Select * from person
	// Method : To find all Person Object from DataBase
	public List<Person> findAllPerson()
	{
		
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}
}
