import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase PaintFleetOpponent
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class PaintFleetOpponent {
    private OpponentBoardPanel opponentBoardPanel;
    private int quantityAircraftCarrier;
    private int amountSubmarine;
    private int amountDestroyer;
    private int quantityFrigate;
    private int boatUsed; // Acumulador para identificar cuál nave ha sido desplegada (en orden del 1 al 10)
    private ArrayList<Integer> usedSquares;

    /**
     * Constructor de la clase PaintFleetOpponent
     * @param _OpponentBoard_panel
     */
    public PaintFleetOpponent(OpponentBoardPanel _OpponentBoard_panel){
        this.opponentBoardPanel = _OpponentBoard_panel;
        quantityAircraftCarrier = 1;
        amountSubmarine = 2;
        amountDestroyer = 3;
        quantityFrigate = 4;
        boatUsed = 1;
        usedSquares = new ArrayList<>();
    }

    /**
     * Retorna la dirección de la imagen dependiendo del barco ingresado
     * @param barco
     * @param estadoOrientacion
     * @param estadoSentidoOrientacion
     * @return String
     */
    public String pathImages(String barco, int estadoOrientacion, int estadoSentidoOrientacion){
        String path = "";
        if(estadoOrientacion == 0){
            switch(estadoSentidoOrientacion){
                case 1:
                    path = "/resources/" + barco + "_V_S_I/";
                    break;
                case 2:
                    path = "/resources/" + barco + "_V_I_S/";
                    break;
            }
        }else{
            switch(estadoSentidoOrientacion){
                case 3:
                    path = "/resources/" + barco + "_H_I_D/";
                    break;
                case 4:
                    path = "/resources/" + barco + "_H_D_I/";
                    break;
            }
        }
        return path;
    }

    /**
     * Relaciona la casilla y la cantidad de casillas que usa el barco ingresado
     * @param casilla
     * @param barco
     * @param numeroBarco
     */
    public void relacionJLabelBarco(JLabel casilla, String barco, int numeroBarco){
        if(barco.equals("portavion" + String.valueOf(numeroBarco))){
            usedSquares.add(4);
            opponentBoardPanel.getTableroOponente("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
        }else{
            if(barco.equals("submarino" + String.valueOf(numeroBarco))){
                usedSquares.add(3);
                opponentBoardPanel.getTableroOponente("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
            }else{
                if(barco.equals("destructor" + String.valueOf(numeroBarco))){
                    usedSquares.add(2);
                    opponentBoardPanel.getTableroOponente("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
                }else{
                    if(barco.equals("fragata" + String.valueOf(numeroBarco))){
                        usedSquares.add(1);
                        opponentBoardPanel.getTableroOponente("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
                    }
                }
            }
        }
    }

    /**
     * Pinta el barco en las respectivas casillas del tablero posición
     * @param barco
     * @param estadoOrientacion
     * @param estadoSentidoOrientacion
     * @param col
     * @param row
     * @return boolean
     */
    public boolean funcionesFlota(String barco, int estadoOrientacion, int estadoSentidoOrientacion, int col, int row){
        int casillasAUsar; // Cantidad de casillas que ocupa el barco
        int casillasUsadas = 0; // Determina si las casillas siguientes están ocupadas para poder desplegar el barco
        int columnaReferencia = 0; // Es la columna de referencia dependiendo si es horizontal o vertical
        int filaReferencia = 0; // Es la fila de referencia dependiendo si es horizontal o vertical
        int nextImage; // Acumulador para mostrar las imágenes en orden
        boolean auxiliar = false; // false si no se puede colocar el barco, de lo contrario true

        if(barco == "portavion"){
            casillasAUsar = 4;
        }else{
            if(barco == "submarino"){
                casillasAUsar = 3;
            }else{
                if(barco == "destructor"){
                    casillasAUsar = 2;
                }else{
                    casillasAUsar = 1;
                }
            }
        }

        // Si la orientación es horizontal, solo puede usar dos sentidos
        if(estadoOrientacion == 1){
            if(estadoSentidoOrientacion == 3){
                columnaReferencia = 10;
            }else{
                if(estadoSentidoOrientacion == 4){
                    columnaReferencia = 1;
                }
            }

            int ultimasCasillas = Math.abs(col - columnaReferencia);
            if(ultimasCasillas < casillasAUsar-1){
                auxiliar = false;
            }else{
                if(estadoSentidoOrientacion == 3){
                    nextImage = 1;
                    // Determina si las casillas siguientes estan ocupadas ocupadas por otra nave o no
                    for(int casilla=col; casilla < col+casillasAUsar; casilla++){
                        if(opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().get(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][casilla]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    // Si las casillas siguientes no estan ocupadas, se despliega el barco
                    if(casillasUsadas == 0){
                        for(int pic=col; pic < col+casillasAUsar; pic++){
                            opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], 1);
                            opponentBoardPanel.getTableroOponente("posicion").getCasillaNombreBarco().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage++;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        auxiliar = false;
                    }
                }else{
                    nextImage = casillasAUsar;
                    for(int casilla=col; casilla > col-casillasAUsar; casilla--){
                        if(opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().get(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][casilla]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=col; pic > col-casillasAUsar; pic--){
                            opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], 1);
                            opponentBoardPanel.getTableroOponente("posicion").getCasillaNombreBarco().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage--;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        auxiliar = false;
                    }
                }
            }
        }else{
            if(estadoSentidoOrientacion == 1){
                filaReferencia = 10;
            }else{
                if(estadoSentidoOrientacion == 2){
                    filaReferencia = 1;
                }
            }

            int ultimasCasillas = Math.abs(row - filaReferencia);
            if(ultimasCasillas < casillasAUsar-1){
                auxiliar = false;
            }else{
                if(estadoSentidoOrientacion == 1){
                    nextImage = 1;
                    for(int casilla=row; casilla < row+casillasAUsar; casilla++){
                        if(opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().get(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[casilla][col]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=row; pic < row+casillasAUsar; pic++){
                            opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], 1);
                            opponentBoardPanel.getTableroOponente("posicion").getCasillaNombreBarco().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage++;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        auxiliar = false;
                    }
                }else{
                    nextImage = casillasAUsar;
                    for(int casilla=row; casilla > row-casillasAUsar; casilla--){
                        if(opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().get(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[casilla][col]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=row; pic > row-casillasAUsar; pic--){
                            opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            opponentBoardPanel.getTableroOponente("posicion").getCasillasOcupadas().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], 1);
                            opponentBoardPanel.getTableroOponente("posicion").getCasillaNombreBarco().put(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(opponentBoardPanel.getTableroOponente("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage--;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        auxiliar = false;
                    }
                }
            }
        }
        return auxiliar;
    }

    /**
     * Cambia la cantidad disponible del barco ingresado
     * @param barco
     */
    public void setCantidadBarco(String barco){
        if(barco.equals("portavion")){
            quantityAircraftCarrier--;
        }else{
            if(barco.equals("submarino")) {
                amountSubmarine--;
            }else{
                if(barco.equals("destructor")) {
                    amountDestroyer--;
                }else{
                    if(barco.equals("fragata")) {
                        quantityFrigate--;
                    }
                }
            }
        }
    }

    /**
     * Retorna la cantidad disponible del barco ingresado
     * @param barco
     * @return int
     */
    public int getCantidadBarco(String barco){
        int cantidad = 0;
        if(barco.equals("portavion")){
            cantidad = quantityAircraftCarrier;
        }else{
            if(barco.equals("submarino")) {
                cantidad = amountSubmarine;
            }else{
                if(barco.equals("destructor")) {
                    cantidad = amountDestroyer;
                }else{
                    if(barco.equals("fragata")) {
                        cantidad = quantityFrigate;
                    }
                }
            }
        }
        return cantidad;
    }

    /**
     * Retorna la cantidad total de naves disponibles
     * @return int
     */
    public int cantidadTotalNaves(){
        return quantityAircraftCarrier + amountSubmarine + amountDestroyer + quantityFrigate;
    }
}