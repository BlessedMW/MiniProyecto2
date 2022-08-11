/*
Archivo: Menu.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;



import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.logging.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

public class Menu extends JFrame {
    //Declaracion
    private int anchoV = 1068;
    private int largoV = 700;
    private ImageIcon imgFondo;
    private JLabel lblFondo;
    private JButton btnJugar;
    private JButton btnParaQueSirve;
    private JButton btnComoJugar;
    private Container contPrincipal;
    private Clip clip;
    private boolean pasoVentana;
    private int opcion;
    private Timer tiempo;
    private String rutaAbsoluta;
    //Constructor
    public Menu(int opcion) throws IOException {
        iniciarComponentes();
        iniciarVentana();
        this.opcion = opcion;
        this.pasoVentana = false;
    }

    private void iniciarVentana() throws IOException {
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
        imgFondo = establecerIcon("/src/imagenes/fondoMenu.png",anchoV, largoV);
        //Llamado de metodos de componentes
        CrearLbls();
        CrearTiempo();
        BotonJuega();
        BotonComoJuega();
        BotonParaQSirve();
        ContenPrincipal();
    }

    public void CrearLbls(){
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);
                
    }
    
    public void CrearTiempo(){
        tiempo = new Timer(100, new ManejadorDeEventosTiempo());
        tiempo.start();}
    
    public void BotonJuega(){
        btnJugar = new BotonSinFondo();
        btnJugar.setBounds(350, 500, 250, 150);
        lblFondo.add(btnJugar);
        btnJugar.addMouseListener(new ManejadorDeEventos());
    }
    
    public void BotonComoJuega(){
        btnComoJugar = new BotonSinFondo();
        btnComoJugar.setBounds(0, 500, 500, 300);
        lblFondo.add(btnComoJugar);
        btnComoJugar.addMouseListener(new ManejadorDeEventos());
    
    }
    
    public void BotonParaQSirve(){
        btnParaQueSirve = new BotonSinFondo();
        btnParaQueSirve.setBounds(800, 500, 400, 300);
        lblFondo.add(btnParaQueSirve);
        btnParaQueSirve.addMouseListener(new ManejadorDeEventos());
    }
    
    public void ContenPrincipal(){
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        contPrincipal.add(lblFondo);
    }
    
    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }

    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnJugar) {
                dispose();
                try {
                    pasoVentana = true;
                    if (clip != null) {
                        clip.stop();
                    }
                    Jugar EmpiezaJuego = new Jugar();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnComoJugar) {
                dispose();

                try {
                    if (clip != null) {
                        clip.stop();
                    }
                    pasoVentana = true;
                    ComoJugar ComoSeJuega = new ComoJugar();

                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnParaQueSirve) {
                dispose();
                try {
                    if (clip != null) {
                        clip.stop();
                    }
                    pasoVentana = true;
                    ParaQueSirve Leer = new ParaQueSirve();
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.
                            getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private class ManejadorDeEventosTiempo implements ActionListener {

        private double t = 0;


        @Override
        public void actionPerformed(ActionEvent e) {

            t += 0.1;

        }
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
