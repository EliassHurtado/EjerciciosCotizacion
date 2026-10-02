package mx.edu.utez.EjerciciosCotizacion.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Positive(message = "El número de noches debe ser mayor a 0")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Positive(message = "El número de huéspedes debe ser mayor a 0")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @NotNull(message = "La indicación de desayuno es obligatoria")
    private Boolean incluyeDesayuno;

    @NotNull(message = "La indicación de estacionamiento es obligatoria")
    private Boolean incluyeEstacionamiento;
}