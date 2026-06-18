package com.fundacionanimal.ms_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundacionanimal.ms_inventario.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long>{

}
