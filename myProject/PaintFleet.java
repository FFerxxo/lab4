package myProject;


import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase PaintFleet
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class PaintFleet {
    private BoardPanel boardPanel;
    private fleetPanel fleetPanel;
    private int boatUsed; // Acumulador para identificar cuál nave ha sido desplegada
    private ArrayList<Integer> usedSquares; // casillas usadas por cada nave

    /**
     * Constructor de la clase PaintFleet
     * @param _Board_panel
     * @param _fleet_panel
     */
    public PaintFleet(BoardPanel _Board_panel, fleetPanel _fleet_panel){
        this.boardPanel = _Board_panel;
        this.fleetPanel = _fleet_panel;
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
            boardPanel.getTablero("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
        }else{
            if(barco.equals("submarino" + String.valueOf(numeroBarco))){
                usedSquares.add(3);
                boardPanel.getTablero("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
            }else{
                if(barco.equals("destructor" + String.valueOf(numeroBarco))){
                    usedSquares.add(2);
                    boardPanel.getTablero("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
                }else{
                    if(barco.equals("fragata" + String.valueOf(numeroBarco))){
                        usedSquares.add(1);
                        boardPanel.getTablero("posicion").getCasillaBarco().put(casilla, usedSquares.get(usedSquares.size()-1));
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
    public boolean funcionesFlota(String barco, int estadoOrientacion, int estadoSentidoOrientacion, int col, int row)throws InvalidPositionException{
        int casillasAUsar;
        int casillasUsadas = 0;
        int columnaReferencia = 0;
        int filaReferencia = 0;
        int nextImage;
        boolean auxiliar = false; // false si no pudo colocar el barco, de lo contrario true

        casillasAUsar = ShipFactory.crearBarco(barco); // singleton

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
                throw new InvalidPositionException("No hay espacio para colocar el " + barco); //excepcioon
            }else{
                if(estadoSentidoOrientacion == 3){
                    nextImage = 1;
                    for(int casilla=col; casilla < col+casillasAUsar; casilla++){
                        if(boardPanel.getTablero("posicion").getCasillasOcupadas().get(boardPanel.getTablero("posicion").getMatriz()[row][casilla]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=col; pic < col+casillasAUsar; pic++){
                            boardPanel.getTablero("posicion").getMatriz()[row][pic].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            boardPanel.getTablero("posicion").getCasillasOcupadas().put(boardPanel.getTablero("posicion").getMatriz()[row][pic], 1);
                            boardPanel.getTablero("posicion").getCasillaNombreBarco().put(boardPanel.getTablero("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(boardPanel.getTablero("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage++;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        throw new InvalidPositionException("No hay espacio para colocar el " + barco); // excepcion
                    }
                }else{
                    nextImage = casillasAUsar;
                    for(int casilla=col; casilla > col-casillasAUsar; casilla--){
                        if(boardPanel.getTablero("posicion").getCasillasOcupadas().get(boardPanel.getTablero("posicion").getMatriz()[row][casilla]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=col; pic > col-casillasAUsar; pic--){
                            boardPanel.getTablero("posicion").getMatriz()[row][pic].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            boardPanel.getTablero("posicion").getCasillasOcupadas().put(boardPanel.getTablero("posicion").getMatriz()[row][pic], 1);
                            boardPanel.getTablero("posicion").getCasillaNombreBarco().put(boardPanel.getTablero("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(boardPanel.getTablero("posicion").getMatriz()[row][pic], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage--;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        throw new InvalidPositionException("No hay espacio para colocar el " + barco); // excepcion
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
                throw new InvalidPositionException("No hay espacio para colocar el " + barco); // excepcion
            }else{
                if(estadoSentidoOrientacion == 1){
                    nextImage = 1;
                    for(int casilla=row; casilla < row+casillasAUsar; casilla++){
                        if(boardPanel.getTablero("posicion").getCasillasOcupadas().get(boardPanel.getTablero("posicion").getMatriz()[casilla][col]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=row; pic < row+casillasAUsar; pic++){
                            boardPanel.getTablero("posicion").getMatriz()[pic][col].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            boardPanel.getTablero("posicion").getCasillasOcupadas().put(boardPanel.getTablero("posicion").getMatriz()[pic][col], 1);
                            boardPanel.getTablero("posicion").getCasillaNombreBarco().put(boardPanel.getTablero("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(boardPanel.getTablero("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage++;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        throw new InvalidPositionException("No hay espacio para colocar el " + barco);// excepcion
                    }
                }else{
                    nextImage = casillasAUsar;
                    for(int casilla=row; casilla > row-casillasAUsar; casilla--){
                        if(boardPanel.getTablero("posicion").getCasillasOcupadas().get(boardPanel.getTablero("posicion").getMatriz()[casilla][col]) == Integer.valueOf(1)) {
                            casillasUsadas++;
                        }
                    }

                    if(casillasUsadas == 0){
                        for(int pic=row; pic > row-casillasAUsar; pic--){
                            boardPanel.getTablero("posicion").getMatriz()[pic][col].setIcon(new ImageIcon(getClass().getResource(pathImages(barco, estadoOrientacion, estadoSentidoOrientacion) + String.valueOf(nextImage) + ".png")));
                            boardPanel.getTablero("posicion").getCasillasOcupadas().put(boardPanel.getTablero("posicion").getMatriz()[pic][col], 1);
                            boardPanel.getTablero("posicion").getCasillaNombreBarco().put(boardPanel.getTablero("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed));
                            relacionJLabelBarco(boardPanel.getTablero("posicion").getMatriz()[pic][col], barco + String.valueOf(boatUsed), boatUsed);
                            nextImage--;
                            auxiliar = true;
                        }
                        boatUsed++;
                    }else{
                        throw new InvalidPositionException("No hay espacio para colocar el " + barco); // excepcion
                    }
                }
            }
        }
        return auxiliar;
    }
}