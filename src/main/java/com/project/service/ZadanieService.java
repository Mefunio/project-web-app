package com.project.service;


import com.project.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ZadanieService {
    Optional<Zadanie> getZadanie(Integer zadanieId);
    void setZadanie(Zadanie zadanie);
    void deleteZadanie(Integer zadanieId);
    Page<Zadanie> getZadania(Pageable pageable);
    Page<Zadanie> searchByProjektId(Integer projektId, Pageable pageable);
}
