import javax.swing.*;
import java.awt.*;

/**
 * Clase OpponentBoardPanel
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class OpponentBoardPanel extends JPanel {
    private BackgroundPane boardPositionPanel;
    private JLabel boardName;
    private Boards boardPositionOpponent, mainBoardOpponent;
    private String letters[];

    /**
     * Constructor de la clase OpponentBoardPanel
     */
    public OpponentBoardPanel(){
        GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);
        this.setBackground(Color.WHITE);
        boardPositionOpponent = new Boards();
        mainBoardOpponent = new Boards();
        letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        iniciar();
        modelTableroOponente();
    }

    /**
     * Establece la configuración inicial del JComponent
     */
    public void iniciar(){
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel tablero posición
        boardName = new JLabel("Tablero de posicion");
        boardName.setForeground(new Color(0, 0, 0, 230));
        boardName.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(boardName, gbc);

        boardPositionPanel = new BackgroundPane();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0,15,0,15);
        this.add(boardPositionPanel, gbc);
    }

    /**
     * JPanel con imagen para agregar las matrices
     */
    public class BackgroundPane extends JPanel{
        private Image img;

        public BackgroundPane(){

            this.setLayout(new GridLayout(11, 11));
            this.setPreferredSize(new Dimension(400, 400));
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this);
            revalidate();
            repaint();
        }
    }

    /**
     * Crea los tableros posición y principal
     */
    public void modelTableroOponente(){
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if(row == 0 && col == 0){
                    boardPositionOpponent.getMatriz()[row][col] = new JLabel();
                    boardPositionOpponent.getMatriz()[row][col].setOpaque(true);
                    boardPositionOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                    boardPositionOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    mainBoardOpponent.getMatriz()[row][col] = new JLabel();
                    mainBoardOpponent.getMatriz()[row][col].setOpaque(true);
                    mainBoardOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                    mainBoardOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }else{
                    if(row == 0 && col > 0){
                        boardPositionOpponent.getMatriz()[row][col] = new JLabel(letters[col-1], SwingConstants.CENTER);
                        boardPositionOpponent.getMatriz()[row][col].setOpaque(true);
                        boardPositionOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                        boardPositionOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        mainBoardOpponent.getMatriz()[row][col] = new JLabel(letters[col-1], SwingConstants.CENTER);
                        mainBoardOpponent.getMatriz()[row][col].setOpaque(true);
                        mainBoardOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                        mainBoardOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }else{
                        if(row > 0 && col == 0){
                            boardPositionOpponent.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            boardPositionOpponent.getMatriz()[row][col].setOpaque(true);
                            boardPositionOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                            boardPositionOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            mainBoardOpponent.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            mainBoardOpponent.getMatriz()[row][col].setOpaque(true);
                            mainBoardOpponent.getMatriz()[row][col].setBackground(Color.WHITE);
                            mainBoardOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }else{
                            boardPositionOpponent.getMatriz()[row][col] = new JLabel();
                            boardPositionOpponent.getMatriz()[row][col].setOpaque(false);
                            boardPositionOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            mainBoardOpponent.getMatriz()[row][col] = new JLabel();
                            mainBoardOpponent.getMatriz()[row][col].setOpaque(false);
                            mainBoardOpponent.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                boardPositionPanel.add(boardPositionOpponent.getMatriz()[row][col]);
            }
        }
    }

    /**
     * Retorna el tablero ingresado
     * @param _tablero
     * @return Boards
     */
    public Boards getTableroOponente(String _tablero){
        Boards tablero = new Boards();
        if(_tablero.equals("posicion")){
            tablero = boardPositionOpponent;
        }else{
            if(_tablero.equals("principal")){
                tablero = mainBoardOpponent;
            }
        }
        return tablero;
    }
}
