package myProject;


/**
 * Estrategia de ataque aleatorio para el oponente
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class RandomAttackStrategy implements AttackStrategy {

    @Override
    public String ejecutarAtaque(int row, int col) {
        return "Ataque aleatorio en posición [" + row + "," + col + "]";
    }
}