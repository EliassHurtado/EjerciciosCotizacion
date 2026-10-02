package mx.edu.utez.EjerciciosCotizacion.service;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.EjerciciosCotizacion.exception.customException.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HospedajeService {

    public Map<String, Object> calcularHospedaje(RequestHospedajeDTO dto) {
        if (dto.getNumeroNoches() > 30) {
            throw new BadRequestException("El número de noches no puede superar los 30 días.");
        }

        String tipoUpper = dto.getTipoHabitacion().toUpperCase();
        double costoPorNoche;
        int capacidadMaxima;

        switch (tipoUpper) {
            case "INDIVIDUAL":
                costoPorNoche = 700.0;
                capacidadMaxima = 1;
                break;
            case "DOBLE":
                costoPorNoche = 1100.0;
                capacidadMaxima = 2;
                break;
            case "SUITE":
                costoPorNoche = 1800.0;
                capacidadMaxima = 4;
                break;
            default:
                throw new BadRequestException("Tipo de habitación no permitido. Usar: INDIVIDUAL, DOBLE o SUITE.");
        }

        if (dto.getNumeroHuespedes() > capacidadMaxima) {
            throw new BadRequestException("El número de huéspedes (" + dto.getNumeroHuespedes() +
                    ") supera la capacidad máxima para la habitación " + tipoUpper + " (" + capacidadMaxima + " persona/s).");
        }

        double costoHospedajeBase = costoPorNoche * dto.getNumeroNoches();

        String temporadaUpper = dto.getTemporada().toUpperCase();
        double ajusteTemporada = 0.0;

        switch (temporadaUpper) {
            case "BAJA":
                ajusteTemporada = -(costoHospedajeBase * 0.10); // Descuento del 10%
                break;
            case "REGULAR":
                ajusteTemporada = 0.0;
                break;
            case "ALTA":
                ajusteTemporada = costoHospedajeBase * 0.25; // Cargo adicional del 25%
                break;
            default:
                throw new BadRequestException("Temporada no válida. Usar: BAJA, REGULAR o ALTA.");
        }
        double descuentoLargaEstancia = 0.0;
        if (dto.getNumeroNoches() >= 7) {
            descuentoLargaEstancia = costoHospedajeBase * 0.10;
        }
        double costoDesayuno = dto.getIncluyeDesayuno() ? (dto.getNumeroHuespedes() * dto.getNumeroNoches() * 150.0) : 0.0;

        double costoEstacionamiento = dto.getIncluyeEstacionamiento() ? (dto.getNumeroNoches() * 100.0) : 0.0;

        double subtotal = (costoHospedajeBase + ajusteTemporada - descuentoLargaEstancia) + costoDesayuno + costoEstacionamiento;

        double impuestoHospedaje = subtotal * 0.04;

        double costoTotal = subtotal + impuestoHospedaje;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombreHuesped", dto.getNombreHuesped());
        respuesta.put("tipoHabitacion", tipoUpper);
        respuesta.put("numeroNoches", dto.getNumeroNoches());
        respuesta.put("numeroHuespedes", dto.getNumeroHuespedes());
        respuesta.put("temporada", temporadaUpper);
        respuesta.put("costoHospedajeBase", costoHospedajeBase);
        respuesta.put("ajusteTemporada", ajusteTemporada);
        respuesta.put("descuentoLargaEstancia", descuentoLargaEstancia);
        respuesta.put("costoDesayuno", costoDesayuno);
        respuesta.put("costoEstacionamiento", costoEstacionamiento);
        respuesta.put("subtotal", subtotal);
        respuesta.put("impuestoHospedaje", Math.round(impuestoHospedaje * 100.0) / 100.0);
        respuesta.put("costoTotal", Math.round(costoTotal * 100.0) / 100.0);

        return respuesta;
    }
}