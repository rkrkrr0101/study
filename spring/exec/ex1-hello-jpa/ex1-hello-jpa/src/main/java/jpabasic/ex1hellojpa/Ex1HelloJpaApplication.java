package jpabasic.ex1hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex1HelloJpaApplication {

	public static void main(String[] args) {



		SpringApplication.run(Ex1HelloJpaApplication.class, args);
	}

}
