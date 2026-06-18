package com.fundacionanimal.ms_voluntariado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.ms_voluntariado.model.InscripcionVoluntario;

@Repository
public interface InscripcionVoluntarioRepository extends JpaRepository<InscripcionVoluntario, Long> {
}
