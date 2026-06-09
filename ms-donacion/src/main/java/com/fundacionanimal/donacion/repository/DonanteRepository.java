package com.fundacionanimal.donacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.donacion.model.Donante;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, String> {
}
