package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Clase GUI_Secundaria
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class Controller extends JFrame {
    private Header header;
    private OpponentBoardPanel opponentBoard;
    private PaintFleetOpponent paintFleetOpponent;
    private myProject.View _view;
    private int sunk;
    private int state; // 1 si continua, 2 si gana el oponente, de lo contrario 0
    private AttackStrategy attackStrategy;

    /**
     * Constructor de la clase GUI_Secundaria
     */
    public Controller(myProject.View _guiPrincipal) {
        this._view = _guiPrincipal;
        sunk = 0;
        attackStrategy = new RandomAttackStrategy(); //singleton
        initGUI_Secundaria();

        // Configuración del JFrame
        this.setTitle("Batalla Naval");
        this.setUndecorated(false);
        this.setSize(600, 600);
        this.setResizable(true);
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Este método se utiliza para configurar la configuración predeterminada de JComponent,
     * crear objetos de escucha y control utilizados para la clase GUI
     */
    private void initGUI_Secundaria() {
        // Set up JFrame Container's Layout
        getContentPane().setLayout(new BorderLayout(0,0));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        getContentPane().add(panelPrincipal,BorderLayout.CENTER);
        panelPrincipal.setLayout(new BorderLayout(0,0));

        JPanel panelSup = new JPanel();
        panelSup.setBackground(Color.WHITE);
        panelPrincipal.add(panelSup,BorderLayout.NORTH);
        panelSup.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);
        panelPrincipal.add(panelInferior,BorderLayout.SOUTH);
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER,200,0));

        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        panelPrincipal.add(panelCentral,BorderLayout.CENTER);
        panelCentral.setLayout(new GridBagLayout());
        opponentBoard = new OpponentBoardPanel();
        paintFleetOpponent = new PaintFleetOpponent(opponentBoard);
        panelCentral.add(opponentBoard);

        // Set up JComponents
        // Titulo
        header = new Header("MOVIMIENTOS ENEMIGO", Color.WHITE);
        panelSup.add(header,FlowLayout.LEFT);

    }

    /**
     * Selecciona aleatoriamente una casilla para atacar la flota del usuario
     */
    public void oponenteVsUsuario(){
        Random fila = new Random();
        Random columna = new Random();

        int row = fila.nextInt(10)+1;
        int col = columna.nextInt(10)+1;

        // Verifica si la casilla seleccionada hay un barco del usuario
        if(opponentBoard.getTableroOponente("principal").getCasillasOcupadas().get(opponentBoard.getTableroOponente("principal").getMatriz()[row][col]) == Integer.valueOf(1)){
            // Verifica si todas las casillas del barco fueron seleccionadas
            if(_view.getPanelTablero().getTablero("posicion").getCasillaBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]) != Integer.valueOf(0)){
                for(int num=1; num < 11; num++){
                    if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]).equals("portavion" + String.valueOf(num))){
                        funcionesCombate(row, col, "portavion" + String.valueOf(num));
                        break;
                    }else{
                        if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]).equals("submarino" + String.valueOf(num))){
                            funcionesCombate(row, col, "submarino" + String.valueOf(num));
                            break;
                        }else{
                            if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]).equals("destructor" + String.valueOf(num))){
                                funcionesCombate(row, col, "destructor" + String.valueOf(num));
                                break;
                            }else{
                                if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]).equals("fragata" + String.valueOf(num))){
                                    funcionesCombate(row, col, "fragata" + String.valueOf(num));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }else{
            if(opponentBoard.getTableroOponente("principal").getCasillasOcupadas().get(opponentBoard.getTableroOponente("principal").getMatriz()[row][col]) == Integer.valueOf(2)){
                oponenteVsUsuario();
            }else{
                opponentBoard.getTableroOponente("principal").getCasillasOcupadas().put(opponentBoard.getTableroOponente("principal").getMatriz()[row][col], Integer.valueOf(2));
                _view.getPanelTablero().getTablero("posicion").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/resources/agua.png")));
                opponentBoard.getTableroOponente("principal").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/resources/agua.png")));
                state = 0;
            }
        }
    }

    /**
     * Identifica si hay un barco en la casilla del tablero principal para hundirlo
     */
    public void funcionesCombate(int row, int col, String barco){
        // Establece una imagen a la casilla seleccionada del tablero posición del usuario si un barco fue tocado
        _view.getPanelTablero().getTablero("posicion").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/resources/tocado.png")));
        opponentBoard.getTableroOponente("principal").getCasillasOcupadas().replace(opponentBoard.getTableroOponente("principal").getMatriz()[row][col], Integer.valueOf(2));

        // Reduce las casillas ocupadas del barco tocado para poder ser hundido
        _view.getPanelTablero().getTablero("posicion").reducirCasillasUsadas(barco);

        // Si no hay más casillas ocupadas, el barco se hunde y se establecen las imágenes respectivas
        if(_view.getPanelTablero().getTablero("posicion").getCasillaBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[row][col]) == Integer.valueOf(0)){
            sunk++;
            state = 1;
            for (int fil = 1; fil < 11; fil++) {
                for (int colu = 1; colu < 11; colu++) {
                    if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[fil][colu]) != null){
                        if(_view.getPanelTablero().getTablero("posicion").getCasillaNombreBarco().get(_view.getPanelTablero().getTablero("posicion").getMatriz()[fil][colu]).equals(barco)){
                            _view.getPanelTablero().getTablero("posicion").getMatriz()[fil][colu].setIcon(new ImageIcon(getClass().getResource("/resources/hundido.png")));
                        }
                    }else{
                        continue;
                    }
                }
            }
        }else{
            state = 1;
        }

        if(sunk == 10){
            state = 2;
        }
    }

    /**
     * Distribuye de forma aleatoria la flota en el tablero posición del oponente
     */
    public void distribucionFlotaOponente(){
        Random barcoAleatorio = new Random();
        String nombreBarco = "";
        int numBarcoAleatorio = barcoAleatorio.nextInt(4)+1;

        switch (numBarcoAleatorio){
            case 1: nombreBarco = "portavion";
                break;
            case 2: nombreBarco = "submarino";
                break;
            case 3: nombreBarco = "destructor";
                break;
            case 4: nombreBarco = "fragata";
                break;
        }

        Random orientacionAleatoria = new Random();
        int numOrientacionAleatoria = orientacionAleatoria.nextInt(2);

        Random sentidoAleatorio = new Random();
        int numSentidoAleatorio = 0;
        switch (numOrientacionAleatoria){
            case 0:
                numSentidoAleatorio = sentidoAleatorio.nextInt(2)+1;
                break;
            case 1:
                numSentidoAleatorio = sentidoAleatorio.nextInt(4-3)+3;
                break;
        }

        Random columnaAleatoria = new Random();
        int numColumnaAleatoria = columnaAleatoria.nextInt(10)+1;

        Random filaAleatoria = new Random();
        int numFilaAleatoria = filaAleatoria.nextInt(10)+1;

        if(numBarcoAleatorio == 1 &&  paintFleetOpponent.getCantidadBarco("portavion") > 0){
            if(!paintFleetOpponent.funcionesFlota(nombreBarco, numOrientacionAleatoria, numSentidoAleatorio, numColumnaAleatoria, numFilaAleatoria)){
                distribucionFlotaOponente();
            }else{
                paintFleetOpponent.setCantidadBarco("portavion");
            }
        }else{
            if(numBarcoAleatorio == 2 &&  paintFleetOpponent.getCantidadBarco("submarino") > 0){
                if(!paintFleetOpponent.funcionesFlota(nombreBarco,numOrientacionAleatoria, numSentidoAleatorio, numColumnaAleatoria, numFilaAleatoria)){
                    distribucionFlotaOponente();
                }else{
                    paintFleetOpponent.setCantidadBarco("submarino");
                }
            }else{
                if(numBarcoAleatorio == 3 &&  paintFleetOpponent.getCantidadBarco("destructor") > 0){
                    if(!paintFleetOpponent.funcionesFlota(nombreBarco,numOrientacionAleatoria, numSentidoAleatorio, numColumnaAleatoria, numFilaAleatoria)){
                        distribucionFlotaOponente();
                    }else{
                        paintFleetOpponent.setCantidadBarco("destructor");
                    }
                }else{
                    if(numBarcoAleatorio == 4 &&  paintFleetOpponent.getCantidadBarco("fragata") > 0){
                        if(!paintFleetOpponent.funcionesFlota(nombreBarco,numOrientacionAleatoria, numSentidoAleatorio, numColumnaAleatoria, numFilaAleatoria)){
                            distribucionFlotaOponente();
                        }else{
                            paintFleetOpponent.setCantidadBarco("fragata");
                        }
                    }
                }
            }
        }
    }

    /**
     * Retorna el panelTableroOponente
     */
    public OpponentBoardPanel getTableroOponente(){
        return opponentBoard;
    }

    /**
     * Retorna el objeto para pintar la flota oponente
     */
    public PaintFleetOpponent getPintarFlotaOponente(){
        return paintFleetOpponent;
    }

    /**
     * Retorna la variable state
     */
    public int getEstado(){
        return state;
    }
}