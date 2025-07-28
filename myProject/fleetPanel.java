import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * Clase fleetPanel
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 */
public class fleetPanel extends JPanel{
    public static final String PATH = "/resources/";
    private JButton aircraftCarrier, destroyer, frigate, submarine, vertical, horizontal, yes, is, id, di,explanationButtons;
    private JPanel panelFleet, information, buttonsPanel, subpanelButtons, subpanelButtons1;
    private JLabel assignShift;
    private JTextPane gameInformation;
    private ImageIcon destroyerImage, aircraftCarrierImage, frigateImage, submarineImage;
    private TitledBorder title,title1, title2;
    private Border line;
    private String nameButton = ""; // Guarda el texto del boton
    private int orientation; // 0 yes es vertical, 1 yes es horizontal
    private int orientation1234; // 1 superior-inferior, 2 inferior-superior, 3 izquierda-derecha, 4 derecha-izquierda
    private int quantityAircraftCarrier;
    private int amountSubmarine;
    private int amountDestroyer;
    private int quantityFrigate;

    /**
     * Constructor de clase fleetPanel
     */
    public fleetPanel(){
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,100));
        boats();
    }

    /**
     * Creación de panel con los boats y los botones de sentido y orientación
     */
    public void boats(){
        GridBagConstraints gbc = new GridBagConstraints();
        
        orientation = 0;
        orientation1234 = 0;

        // Cantidad inicial de boats
        quantityAircraftCarrier = 1;
        amountSubmarine = 2;
        amountDestroyer = 3;
        quantityFrigate = 4;

        // Imágenes
        destroyerImage = new ImageIcon(getClass().getResource(PATH + "destructor.png"));
        aircraftCarrierImage = new  ImageIcon(getClass().getResource(PATH+"portavion.png"));
        frigateImage = new ImageIcon(getClass().getResource(PATH+"fragata.png"));
        submarineImage = new ImageIcon(getClass().getResource(PATH+"submarino.png"));

        // Botones de aviones
        aircraftCarrier = new JButton();
        aircraftCarrier.setText("PORTAVION");
        aircraftCarrier.setIcon(aircraftCarrierImage);
        aircraftCarrier.setBackground(Color.WHITE);
        aircraftCarrier.setHorizontalTextPosition( SwingConstants.CENTER );
        aircraftCarrier.setVerticalTextPosition( SwingConstants.BOTTOM );
        aircraftCarrier.setFocusable(false);
        aircraftCarrier.setBorder(null);

        destroyer = new JButton();
        destroyer.setText("DESTRUCTOR");
        destroyer.setIcon(destroyerImage);
        destroyer.setBackground(Color.WHITE);
        destroyer.setHorizontalTextPosition( SwingConstants.CENTER );
        destroyer.setVerticalTextPosition( SwingConstants.BOTTOM );
        destroyer.setFocusable(false);
        destroyer.setBorder(null);

        frigate = new JButton();
        frigate.setText("FRAGATA");
        frigate.setIcon(frigateImage);
        frigate.setBackground(Color.WHITE);
        frigate.setHorizontalTextPosition( SwingConstants.CENTER );
        frigate.setVerticalTextPosition( SwingConstants.BOTTOM );
        frigate.setFocusable(false);
        frigate.setBorder(null);

        submarine = new JButton();
        submarine.setText("SUBMARINO");
        submarine.setIcon(submarineImage);
        submarine.setBackground(Color.WHITE);
        submarine.setHorizontalTextPosition( SwingConstants.CENTER );
        submarine.setVerticalTextPosition( SwingConstants.BOTTOM );
        submarine.setFocusable(false);
        submarine.setBorder(null);

        // Botones de orientación
        vertical = new JButton("Vertical");
        vertical.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        vertical.setBackground(new Color(255, 255, 255));
        vertical.setForeground(Color.BLACK);
        vertical.setFocusable(false);
        vertical.setBorder(null);

        horizontal = new JButton("Horizontal");
        horizontal.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        horizontal.setBackground(new Color(255, 255, 255));
        horizontal.setForeground(Color.BLACK);
        horizontal.setFocusable(false);
        horizontal.setBorder(null);

        // Botones de sentido
        yes = new JButton("Abajo");
        yes.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        yes.setBackground(new Color(255, 255, 255));
        yes.setForeground(Color.BLACK);
        yes.setFocusable(false);
        yes.setBorder(null);

        is = new JButton("Arriba");
        is.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        is.setBackground(new Color(255, 255, 255));
        is.setForeground(Color.BLACK);
        is.setFocusable(false);
        is.setBorder(null);

        id = new JButton("Derecha");
        id.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        id.setBackground(new Color(255, 255, 255));
        id.setForeground(Color.BLACK);
        id.setFocusable(false);
        id.setBorder(null);

        di = new JButton("Izquierda");
        di.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        di.setBackground(new Color(255, 255, 255));
        di.setForeground(Color.BLACK);
        di.setFocusable(false);
        di.setBorder(null);

        // Botón explicación de los botones de orientación
        explanationButtons = new JButton("Explicacion de los botones");
        explanationButtons.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        explanationButtons.setBackground(new Color(255, 255, 255));
        explanationButtons.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(explanationButtons, gbc);

        // Flota
        line = BorderFactory.createLineBorder(Color.black);
        panelFleet = new JPanel();
        panelFleet.setLayout(new GridLayout(2,2,10,10));
        panelFleet.setPreferredSize(new Dimension(350,250));
        panelFleet.setBackground(Color.WHITE);
        title = BorderFactory.createTitledBorder(line, "Barcos");
        title.setTitleJustification(TitledBorder.CENTER);
        panelFleet.setBorder(title);
        panelFleet.add(aircraftCarrier);
        panelFleet.add(destroyer);
        panelFleet.add(frigate);
        panelFleet.add(submarine);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelFleet, gbc);

        assignShift = new JLabel();
        assignShift.setHorizontalAlignment(SwingConstants.CENTER);

        gameInformation = new JTextPane();
        gameInformation.setEditable(false);
        gameInformation.setBackground(Color.WHITE);
        StyledDocument documentStyle = gameInformation.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        information = new JPanel(new GridLayout(2,0,0,0));
        information.setPreferredSize(new Dimension(350,100));
        information.setBackground(Color.WHITE);
        title1 = BorderFactory.createTitledBorder(line, "Información del juego");
        title1.setTitleJustification(TitledBorder.CENTER);
        information.setBorder(title1);
        information.add(assignShift);
        information.add(gameInformation);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(information, gbc);

        // Panel botones dentro de panel flota
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0,2,5,10));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setPreferredSize(new Dimension(350,120));
        title2 = BorderFactory.createTitledBorder(line, "Posición de tu flota");
        title2.setTitleJustification(TitledBorder.CENTER);
        buttonsPanel.setBorder(title2);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(buttonsPanel,gbc);

        // panel dentro de panel botones para los botones de horizontal y vertical
        subpanelButtons = new JPanel(new GridLayout());
        subpanelButtons.setPreferredSize(new Dimension(175,120));
        subpanelButtons.setBackground(Color.WHITE);
        subpanelButtons.setLayout(new GridLayout(2,0,5,5));
        subpanelButtons.add(vertical);
        subpanelButtons.add(horizontal);
        buttonsPanel.add(subpanelButtons,BorderLayout.WEST);

        // panel dentro de panel botones para los botones de yes, is, di, id
        subpanelButtons1 = new JPanel();
        subpanelButtons1.setPreferredSize(new Dimension(175,120));
        subpanelButtons1.setBackground(Color.WHITE);
        subpanelButtons1.setLayout(new GridLayout(4,0,5,5));
        subpanelButtons1.add(yes);
        subpanelButtons1.add(is);
        subpanelButtons1.add(id);
        subpanelButtons1.add(di);
        buttonsPanel.add(subpanelButtons1,BorderLayout.EAST);

    }

    /**
     * Retorna el botón del barco especificado
     */
    public JButton getBotonBarco(String barco){
        JButton boton = new JButton();
        if(barco.equals("portavion")){
            boton = aircraftCarrier;
        }else{
            if(barco.equals("submarino")){
                boton = submarine;
            }else{
                if(barco.equals("destructor")){
                    boton = destroyer;
                }else{
                    if(barco.equals("fragata")){
                        boton = frigate;
                    }
                }
            }
        }
        return boton;
    }

    /**
     * Retorna el botón de orientación especificado
     */
    public JButton getBotonOrientacion(String orientation){
        JButton boton = new JButton();
        if(orientation.equals("vertical")){
            boton = vertical;
        }else{
            if(orientation.equals("horizontal")){
                boton = horizontal;
            }
        }
        return boton;
    }
    
    /**
     * Retorna el botón del sentido de la orientación especificado
     */
    public JButton getBotonSentidoOrientacion(String sentido){
        JButton boton = new JButton();
        if(sentido.equals("Arriba")){
            boton = yes;
        }else{
            if(sentido.equals("Abajo")){
                boton = is;
            }else{
                if(sentido.equals("Izquierda")){
                    boton = id;
                }else{
                    if(sentido.equals("Derecha")){
                        boton = di;
                    }
                }
            }
        }
        return boton;
    }

    /**
     * Guarda el nombre del botón presionado en un string
     * @param _nombreBoton
     */
    public void setNombreBoton(String _nombreBoton){
        nameButton = _nombreBoton;
    }

    /**
     * Retorna el nombre del barco que se presionó
     * @return String
     */
    public String getNombreBoton(){
        return nameButton;
    }

    /**
     * Asigna el estado de orientación
     * @param _orientacion
     */
    public void setOrientacion(int _orientacion){
        orientation = _orientacion;
    }

    /**
     * Asigna el estado de orientation1234
     * @param _sentidoOrientacion
     */
    public void setSentidoOrientacion(int _sentidoOrientacion){
        orientation1234 = _sentidoOrientacion;
    }

    /**
     * Retorna el estado de orientación
     * @return int
     */
    public int getOrientacion(){
        return orientation;
    }

    /**
     * Retorna el estado de orientation1234
     * @return int
     */
    public int getSentidoOrientacion(){
        return orientation1234;
    }

    /**
     * Reduce la cantidad disponible del barco ingresado
     * @param barco
     */
    public void setCantidadBarco(String barco){
        if(barco.equals("portavion")){
            quantityAircraftCarrier--;
        }else{
            if(barco.equals("submarino")){
                amountSubmarine--;
            }else{
                if(barco.equals("destructor")){
                    amountDestroyer--;
                }else{
                    if(barco.equals("fragata")){
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
            if(barco.equals("submarino")){
                cantidad = amountSubmarine;
            }else{
                if(barco.equals("destructor")){
                    cantidad = amountDestroyer;
                }else{
                    if(barco.equals("fragata")){
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

    /**
     * Retorna el JTextPane para editar la información del juego
     * @return JTextPane
     */
    public JTextPane getInformacionJuego(){
        return gameInformation;
    }

    /**
     * Retorna el JLabel que edita el turno
     * @return JLabel
     */
    public JLabel getAsignarTurno(){
        return assignShift;
    }

    /**
     * Retorna el boton que explica la dinámica de los botones
     * @return JButton
     */
    public JButton getExplicacionBotones(){
        return explanationButtons;
    }
}