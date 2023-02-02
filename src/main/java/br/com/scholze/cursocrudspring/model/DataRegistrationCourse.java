package br.com.scholze.cursocrudspring.model;

import jakarta.validation.constraints.NotBlank;

public record DataRegistrationCourse(

        Long _id,
        @NotBlank
        String name,
        @NotBlank
        String category) {
}
