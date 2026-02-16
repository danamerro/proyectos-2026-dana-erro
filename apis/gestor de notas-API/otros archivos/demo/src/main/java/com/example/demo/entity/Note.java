package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 255,message = "tiene que tener un minimo de 1 y máximo de 255 caracteres")
    private String title;
    @JsonProperty
    @NotBlank
    @Size(min = 1, max = 1000,message = "tiene que tener un minimo de 1 y máximo de 1000 caracteres")
    private String content;
    @JsonProperty
    private LocalDate createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
