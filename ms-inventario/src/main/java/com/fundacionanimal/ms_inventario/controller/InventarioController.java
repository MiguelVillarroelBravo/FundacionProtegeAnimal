package com.fundacionanimal.ms_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.ms_inventario.model.Inventario;
import com.fundacionanimal.ms_inventario.service.InventarioService;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController 
{
private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // GET http://localhost:8082/api/inventario
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInsumos() {
        return ResponseEntity.ok(inventarioService.obtenerTodo());
    }

    // POST http://localhost:8082/api/inventario
    @PostMapping
    public ResponseEntity<Inventario> crearInsumo(@RequestBody Inventario insumo) {
        Inventario nuevoInsumo = inventarioService.registrarInsumo(insumo);
        return new ResponseEntity<>(nuevoInsumo, HttpStatus.CREATED);
    }

    // PUT http://localhost:8082/api/inventario/1/merma?cantidad=5.0
    @PutMapping("/{id}/merma")
    public ResponseEntity<Inventario> aplicarMerma(@PathVariable Long id, @RequestParam Double cantidad) {
        Inventario insumoActualizado = inventarioService.registrarMerma(id, cantidad);
        if (insumoActualizado != null) {
            return ResponseEntity.ok(insumoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
