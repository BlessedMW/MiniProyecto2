/*
Archivo: JuegoFinal.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import funcionalidad.LogicGame;

public class JuegoFinal extends JFrame {
    //Declaracion
    private String rutaAbsoluta;
    private int ancho;
    private int largo;
    private JButton btnJugar;
    private JLabel lblFondo;
    private JLabel lblPuntajeLogic;
    private JLabel lblAciertosLogic;
    private JLabel lblFallosLogic;
    private Container contPrincipal;
    private LogicGame logica;
    //Constructor
    public JuegoFinal(LogicGame logica) throws IOException {
        //Declaracion
        ancho = 700;
        largo = 500;
        this.logica = logica;
        //Ajustes Ventana
        setSize(ancho, largo);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2_Game");
        setResizable(false);
        inciarComponentes();
    }

    private void inciarComponentes() throws IOException {
        //Declaracion y ajustes
        rutaAbsoluta = new File("").getAbsolutePath();
        lblFondo = new JLabel(establecerIcon("/src/imagenes/fondoFinal.png",ancho, largo));
        lblFondo.setBounds(0, 0, ancho, largo);
        //Llamado de metodos de componentes
        PuntajeLogic();
        AciertoLogic();
        FalloLogic();
        BtnJugar();
        ContePrincipal();

    }
    
    
    private void PuntajeLogic(){
        //Puntaje
        lblPuntajeLogic = new JLabel(logica.getPuntaje() + "");
        lblPuntajeLogic.setForeground(Color.yellow);
        lblPuntajeLogic.setFont(new Font("cooper black",1,45));
        lblPuntajeLogic.setBounds(420, 145, 220, 50); 
        lblFondo.add(lblPuntajeLogic);
    }
    
    
    private void AciertoLogic(){
        //Acierto
        lblAciertosLogic = new JLabel(logica.getAciertos() + "");
        lblAciertosLogic.setForeground(Color.yellow);
        lblAciertosLogic.setFont(new Font("cooper black",1,45));
        lblAciertosLogic.setBounds(420, 230, 220, 50); 
        lblFondo.add(lblAciertosLogic);
    }

    
    private void FalloLogic(){
        //Fallo
        lblFallosLogic = new JLabel(logica.getFallos() + "");
        lblFallosLogic.setForeground(Color.yellow);
        lblFallosLogic.setFont(new Font("cooper black",1,45));
        lblFallosLogic.setBounds(420, 310, 220, 50); 
        lblFondo.add(lblFallosLogic);
    }

    
    private void BtnJugar(){
        //Jugar
        btnJugar = new JButton("");
        btnJugar.setBounds(200, 380, 300, 75);
        btnJugar.setRolloverEnabled(true);
        btnJugar.setFocusPainted(false);
        btnJugar.setBorderPainted(false);
        btnJugar.setContentAreaFilled(false);
        btnJugar.addActionListener(new ManejadorDeEventos());
        lblFondo.add(btnJugar);
    }

    
    private void ContePrincipal(){
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

    private class ManejadorDeEventos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                Menu Inicio = new Menu(0);
            } catch (IOException ex) {
                Logger.getLogger(JuegoFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
