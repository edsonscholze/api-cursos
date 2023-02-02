package br.com.scholze.cursocrudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity(name = "Course")
@Table(name="courses")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@SQLDelete(sql = "Update courses set ativo = false where id = ?")
@Where(clause = "ativo = true")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min=5, max=100)
    private String name;

    @NotBlank
    @Length(max=10)
    @Pattern(regexp = "Front-end|Back-end")
    private String category;
    private boolean ativo = true;

    public Course(DataRegistrationCourse data){
        this.name = data.name();
        this.category = data.category();
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
