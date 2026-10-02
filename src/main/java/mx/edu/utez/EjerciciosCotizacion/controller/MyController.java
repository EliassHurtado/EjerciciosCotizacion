package mx.edu.utez.EjerciciosCotizacion.controller;

import mx.edu.utez.EjerciciosCotizacion.controller.dto.RequestCotizadorDTO;
import mx.edu.utez.EjerciciosCotizacion.service.MyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/my-services")
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/cotizaciones")
    public ResponseEntity<Map<String, Object>> cotizar(@Valid @RequestBody RequestCotizadorDTO request) {
        Map<String, Object> resultado = myService.calcularCotizacion(request);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}