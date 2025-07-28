import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Clase Boards
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class Boards implements Serializable {
    private static final long serialVersionUID = 1L;
    private JLabel board[][];
    private HashMap<JLabel, Integer> usedSquares; // Verifica si la casilla esta ocupada por una nave, 1 si esta ocupado, 2 si fue atacado
    private HashMap<JLabel, Integer> boxShip; // Relacion entre la casilla y las casillas que usa la nave
    private HashMap<JLabel, String> boxNameShip; // Almacena el nombre del barco ubicado en la casilla

    /**
     * Constructor de la clase Boards
     */
    public Boards(){
        board = new JLabel[11][11];
        usedSquares = new HashMap<>();
        boxShip = new HashMap<>();
        boxNameShip = new HashMap<>();
    }

    /**
     * Reduce la cantidad de casillas ocupadas por los barcos para que sea hundido
     * @param barco
     */
    public void reducirCasillasUsadas(String barco){
        for (int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if(boxNameShip.get(board[row][col]) != null){
                    if(boxNameShip.get(board[row][col]).equals(barco)){
                        boxShip.replace(board[row][col], boxShip.get(board[row][col])-1);
                    }
                }
            }
        }
    }

    /**
     * Retorna la matriz
     * @return JLabel[][]
     */
    public JLabel[][] getMatriz(){
        return board;
    }

    /**
     * Retorna el map usedSquares
     * @return HashMap
     */
    public HashMap getCasillasOcupadas(){
        return usedSquares;
    }

    /**
     * Retorna el map boxShip
     * @return HashMap
     */
    public HashMap getCasillaBarco(){
        return boxShip;
    }

    /**
     * Retorna el map boxNameShip
     * @return HashMap
     */
    public HashMap getCasillaNombreBarco(){
        return boxNameShip;
    }
}