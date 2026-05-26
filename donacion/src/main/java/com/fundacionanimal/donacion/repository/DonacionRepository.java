package com.fundacionanimal.donacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.donacion.model.Donaciones;

@Repository
public interface DonacionRepository extends JpaRepository<Donaciones, Long>{

}
