package mx.edu.utez.EjerciciosCotizacion.service;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestRentaVehiculoDTO;
import mx.edu.utez.EjerciciosCotizacion.exception.customException.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RentaVehiculoService {

    public Map<String, Object> calcularRenta(RequestRentaVehiculoDTO dto) {
        if (dto.getEdadConductor() < 18) {
            throw new BadRequestException("El conductor debe ser mayor de edad (mínimo 18 años).");
        }
        if (dto.getDiasRenta() > 30) {
            throw new BadRequestException("La renta no puede superar los 30 días.");
        }
        if (dto.getKilometrosEstimados() > 5000) {
            throw new BadRequestException("Los kilómetros estimados no pueden superar los 5,000 km.");
        }

        String tipoUpper = dto.getTipoVehiculo().toUpperCase();
        if (tipoUpper.equals("CAMIONETA") && dto.getEdadConductor() < 25) {
            throw new BadRequestException("Para rentar una CAMIONETA el conductor debe tener al menos 25 años.");
        }

        double costoDiario;
        switch (tipoUpper) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
            default:
                throw new BadRequestException("Tipo de vehículo no permitido. Usar: COMPACTO, SEDAN, SUV o CAMIONETA.");
        }

        double costoRenta = costoDiario * dto.getDiasRenta();

        int kilometrosIncluidos = dto.getDiasRenta() * 100;
        int kilometrosAdicionales = Math.max(0, dto.getKilometrosEstimados() - kilometrosIncluidos);
        double cargoKmAdicionales = kilometrosAdicionales * 4.0;

        double cargoEdad = 0.0;
        if (dto.getEdadConductor() >= 18 && dto.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKmAdicionales) * 0.15;
        }

        double costoSeguro = dto.getSeguroCompleto() ? (180.0 * dto.getDiasRenta()) : 0.0;

        double descuentoRenta = 0.0;
        if (dto.getDiasRenta() >= 7) {
            descuentoRenta = costoRenta * 0.10;
        }

        double costoTotal = (costoRenta - descuentoRenta) + cargoKmAdicionales + cargoEdad + costoSeguro;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreCliente", dto.getNombreCliente());
        respuesta.put("tipoVehiculo", tipoUpper);
        respuesta.put("diasRenta", dto.getDiasRenta());
        respuesta.put("costoRentaBase", costoRenta);
        respuesta.put("descuentoRenta", descuentoRenta);
        respuesta.put("kilometrosIncluidos", kilometrosIncluidos);
        respuesta.put("kilometrosAdicionales", kilometrosAdicionales);
        respuesta.put("cargoKmAdicionales", cargoKmAdicionales);
        respuesta.put("cargoEdad", cargoEdad);
        respuesta.put("costoSeguro", costoSeguro);
        respuesta.put("costoTotal", Math.round(costoTotal * 100.0) / 100.0);

        return respuesta;
    }
}