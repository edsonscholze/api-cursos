package br.com.scholze.cursocrudspring.controller;

import br.com.scholze.cursocrudspring.infra.CourseRepository;
import br.com.scholze.cursocrudspring.model.Course;
import br.com.scholze.cursocrudspring.model.DataListCourses;
import br.com.scholze.cursocrudspring.model.DataRegistrationCourse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;
    @GetMapping
    public ResponseEntity<List<DataListCourses>> listar(){
        var courses = courseRepository.findAll().stream().map(DataListCourses::new).toList();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRegistrationCourse> obterPorId(@PathVariable @NotNull @Positive Long id){
        //return courseRepository.findById(id).map(record -> ResponseEntity.ok().body(new DataRegistrationCourse(record.getName(), record.getCategory()))).orElse(ResponseEntity.notFound().build());

        var course = courseRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataRegistrationCourse(course.getId(), course.getName(), course.getCategory()));
    }
    @PostMapping
    @Transactional
    public ResponseEntity salvar(@Valid @RequestBody DataRegistrationCourse data){
        var course = new Course(data);
        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@Valid @RequestBody DataRegistrationCourse courseAtualizado){
        var course = courseRepository.getReferenceById(courseAtualizado._id());
        course.setCategory(courseAtualizado.category());
        course.setName(courseAtualizado.name());
        return ResponseEntity.ok(courseAtualizado);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLogica(@PathVariable @NotNull @Positive Long id){
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("excluir/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable @NotNull @Positive Long id){
        var course = courseRepository.getReferenceById(id);
        courseRepository.delete(course);
        return ResponseEntity.noContent().build();
    }

}
