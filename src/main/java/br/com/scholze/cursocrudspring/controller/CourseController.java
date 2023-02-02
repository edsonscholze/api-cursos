package br.com.scholze.cursocrudspring.controller;

import br.com.scholze.cursocrudspring.model.DataListCourses;
import br.com.scholze.cursocrudspring.model.DataRegistrationCourse;
import br.com.scholze.cursocrudspring.service.CourseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;
    @GetMapping
    public ResponseEntity<List<DataListCourses>> listar(){
        var courses = courseService.listar();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataRegistrationCourse> obterPorId(@PathVariable @NotNull @Positive Long id){
        //return courseRepository.findById(id).map(record -> ResponseEntity.ok().body(new DataRegistrationCourse(record.getName(), record.getCategory()))).orElse(ResponseEntity.notFound().build());
        var course = courseService.obterPorId(id);
        return ResponseEntity.ok(course);
    }
    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody DataRegistrationCourse data){
        if(!courseService.salvar(data)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity atualizar(@Valid @RequestBody DataRegistrationCourse courseUpdated){
        var course = courseService.atualizar(courseUpdated);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLogica(@PathVariable @NotNull @Positive Long id){
        if(!courseService.exclusaoLogica(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable @NotNull @Positive Long id){
        if(!courseService.excluir(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
