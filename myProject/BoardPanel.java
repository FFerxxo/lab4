package myProject;


import javax.swing.*;
import java.awt.*;

/**
 * Clase BoardPanel
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class BoardPanel extends JPanel {

    public static final String PATH = "/resources/";
    private JLabel nameBoardPosition, mainBoardName;
    private BackgroundPane panelBoardPosition, panelMainBoard;
    private Boards boardPosition, mainBoard;
    private String alphabet[];

    /**
     * Constructor de la clase BoardPanel
     */
    public BoardPanel(){
        GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);
        this.setBackground(Color.WHITE);
        boardPosition = new Boards();
        mainBoard = new Boards();
        alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        iniciar();
        modelTablero();
    }

    /**
     * Establece la configuración inicial del JComponent
     */
    public void iniciar(){
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel tablero posición
        nameBoardPosition = new JLabel("Tablero de posicion");
        nameBoardPosition.setForeground(new Color(0, 0, 0, 230));
        nameBoardPosition.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(nameBoardPosition, gbc);

        panelBoardPosition = new BackgroundPane();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0,15,0,15);
        this.add(panelBoardPosition, gbc);

        // Panel tablero principal
        mainBoardName = new JLabel("Tablero principal");
        mainBoardName.setForeground(new Color(0, 0, 0, 230));
        mainBoardName.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(mainBoardName, gbc);

        panelMainBoard = new BackgroundPane();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0,15,0,15);
        this.add(panelMainBoard, gbc);

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
    public void modelTablero(){
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if(row == 0 && col == 0){
                    boardPosition.getMatriz()[row][col] = new JLabel();
                    boardPosition.getMatriz()[row][col].setOpaque(true);
                    boardPosition.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    boardPosition.getMatriz()[row][col].setBackground(Color.WHITE);

                    mainBoard.getMatriz()[row][col] = new JLabel();
                    mainBoard.getMatriz()[row][col].setOpaque(true);
                    mainBoard.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mainBoard.getMatriz()[row][col].setBackground(Color.WHITE);
                }else{
                    if(row == 0 && col > 0){
                        boardPosition.getMatriz()[row][col] = new JLabel(alphabet[col-1], SwingConstants.CENTER);
                        boardPosition.getMatriz()[row][col].setOpaque(true);
                        boardPosition.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        boardPosition.getMatriz()[row][col].setBackground(Color.WHITE);

                        mainBoard.getMatriz()[row][col] = new JLabel(alphabet[col-1], SwingConstants.CENTER);
                        mainBoard.getMatriz()[row][col].setOpaque(true);
                        mainBoard.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        mainBoard.getMatriz()[row][col].setBackground(Color.WHITE);
                    }else{
                        if(row > 0 && col == 0){
                            boardPosition.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            boardPosition.getMatriz()[row][col].setOpaque(true);
                            boardPosition.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            boardPosition.getMatriz()[row][col].setBackground(Color.WHITE);

                            mainBoard.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            mainBoard.getMatriz()[row][col].setOpaque(true);
                            mainBoard.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            mainBoard.getMatriz()[row][col].setBackground(Color.WHITE);
                        }else{
                            boardPosition.getMatriz()[row][col] = new JLabel();
                            boardPosition.getMatriz()[row][col].setOpaque(false);
                            boardPosition.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            mainBoard.getMatriz()[row][col] = new JLabel();
                            mainBoard.getMatriz()[row][col].setOpaque(false);
                            mainBoard.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }

                panelBoardPosition.add(boardPosition.getMatriz()[row][col]);
                panelMainBoard.add(mainBoard.getMatriz()[row][col]);
            }
        }
    }

    /**
     * Retorna el tablero ingresado
     * @param _tablero
     * @return Boards
     */
    public Boards getTablero(String _tablero){
        Boards tablero = new Boards();
        if(_tablero.equals("posicion")){
            tablero = boardPosition;
        }else{
            if(_tablero.equals("principal")){
                tablero = mainBoard;
            }
        }
        return tablero;
    }
}