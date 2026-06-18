package com.fundacionanimal.ms_inventario.service;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.fundacionanimal.ms_inventario.model.Inventario;
import com.fundacionanimal.ms_inventario.repository.InventarioRepository;

@Service
public class InventarioService 
{
    private final InventarioRepository inventarioRepository;

    // Inyección de dependencias por constructor (Estilo idéntico a tu proyecto)
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> obtenerTodo() {
        return inventarioRepository.findAll();
    }

    public Inventario registrarInsumo(Inventario insumo) {
        return inventarioRepository.save(insumo);
    }

    // Lógica para descontar stock por merma o uso (E4-HU-04 del Excel)
    public Inventario registrarMerma(Long id, Double cantidadMerma) {
        Optional<Inventario> opcional = inventarioRepository.findById(id);
        if (opcional.isPresent()) {
            Inventario insumo = opcional.get();
            double nuevoStock = insumo.getStockActual() - cantidadMerma;
            
            // Validación para que el stock no sea menor a cero
            if (nuevoStock < 0) {
                nuevoStock = 0.0;
            }
            
            insumo.setStockActual(nuevoStock);
            return inventarioRepository.save(insumo);
        }
        return null;
    }




    public Inventario obtenerPorId(Long idInsumo) {

        if (idInsumo == null) {
            return null;
        }
    
        return inventarioRepository.findById(idInsumo).orElse(null);
    }


    public Inventario actualizarInsumo(Long idInsumo, Inventario datosActualizados) {

        Optional<Inventario> opcional = inventarioRepository.findById(idInsumo);
    
        if (opcional.isPresent()) {
    
            Inventario insumo = opcional.get();
    
            insumo.setNombre(datosActualizados.getNombre());
            insumo.setTipoInsumo(datosActualizados.getTipoInsumo());
            insumo.setStockActual(datosActualizados.getStockActual());
            insumo.setStockMinimo(datosActualizados.getStockMinimo());
           
            insumo.setUnidadMedida(datosActualizados.getUnidadMedida());
    
            return inventarioRepository.save(insumo);
        }
    
        return null;

    }

    public boolean eliminarInsumo(Long idInsumo) {

        Optional<Inventario> opcional = inventarioRepository.findById(idInsumo);
    
        if (opcional.isPresent()) {
            inventarioRepository.deleteById(idInsumo);
            return true;
        }
    
        return false;
    }



    
}
