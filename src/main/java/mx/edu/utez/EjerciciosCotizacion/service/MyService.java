package mx.edu.utez.EjerciciosCotizacion.service;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestCotizadorDTO;
import mx.edu.utez.EjerciciosCotizacion.exception.customException.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyService {

    public Map<String, Object> calcularCotizacion(RequestCotizadorDTO dto) {
        double volumen = dto.getLargoCm() * dto.getAnchoCm() * dto.getAltoCm();

        if (dto.getPesoKg() > 50) {
            throw new BadRequestException("El paquete no puede pesar más de 50 kg.");
        }
        if (dto.getLargoCm() > 150 || dto.getAnchoCm() > 150 || dto.getAltoCm() > 150) {
            throw new BadRequestException("Ninguna dimensión puede ser superior a 150 cm.");
        }
        if (volumen > 1000000) {
            throw new BadRequestException("El volumen no puede superar 1,000,000 cm³.");
        }

        double costoBase = 80.0;
        double costoPeso = dto.getPesoKg() * 12.0;
        double recargoVolumen = (volumen > 50000) ? 100.0 : 0.0;

        double acumulado = costoBase + costoPeso + recargoVolumen;

        double recargoTipoEnvio = 0.0;
        String tipoEnvioUpper = dto.getTipoEnvio().toUpperCase();

        switch (tipoEnvioUpper) {
            case "EXPRESS":
                recargoTipoEnvio = acumulado * 0.40;
                break;
            case "MISMO_DIA":
                recargoTipoEnvio = acumulado * 0.70;
                break;
            case "ESTANDAR":
                recargoTipoEnvio = 0.0;
                break;
            default:
                throw new BadRequestException("Tipo de envío no permitido. Usar: ESTANDAR, EXPRESS o MISMO_DIA.");
        }

        double acumuladoConTipoEnvio = acumulado + recargoTipoEnvio;

        double seguro = (dto.getValorDeclarado() > 10000) ? (dto.getValorDeclarado() * 0.02) : 0.0;

        double costoTotal = acumuladoConTipoEnvio + seguro;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("codigoPostal", dto.getCodigoPostal());
        respuesta.put("costoBase", costoBase);
        respuesta.put("costoPeso", costoPeso);
        respuesta.put("recargoVolumen", recargoVolumen);
        respuesta.put("recargoTipoEnvio", recargoTipoEnvio);
        respuesta.put("costoSeguro", seguro);
        respuesta.put("costoTotal", Math.round(costoTotal * 100.0) / 100.0);

        return respuesta;
    }
}