package com.eazybytes.eazyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eazybytes.eazyschool.repository") //It tells Spring where to scan for repository interfaces so that it can generate their implementations at runtime.
@EntityScan("com.eazybytes.eazyschool.model") //@EntityScan is a Spring Boot annotation used to specify the package(s) where JPA entity classes are located
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") //This annotation is used to enable JPA Auditing feature in a Spring Boot application. and we also tell spring that what is the bean name of the
//AuditAwareImpl class
public class EazyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyschoolApplication.class, args);

	}

}
