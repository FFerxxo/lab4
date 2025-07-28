/**
 * Interfaz Strategy para diferentes tipos de ataque
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public interface AttackStrategy {
    /**
     * Ejecuta el ataque según la estrategia
     * @param row Fila del ataque
     * @param col Columna del ataque
     * @return String Resultado del ataque
     */
    String ejecutarAtaque(int row, int col);
}