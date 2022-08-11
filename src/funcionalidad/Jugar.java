/*
Archivo: Jugar.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import javax.swing.Timer;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import funcionalidad.LogicGame;
import funcionalidad.Baldosas;


/**
 *
 * @author 
 */
public class Jugar extends JFrame {
    //Declaracion
    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;
    //Timer
    private Timer tiempo;
    //Labels
    private JLabel lblPuntaje;
    private JLabel lblFondo;
    private JLabel lblContador;
    //Boton
    private JButton btnBlanco;
    //Image
    private ImageIcon imgFondo;
    private ImageIcon BtnClik;
    //Baldosas y logica
    private Baldosas imgsBaldosas;
    private int baldosaCambiada = -1;
    private LogicGame logica;
    private Container contPrincipal;
    private boolean puedeJugar = false;
    private boolean puedeTirar = false;
    private double tAux = 0;
    private boolean inicioJuego = true;
    private String rutaAbsoluta;
    private ArrayList<JLabel> listaVidas;
    private ArrayList<JLabel> listaBaldosas;
    //Constructor
    public Jugar() throws IOException {
        iniciarVentana();
        iniciarComponentes();
    }

    private void iniciarVentana() {
        //Ajustes
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
        logica = new LogicGame();
        imgsBaldosas = new Baldosas();
        tiempo = new Timer(100, new ManejadorDeEventosTiempo());
        tiempo.start();
        imgFondo = establecerIcon("/src/imagenes/fondoJuego.png", anchoV - 10,largoV - 38);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setLayout(null);
        BtnClik = establecerIcon("/src/imagenes/click.png", 100, 100);
        listaVidas = new ArrayList<>();
        inicializarVidas();
        listaBaldosas = new ArrayList<>();
        inicializarBaldosas();
        //Ciclos For
        for (int i = 0; i < 8; i++) {
            lblFondo.add(listaBaldosas.get(i));
        }
        for (int i = 0; i < 3; i++) {
            lblFondo.add(listaVidas.get(i));
        }
        //Llamado de metodos de componentes
        lblpuntaje();
        btnBlanco();
        lblcontador();
        contPrinci();
    }
    
    
    private void lblpuntaje(){
        //Puntaje
        lblPuntaje = new JLabel("Puntaje: 0000");
        lblPuntaje.setForeground(Color.MAGENTA);
        lblPuntaje.setBounds(5, 0, 220, 50);
        lblPuntaje.setFont(new Font("cooper black",3, 25));
        lblFondo.add(lblPuntaje);
    }
    
    private void btnBlanco(){
        //Boton Blanco
        btnBlanco = new BotonSinFondo();
        btnBlanco.setBounds(520, 320, 100, 100);
        btnBlanco.setIcon(BtnClik);
        lblFondo.add(btnBlanco);
        btnBlanco.addMouseListener(new ManejadorDeEventosMouse());
        btnBlanco.addKeyListener(new ManejadorDeEventosTeclado());

    }
    
    private void lblcontador(){
        //Contador
        lblContador = new JLabel();
        lblContador.setBounds(300, 100, 300, 250);
        lblContador.setBorder(null);
        lblFondo.add(lblContador);
    
    }
    
    private void contPrinci(){
        //Contenedor
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1, 1));
        contPrincipal.add(lblFondo);
    
    }

    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto) throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }

    private class ManejadorDeEventosMouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent evento) {
            //si se da click en el boton balnco
            if (evento.getSource() == btnBlanco) {
                if (puedeJugar && puedeTirar) {
                    if (baldosasIguales(baldosaCambiada)) {
                        tAux = 0;
                        acierto();

                    } else {
                        try {
                            tAux = 0;
                            falloCometido();
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }


    private class ManejadorDeEventosTeclado extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent evento) {
            if (evento.getKeyCode() == 32) {
                if (puedeJugar && puedeTirar) {
                    if (baldosasIguales(baldosaCambiada)) {
                        tAux = 0;
                        acierto();
                    } else {
                        try {
                            tAux = 0;
                            falloCometido();
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }


    private class ManejadorDeEventosTiempo implements ActionListener {
        //Declaracion
        private double t = 0;
        private boolean cuentaRegresiva = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            //se aumenta el tiempo 1 decimo de segundo
            tAux += 0.1;
            t += 0.1;

            if (tAux > 0.5) {
                puedeTirar = true;
            } else {
                if (!inicioJuego) {
                    puedeTirar = false;
                }
            }

            if (t >= 4) {
         //       inicializarVolumen();
                puedeJugar = true;
                puedeTirar = true;
                inicioJuego = false;
            }

            if (cuentaRegresiva) {
                if (t < 4) {
                    if (0 <= t && t < 1) {
                        try {
                            lblContador.setIcon(establecerIcon("/src/imagenes/imgnum1.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (1 <= t && t < 2) {
                        try {
                            lblContador.setIcon(establecerIcon("/src/imagenes/imgnum2.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (2 <= t && t < 3) {
                        try {
                            lblContador.setIcon(establecerIcon("/src/imagenes/imgnum3.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        lblContador.setBounds(200, 100, 300, 250);
                        try {
                            lblContador.setIcon(establecerIcon("/src/imagenes/go.png", 300, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {

                    lblContador.setVisible(false);
                    modificarBaldosas();
                    cuentaRegresiva = false;
                    t = 0;
                }
            } else {
                if (t > logica.getTiempoDeCambio()) {
                    t = 0;

                    if (baldosasIguales(baldosaCambiada)) {
                        try {
                            falloCometido();
                        } catch (IOException ex) {
                            Logger.getLogger(Jugar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {

                        if (baldosaCambiada != -1) {
                            listaBaldosas.get(baldosaCambiada).setBorder(null);
                        }

                        int baldosaACambiar = logica.CambiaBaldosas();
                        listaBaldosas.get(baldosaACambiar).setIcon(imgsBaldosas.getImgBaldosaAleatoria());
                        listaBaldosas.get(baldosaACambiar).setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                        baldosaCambiada = baldosaACambiar;
                    }
                }
            }
        }
    }


    private void inicializarBaldosas() {    
        //Declaracion
        int coordenadas[][] = {{30, 172}, {140, 172}, {440, 180}, {550, 180},
        {292, 7}, {292, 108}, {292, 353}, {292, 252}};

        for (int i = 0; i < 8; i++) {
            JLabel baldosa = new JLabel(imgsBaldosas.getImgBaldosa(i));
            baldosa.setBounds(coordenadas[i][0], coordenadas[i][1],100, 100);
            baldosa.setVisible(false);
            this.listaBaldosas.add(baldosa);
        }
    }
    
    private void inicializarVidas() {
        //Declaracion
        int coordenadas[][] = {{480, 10}, {550, 10}, {620, 10}};
        for (int i = 0; i < 3; i++) {
            LblVida lblVida = new LblVida();
            lblVida.setBounds(coordenadas[i][0], coordenadas[i][1], 50, 50);
            listaVidas.add(lblVida);
        }
    }

    private class LblVida extends JLabel {
        //Constructor
        public LblVida() {
            setOpaque(true);
            setBackground(Color.MAGENTA);
        }

    }

    private boolean baldosasIguales(int baldosaCambiada) {
        //Declaracion
        boolean hayBaldosasIguales = false;
        if (baldosaCambiada != -1) {
            ArrayList<Integer> baldosasEnPantalla = logica.getSaleBaldosa();
            Icon imgBaldosaCambiada = listaBaldosas.get(baldosaCambiada).getIcon();
            for (int i = 0; i < baldosasEnPantalla.size(); i++) {
                if (baldosaCambiada != baldosasEnPantalla.get(i)) {
                    Icon imgBaldosa = listaBaldosas.get(baldosasEnPantalla.get(i)).getIcon();
                    if (imgBaldosaCambiada == imgBaldosa) {
                        hayBaldosasIguales = true;
                    }
                }
            }
        }
        return hayBaldosasIguales;
    }


    private void quitarUnaVida() {
        if (logica.getVidas() > 0) {
            listaVidas.get(logica.getVidas()).setBackground(Color.red);
        }
    }


    private void modificarBaldosas() {
        for (int i = 0; i < 8; i++) {
            if (logica.SaleBaldosa(i)) {
                listaBaldosas.get(i).setVisible(true);
            } else {
                listaBaldosas.get(i).setVisible(false);
            }
            listaBaldosas.get(i).setIcon(imgsBaldosas.getImgBaldosa(i));
        }
    }


    private void falloCometido() throws IOException {
        if (logica.getVidas() != 1) {}
        if (baldosaCambiada != -1) {
            listaBaldosas.get(baldosaCambiada).setBorder(null);
        }
        baldosaCambiada = -1;
        logica.BaldosasIguales();
        quitarUnaVida();
        logica.FallosMas();
        logica.aumCambio();
        if (logica.getVidas() > 0) {
            logica.SaleBaldosaNueva();
            modificarBaldosas();
        } else {
            tiempo.stop();
            dispose();
            JuegoFinal Final = new JuegoFinal(this.logica);


        }

    }

    private void acierto() {
        listaBaldosas.get(baldosaCambiada).setBorder(null);
        logica.PuntosMas();
        logica.PtsMas();
        lblPuntaje.setText("Puntaje: " + logica.getPuntaje());
        logica.AciertosMas();
        logica.rduCambio();
        logica.BaldosaMas();
        logica.SaleBaldosaNueva();
        modificarBaldosas();
        baldosaCambiada = -1;
    }

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
