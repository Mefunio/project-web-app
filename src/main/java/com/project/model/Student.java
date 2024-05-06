package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class Student {


    private Integer studentId;
    private String imie;
    private String nazwisko;
    private String nrIndeksu;
    private String email;
    private boolean stacjonarny;
    private Set<Projekt> projekty;

    public Student() {}
    public Student(String imie, String nazwisko, String nrIndeksu, Boolean stacjonarny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
    }
    public Student(String imie, String nazwisko, String nrIndeksu, String email, Boolean stacjonarny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.email = email;
        this.stacjonarny = stacjonarny;
    }


//...
}