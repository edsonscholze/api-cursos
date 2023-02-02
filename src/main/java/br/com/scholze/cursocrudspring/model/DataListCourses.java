package br.com.scholze.cursocrudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataListCourses(
        Long _id, String name, String category) {
    public DataListCourses(Course course){
        this(course.getId(), course.getName(), course.getCategory());
    }
}
