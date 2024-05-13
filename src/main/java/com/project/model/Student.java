package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private Integer studentId;

    @NotNull(message = "Pole nie może być puste!")
    private String imie;

    @NotNull(message = "Pole nie może być puste!")
    private String nazwisko;
    private String nrIndeksu;
    private String email;
    private boolean stacjonarny;

    @JsonIgnoreProperties({"student"})
    private List<Projekt> projekty;
    private List<Zadanie> zadania;
}