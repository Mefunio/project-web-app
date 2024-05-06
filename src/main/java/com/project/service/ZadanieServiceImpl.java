package com.project.service;


import com.project.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService{


    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        return Optional.empty();
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        return null;
    }

    @Override
    public void deleteZadanie(Integer zadanieId) {

    }

    @Override
    public Page<Zadanie> getZadania(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Zadanie> searchByProjektId(Integer projektId, Pageable pageable) {
        return null;
    }
}
