package com.example.fleetmanager.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.fleetmanager.model.Vehiculo;

@Repository
public class VehiculoRepository {
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoRepository() {
        vehiculos.add(new Vehiculo(1, "Toyota", "Hilux", "LD-ZW-95", "Nuevo", "Camioneta", 1500,
                java.sql.Date.valueOf("2022-01-15")));
        vehiculos.add(new Vehiculo(2, "Ford", "F-150", "TH-GL-21", "Nuevo", "Camioneta", 1200,
                java.sql.Date.valueOf("2021-12-10")));
        vehiculos.add(new Vehiculo(3, "Mercedes-Benz", "Actros", "LD-ZW-95", "Semi-Nuevo", "Camión", 5000,
                java.sql.Date.valueOf("2020-05-20")));
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }

    public Optional<Vehiculo> buscarPorId(int id) {
        return vehiculos.stream().filter(v -> v.getId() == id).findFirst();
    }

    public Vehiculo agregarVehiculo(Vehiculo nuevoVehiculo) {

        if (buscarPorId(nuevoVehiculo.getId()).isPresent()) {
            throw new IllegalArgumentException("El ID del vehículo ya existe.");
        } else {

            vehiculos.add(nuevoVehiculo);
            return nuevoVehiculo;
        }
    }

    public Vehiculo actualizarVehiculo(Vehiculo vehiculoActualizado) {
        Optional<Vehiculo> vehiculoExistente = buscarPorId(vehiculoActualizado.getId());
        if (vehiculoExistente.isPresent()) {
            eliminarVehiculo(vehiculoActualizado.getId());
            agregarVehiculo(vehiculoActualizado);
        } else {
            throw new IllegalArgumentException("El vehículo con ID " + vehiculoActualizado.getId() + " no existe.");
        }
        return vehiculoActualizado;
    }

    public void eliminarVehiculo(int id) {
        vehiculos.removeIf(v -> v.getId() == id);
    }

    public int totalVehiculos() {
        return vehiculos.size();
    }

    public List<Vehiculo> ordenarPorCarga() {
        List<Vehiculo> vehiculosOrdenados = new ArrayList<>(vehiculos);
        vehiculosOrdenados.sort((v1, v2) -> Integer.compare(v2.getCarga(), v1.getCarga()));
        return vehiculosOrdenados;
    }
}
