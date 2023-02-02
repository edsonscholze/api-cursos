package br.com.scholze.cursocrudspring.service;

import br.com.scholze.cursocrudspring.infra.CourseRepository;
import br.com.scholze.cursocrudspring.model.Course;
import br.com.scholze.cursocrudspring.model.DataListCourses;
import br.com.scholze.cursocrudspring.model.DataRegistrationCourse;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<DataListCourses> listar() {
        return courseRepository.findAll().stream().map(DataListCourses::new).toList();
    }

    public DataRegistrationCourse obterPorId(Long id) {
        var course = courseRepository.getReferenceById(id);
        return new DataRegistrationCourse(course.getId(), course.getName(), course.getCategory());
    }

    public boolean salvar(DataRegistrationCourse data) {
        var course = new Course(data);
        courseRepository.save(course);
        return course.getId() != null;
    }

    @Transactional
    public DataRegistrationCourse atualizar(DataRegistrationCourse courseUpdated){
        var course = courseRepository.getReferenceById(courseUpdated._id());
        course.setCategory(courseUpdated.category());
        course.setName(courseUpdated.name());
        return courseUpdated;
    }

    @Transactional
    public boolean exclusaoLogica(Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean excluir(Long id){
        Course course = courseRepository.getReferenceById(id);
        if(course.getName().isEmpty()) return false;
        courseRepository.delete(course);
        return true;
    }
}
