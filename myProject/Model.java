package myProject;

/**
 * Clase Combate
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class Model {
    private BoardPanel boardPanel;
    private OpponentBoardPanel opponentBoardPanel;

    /**
     * Constructor de la clase Combate
     * @param _Board_panel
     * @param _OpponentBoard_panel
     */
    public Model(BoardPanel _Board_panel, OpponentBoardPanel _OpponentBoard_panel){
        this.boardPanel = _Board_panel;
        this.opponentBoardPanel = _OpponentBoard_panel;
    }

    /**
     * Busca las casillas ocupadas por naves del tablero posición del oponente y las marca en el tablero principal del usuario
     */
    public void usuarioVsOponente(){
        for(int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if(opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().get(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][col]) == Integer.valueOf(1)){
                    boardPanel.getTablero("principal").getCasillasOcupadas().put(boardPanel.getTablero("principal").getMatriz()[row][col], 1);
                }
            }
        }
    }

    /**
     * Busca las casillas ocupadas por naves del tablero posición del usuario y las marca en el tablero principal del oponente
     */
    public void oponenteVsUsuario(){
        for(int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if(boardPanel.getTablero("posicion").getCasillasOcupadas().get(boardPanel.getTablero("posicion").getMatriz()[row][col]) == Integer.valueOf(1)){
                    opponentBoardPanel.getTableroOponente("principal").getCasillasOcupadas().put(opponentBoardPanel.getTableroOponente("principal").getMatriz()[row][col], 1);
                }
            }
        }
    }
}
