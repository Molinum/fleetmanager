package com.example.fleetmanager.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleetmanager.model.Vehiculo;
import com.example.fleetmanager.service.VehiculoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehiculoController {

    private final VehiculoService vehiculoService;

    // Listar todos los vehiculos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {
        log.info("GET /api/vehiculos - Listar los vehiculos");
        List<Vehiculo> list = vehiculoService.getVehiculos();
        return ResponseEntity.ok(list);
    }

    // Crear un nuevo vehiculo
    @PostMapping
    public ResponseEntity<Vehiculo> agregarVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        log.info("POST /api/vehiculos - Crear vehiculo: {}", vehiculo.getPatente());
        Vehiculo nuevoVehiculo = vehiculoService.add(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
    }

    // Buscar un vehiculo por ID
    @GetMapping("{id}")
    public ResponseEntity<Vehiculo> buscarVehiculo(@PathVariable int id) {
        log.info("GET /api/vehiculos - Buscar vehiculo por ID: {}", id);
        Vehiculo vehiculo = vehiculoService.getVehiculoId(id);
        return ResponseEntity.ok(vehiculo);
    }

    // Actualizar un vehiculo existente
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(
            @PathVariable int id,
            @RequestBody Vehiculo vehiculo) {

        log.info("PUT /api/vehiculos/{} - Actualizando datos", id);
        Vehiculo vehiculoActualizado = vehiculoService.updateVehiculo(vehiculo);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    // Eliminar un vehiculo por ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable int id) {
        if (vehiculoService.getVehiculoId(id) == null) {
            return new ResponseEntity<>("Vehiculo no encontrado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(vehiculoService.deleteVehiculo(id), HttpStatus.OK);
        }
    }

    // Ordenar los vehiculos por capacidad de carga de mayor a menor
    @GetMapping("/ordenar")
    public List<Vehiculo> ordenarPorCarga() {
        return vehiculoService.ordenarPorCarga();
    }

}
