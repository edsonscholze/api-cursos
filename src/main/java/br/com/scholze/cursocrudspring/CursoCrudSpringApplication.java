package br.com.scholze.cursocrudspring;

import br.com.scholze.cursocrudspring.infra.CourseRepository;
import br.com.scholze.cursocrudspring.model.Course;
import br.com.scholze.cursocrudspring.model.DataRegistrationCourse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CursoCrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoCrudSpringApplication.class, args);
	}

	//@Bean
	CommandLineRunner cadastrar(CourseRepository courseRepository){
		return args -> {
			//DataRegistrationCourse da = new DataRegistrationCourse("Spring", "Back-end");
			//courseRepository.save(new Course(da));
			System.out.println("Iniciou");
		};
	}
}
