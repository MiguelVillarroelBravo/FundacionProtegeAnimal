package com.fundacionanimal.ms_apadrinamientos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.ms_apadrinamientos.model.Apadrinamiento;

@Repository
public interface ApadrinamientoRepository extends JpaRepository<Apadrinamiento, Long> {

	List<Apadrinamiento> findByFkRutPadrinoAndEstado(String fkRutPadrino, String estado);
}
