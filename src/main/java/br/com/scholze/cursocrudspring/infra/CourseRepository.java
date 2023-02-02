package br.com.scholze.cursocrudspring.infra;

import br.com.scholze.cursocrudspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
