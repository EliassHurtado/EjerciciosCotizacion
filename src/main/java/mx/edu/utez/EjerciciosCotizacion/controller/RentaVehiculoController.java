package mx.edu.utez.EjerciciosCotizacion.controller;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestRentaVehiculoDTO;
import mx.edu.utez.EjerciciosCotizacion.service.RentaVehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/my-services")
public class RentaVehiculoController {

    private final RentaVehiculoService rentaVehiculoService;

    public RentaVehiculoController(RentaVehiculoService rentaVehiculoService) {
        this.rentaVehiculoService = rentaVehiculoService;
    }

    @PostMapping("/renta-vehiculos")
    public ResponseEntity<Map<String, Object>> cotizarRenta(@Valid @RequestBody RequestRentaVehiculoDTO request) {
        Map<String, Object> resultado = rentaVehiculoService.calcularRenta(request);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}