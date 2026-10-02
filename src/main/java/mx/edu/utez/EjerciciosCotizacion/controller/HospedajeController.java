package mx.edu.utez.EjerciciosCotizacion.controller;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.EjerciciosCotizacion.service.HospedajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/my-services")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/hospedaje")
    public ResponseEntity<Map<String, Object>> cotizarHospedaje(@Valid @RequestBody RequestHospedajeDTO request) {
        Map<String, Object> resultado = hospedajeService.calcularHospedaje(request);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}