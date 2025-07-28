/**
 * Esta clase es usada...
 * @author Andres Barbosa : andres.barbosa@correounivalle.edu.co - 2324298
 *
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View1 extends JFrame {
    private JLabel imagenLabel;
    private JPanel panelPrincipal;
    private JButton botonJugar;
    private Timer tiempo;


    public View1() {
        this.setTitle("BATTLESHIP");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setResizable(false); // No se puede cambiar el tamaño de la ventana
        this.setSize(1152, 686); // Establecer el tamaño de la ventana
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        initView1();
    }

    private void initView1(){
        //escucha = new Escucha();
        // Crear el JLabel para mostrar la imagen gif
        ImageIcon imagenGIF = new ImageIcon("resources/BATTLESHIP.gif");
        imagenLabel = new JLabel(imagenGIF);

        // Crear el botón y agregarlo al panel contenedor
        botonJugar = new JButton("JUGAR");
        botonJugar.setBackground(Color.white);
        botonJugar.setForeground(Color.BLACK);
        // Crear el borde con un color y un tamaño específicos
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        botonJugar.setBorder(border);
        botonJugar.setFont(new Font("Times New Roman", Font.BOLD, 20));
        botonJugar.setBounds(510, 535, 100, 50); // Establecer el tamaño y la posición del botón
        botonJugar.setVisible(false);
        botonJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                View view = new View();
                view.setVisible(true);
            }
        });
        botonJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonJugar.setBackground(Color.green);

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonJugar.setBackground(Color.white);

            }
        });

        tiempo = new Timer(3000, e -> mostrarImagen());
        tiempo.setRepeats(false);
        add(imagenLabel);
    }

    public void mostrarImagen() {

        // Crear el panel contenedor con OverlayLayout
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);

        //cargar la imagen JPG
        ImageIcon icon = new ImageIcon("resources/BATTLESHIP.jpg");
        Image image = icon.getImage().getScaledInstance(1152, 686, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(image));

        botonJugar.setVisible(true);

        panelPrincipal.add(botonJugar);
        panelPrincipal.add(imagenLabel);

        getContentPane().add(panelPrincipal);
    }

    public JButton getBotonJugar(){
        return botonJugar;
    }

    public void mostrar() {
        setVisible(true);
        tiempo.start();
    }
}