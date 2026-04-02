package com.example.fleetmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleetmanager.exception.model.ResourceNotFoundException;
import com.example.fleetmanager.model.Vehiculo;
import com.example.fleetmanager.repository.VehiculoRepository;

@Service
public class VehiculoService {

    private final VehiculoRepository VehiculoRepository = new VehiculoRepository();

    public List<Vehiculo> getVehiculos() {
        return VehiculoRepository.getVehiculos();
    }

    public Vehiculo add(Vehiculo vehiculo) {
        return VehiculoRepository.agregarVehiculo(vehiculo);
    }

    public Vehiculo getVehiculoId(int id) {
        return VehiculoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehiculo no encontrado con ID: " + id));
    }

    public Vehiculo updateVehiculo(Vehiculo Vehiculo) {
        return VehiculoRepository.actualizarVehiculo(Vehiculo);
    }

    public String deleteVehiculo(int id) {
        VehiculoRepository.eliminarVehiculo(id);
        return "vehículo eliminado";
    }

    public List<Vehiculo> ordenarPorCarga() {
        return VehiculoRepository.ordenarPorCarga();
    }

}