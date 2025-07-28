package myProject;

import javax.swing.*;

/**
 * Adapter para simplificar el acceso al tablero
 * @author
 */
public class BoardAdapter {
    private Boards board;

    public BoardAdapter(Boards board) {
        this.board = board;
    }

    /**
     * Método simplificado para obtener casilla
     */
    public JLabel getCasilla(int row, int col) {
        return board.getMatriz()[row][col];
    }

    /**
     * Método simplificado para verificar si está ocupada
     */
    public boolean estaOcupada(int row, int col) {
        Integer valor = (Integer) board.getCasillasOcupadas().get(board.getMatriz()[row][col]);
        return valor != null && valor == 1;
    }
}