package com.in30minutes.springJPA;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.in30minutes.springJPA.Repository.PersonJpaRepository;
import com.in30minutes.springJPA.entity.Person;

@SpringBootApplication
public class DemoOfSpringJpaApplication  implements CommandLineRunner{
	

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJpaRepository personJpaRepository;

	public static void main(String[] args) {
		
		SpringApplication.run(DemoOfSpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("Person with id 1001 user --> {}",personJpaRepository.findById(10001));
	
	}

}
