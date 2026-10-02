package mx.edu.utez.EjerciciosCotizacion.exception.customException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}