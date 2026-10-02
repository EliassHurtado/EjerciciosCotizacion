package mx.edu.utez.EjerciciosCotizacion.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRentaVehiculoDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Positive(message = "La edad debe ser un número positivo")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "Los días de renta son obligatorios")
    @Positive(message = "Los días de renta deben ser mayores a 0")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @PositiveOrZero(message = "Los kilómetros deben ser mayores o iguales a 0")
    private Integer kilometrosEstimados;

    @NotNull(message = "La indicación de seguro es obligatoria")
    private Boolean seguroCompleto;

}