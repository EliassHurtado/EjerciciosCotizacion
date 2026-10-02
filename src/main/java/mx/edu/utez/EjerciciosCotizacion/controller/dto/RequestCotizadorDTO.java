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
public class RequestCotizadorDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso en kg es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    private Double pesoKg;

    @NotNull(message = "El largo en cm es obligatorio")
    @Positive(message = "El largo debe ser mayor a 0")
    private Double largoCm;

    @NotNull(message = "El ancho en cm es obligatorio")
    @Positive(message = "El ancho debe ser mayor a 0")
    private Double anchoCm;

    @NotNull(message = "El alto en cm es obligatorio")
    @Positive(message = "El alto debe ser mayor a 0")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @Positive(message = "El valor declarado debe ser mayor a 0")
    private Double valorDeclarado;

}