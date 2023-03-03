package com.revature.caseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.revature.caseStudy")
@EnableJpaRepositories("com.revature.caseStudy.repositories")
@EntityScan("com.revature.caseStudy")
public class CaseStudyBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyBackEndApplication.class, args);
	}

}
