package com.fundacionanimal.ms_alertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.ms_alertas.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

	List<Alerta> findByLeidaFalse();
}
