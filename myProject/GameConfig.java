/**
 * Singleton para manejar la configuración del juego
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class GameConfig {
    private static GameConfig instance;
    private int maxBarcos;
    private String dificultad;

    /**
     * Constructor privado para evitar instanciación externa
     */
    private GameConfig() {
        maxBarcos = 10;
        dificultad = "Normal";
    }

    /**
     * Método para obtener la única instancia
     * @return GameConfig
     */
    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    /**
     * Getter para máximo de barcos
     * @return int
     */
    public int getMaxBarcos() {
        return maxBarcos;
    }

    /**
     * Setter para dificultad
     * @param nuevaDificultad
     */
    public void setDificultad(String nuevaDificultad) {
        this.dificultad = nuevaDificultad;
    }

    /**
     * Getter para dificultad
     * @return String
     */
    public String getDificultad() {
        return dificultad;
    }
}