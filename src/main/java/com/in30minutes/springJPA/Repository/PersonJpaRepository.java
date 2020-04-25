package com.in30minutes.springJPA.Repository;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in30minutes.springJPA.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {

	// connect to the database
	@PersistenceContext
	EntityManager entityManager;


	public List<Person> findAll() {
		// JPA
		//Here we are using NamedQuery to create query(using JPQL) now implement it in Person class 
		TypedQuery<Person> namedQuery =  entityManager.createNamedQuery("find_all_perons", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);// JPA
	}
	
	//Basically update and insert method both uses merge() of entity manager
	//merge will insert the object if no primary key is matched where as if
	//primary key is matched then it will acts as update on that primary key of the obj.
	
	
	public Person update(Person person) {
		return entityManager.merge(person);// JPA
	}
	
	public Person insert(Person person) {
		return entityManager.merge(person);// JPA
	}
	
	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);// JPA
	}


}