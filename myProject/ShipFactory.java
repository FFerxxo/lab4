/**
 * Factory para crear diferentes tipos de barcos
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class ShipFactory {

    /**
     * Crea un barco según el tipo especificado
     * @param tipo Tipo de barco a crear
     * @return int Tamaño del barco
     */
    public static int crearBarco(String tipo) {
        switch(tipo) {
            case "portavion":
                return 4;
            case "submarino":
                return 3;
            case "destructor":
                return 2;
            case "fragata":
                return 1;
            default:
                return 1;
        }
    }

    /**
     * Obtiene el nombre completo del barco
     * @param tipo Tipo de barco
     * @return String Nombre descriptivo
     */
    public static String getNombreBarco(String tipo) {
        switch(tipo) {
            case "portavion":
                return "Portaaviones";
            case "submarino":
                return "Submarino";
            case "destructor":
                return "Destructor";
            case "fragata":
                return "Fragata";
            default:
                return "Barco desconocido";
        }
    }
}