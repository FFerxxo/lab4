/**
 * Excepción que se lanza cuando se intenta colocar un barco en una posición inválida
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class InvalidPositionException extends Exception {

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de error a mostrar
     */
    public InvalidPositionException(String mensaje) {
        super(mensaje);
    }
}
