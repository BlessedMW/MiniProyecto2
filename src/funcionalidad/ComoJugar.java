/*
Archivo: ComoJugar.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

public class ComoJugar extends JFrame {
    //Declaraciones
    //Uso Mixto
    private int anchoV = 700;
    private int largoV = 500;
    private Clip clip;
    private String rutaAbsoluta;
    private int numVentana = 1;
    private JTextPane txtTexto;
    private double t = 0;
    //Labels
    private JLabel lblTexto;
    private JLabel lblFondo;
    private JLabel lblFlecha;
    private JLabel txtNumeroVentana;
    private JLabel lblImagen;
    //Botones
    private JButton btnSalir;
    private JButton btnSiguiente;
    private JButton btnAtras;
    //Img
    private ImageIcon imgFondo;
    private ImageIcon iconoFlecha;
    private ImageIcon iconoSalir;
    private ImageIcon imgEjemplo;
    private ImageIcon iconoSiguiente;
    private ImageIcon iconoAtras;
    //Contenedor
    private Container contPrincipal;
    //Constructor
    public ComoJugar() throws IOException {
        iniciarVentana();
        iniciarComponentes();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2_Game");
        setResizable(false);
    }

    private void iniciarComponentes() throws IOException {
        //Declaracion
        rutaAbsoluta = new File("").getAbsolutePath();
        imgFondo = establecerIcon("/src/imagenes/fondoComoJugar.png", anchoV,largoV);
        imgEjemplo = establecerIcon("/src/imagenes/comoJugar1.png", 350, 270);
        iconoSiguiente = establecerIcon("/src/imagenes/Next.png", 130, 40);
        iconoAtras = establecerIcon("/src/imagenes/atras.png", 130, 40);
        iconoSalir = establecerIcon("/src/imagenes/salir.png", 70, 70);
        iconoFlecha = establecerIcon("/src/imagenes/flecha.png", 70, 70);
        //Llamado de metodos de componentes
        creaLbls();
        NumVentana();
        Muestra();
        btnSiguiente();
        btnAtras();
        indicador();
        btnSalir();
        textoGui();
        ContPrincipal();
    }

    private void creaLbls(){     
        //Label Fondo
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);
        lblFondo.setLayout(null);
    }
    
    private void NumVentana(){
        //Numero de Ventana
        txtNumeroVentana = new JLabel("1/4");
        txtNumeroVentana.setFont(new Font("cooper black", 2, 24));
        txtNumeroVentana.setBounds(35, 20, 50, 30);
        lblFondo.add(txtNumeroVentana);

    }
    
    private void Muestra(){
        //Imagen de muestra
        lblImagen = new JLabel(imgEjemplo);
        lblImagen.setBounds(175, 20, 350, 270);
        lblFondo.add(lblImagen);
    
    }
    
    private void btnSiguiente(){
        //Boton Siguiente
        btnSiguiente = new BotonSinFondo();
        btnSiguiente.setIcon(iconoSiguiente);
        btnSiguiente.setBounds(536, 140, 130, 40);  
        btnSiguiente.addMouseListener(new ManejadorDeEventos());
        btnSiguiente.addKeyListener(new ManejadoraEventosTeclado());
        lblFondo.add(btnSiguiente);
    }
    
    private void btnAtras(){
        //Boton Atras
        btnAtras = new BotonSinFondo();
        btnAtras.setIcon(iconoAtras);
        btnAtras.setBounds(20, 140, 130, 40);
        btnAtras.setVisible(false);  
        btnAtras.addMouseListener(new ManejadorDeEventos());
        btnAtras.addKeyListener(new ManejadoraEventosTeclado());
        lblFondo.add(btnAtras);
    }
    
    private void btnSalir(){
        //Boton Salir
        btnSalir = new BotonSinFondo();
        btnSalir.setIcon(iconoSalir);
        btnSalir.setBounds(600, 20, 70, 70);
        btnSalir.addMouseListener(new ManejadorDeEventos());
        btnSalir.addKeyListener(new ManejadoraEventosTeclado());
        btnSalir.requestFocus();
        lblFondo.add(btnSalir);
    
    }
    
    private void indicador(){
        //Indicador
        lblFlecha = new JLabel(iconoFlecha);
        lblFlecha.setBounds(509, 200, 70, 70);
        lblFlecha.setVisible(false);
        lblFondo.add(lblFlecha);
    }
    
    private void textoGui(){
        //Texto GUI
        lblTexto = new JLabel("<html>En Adosa Game apareceran en pantalla una serie de baldosas; las baldosas iran cambiando una por una mostrando diferentes diseños. Podrás saber que  baldosa cambia en cada momento, gracias a un marco verde que las rodea.<html>");
        lblTexto.setBounds(10,220,670,300);
        lblTexto.setForeground(Color.white);
        lblTexto.setFont(new Font("cooper black",1,25));
        lblFondo.add(lblTexto);
    }
    
    private void ContPrincipal(){
        //Contenedor
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        contPrincipal.add(lblFondo);
    }

    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto) throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }

    private class ManejadorDeEventos extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent excepcion) {
            if (excepcion.getSource() == btnSiguiente) {
                switch (numVentana) {
                    case 1 ->
                        iniciarVentana2();
                    case 2 ->
                        iniciarVentana3();
                    case 3 ->
                        iniciarVentana4();
                    default -> {
                    }
                }
            } else if (excepcion.getSource() == btnSalir) {
                dispose();
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
                try {
                    Menu ventanaInicial = new Menu(1);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (excepcion.getSource() == btnAtras) {
                switch (numVentana) {
                    case 2 ->
                        iniciarVentana1();
                    case 3 ->
                        iniciarVentana2();
                    case 4 ->
                        iniciarVentana3();
                    default -> {
                    }
                }
            }
        }

    }

    private class ManejadoraEventosTeclado implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource() == btnSalir || e.getSource() == btnAtras || e.getSource() == btnSiguiente) {
                switch (e.getKeyCode()) {
                    case 39 -> {
                        switch (numVentana) {
                            case 1 -> {
                                iniciarVentana2();
                            }
                            case 2 -> {
                                iniciarVentana3();
                            }
                            case 3 -> {
                                iniciarVentana4();
                            }

                            default -> {
                            }
                        }
                    }
                    case 10, 32 -> {
                        dispose();
                        if (clip != null && clip.isRunning()) {
                            clip.stop();
                        }
                        try {
                            Menu ventanaInicial = new Menu(1);
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    case 37 -> {
                        switch (numVentana) {
                            case 2 -> {
                                iniciarVentana1();
                            }
                            case 3 -> {
                                iniciarVentana2();
                            }
                            case 4 -> {
                                iniciarVentana3();
                            }

                            default -> {
                            }
                        }
                    }
                    default -> {
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }


    private void iniciarVentana1() {
        numVentana = 1;
        txtNumeroVentana.setText("1/4");

        t = 0;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        try {
            imgEjemplo = establecerIcon("/src/imagenes/comoJugar1.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(false);
        btnAtras.setVisible(false);
        txtTexto.setText("<html>En Adosa Game apareceran en pantalla una serie de baldosas; las baldosas iran cambiando una por una mostrando diferentes diseños. Podrás saber que  baldosa cambia en cada momento, gracias a un marco verde que las rodea.<html>");

    }

    private void iniciarVentana2() {
        numVentana = 2;
        txtNumeroVentana.setText("2/4");
        t = 0;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        try {
            imgEjemplo = establecerIcon("/src/imagenes/comoJugar2.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(true);
        btnAtras.setVisible(true);
        lblTexto.setText("<html>En el momento en el que veas en pantalla 2 baldosas identicas, deberás presionar rapidamente el pulsador blanco que aparece en la zona inferior derecha.<html>");
                         
    }

    private void iniciarVentana3() {
        numVentana = 3;
        txtNumeroVentana.setText("3/4");
        t = 0;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        try {
            imgEjemplo = establecerIcon("/src/imagenes/comoJugar3.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        btnSiguiente.setVisible(true);
        lblFlecha.setVisible(false);
        lblTexto.setText("<html>Hay dos formas de presionar el pulsador:1-Pulsando directamente el boton blanco en panatlla. 2-Pulsando la barra de espacio del teclado.<html>");

    }

    private void iniciarVentana4() {
        numVentana = 4;
        txtNumeroVentana.setText("4/4");

        t = 0;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        try {
            imgEjemplo = establecerIcon("/src/imagenes/comoJugar4.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(true);
        lblFlecha.setBounds(509, 20, 70, 70);
        lblFondo.add(lblFlecha);
        lblFondo.add(lblImagen);

        btnSiguiente.setVisible(false);

        lblTexto.setText("<html>OJO! Si no pulsas a tiempo, perderas vidas. En eL transcurso del juego las veelocidad de cambio de baldosas aumentará. TENDRÁS QUE ESTAR MUY CINCENTRADO<html>");

    }

    //Clase de boton sin fondo ni bordes
    private class BotonSinFondo extends JButton {
        public BotonSinFondo() {
            inicializar();
        }

        private void inicializar() {
            setRolloverEnabled(true);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
    }

}
