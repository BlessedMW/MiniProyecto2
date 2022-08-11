/*
Archivo: Menu.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ParaQueSirve extends JFrame {
    //Declaracion
    private String rutaAbsoluta;
    private int anchoV;
    private int largoV;
    private JButton btnSalir;
    private JLabel lblFondo;
    private JLabel lblParaQueSirveTexto;
    private Container contPrincipal;
    //Declaracion
    public ParaQueSirve() throws IOException {
        iniciarComponentes();
        iniciarVentana();
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
        anchoV = 700;
        largoV = 500;
        lblFondo = new JLabel(establecerIcon("/src/imagenes/fondoComoJugar.png", anchoV, largoV));
        //Llamado de metodos de componentes
        btnSalir();
        lblParaQSirve();
        contePrinc();
    }
    
    private void btnSalir(){
        //Boton Salir
        btnSalir = new JButton("Back");
        btnSalir.setBounds(590,5,70,20);
        btnSalir.setForeground(Color.black);
        btnSalir.setFont(new Font("arial",3,10));
        lblFondo.add(btnSalir);

    }
    private void lblParaQSirve(){
        //Label - Para Que Sirve
        lblParaQueSirveTexto = new JLabel("<html>Este juego pone en acción la habilidad para comparar patrones visuales, entrenando ademas la atención a los detalles y la velocidad perceptiva. Estas capacidades son relevantes cuando hay que decidir entre estimulos semejantes y hay que hacerlo de forma rápida, por ejemplo al reconocer fotografias, caras, objetos cotidianos o palabras escritas.<html>");
        lblParaQueSirveTexto.setBounds(10,100,670,300);
        lblParaQueSirveTexto.setForeground(Color.white);
        lblParaQueSirveTexto.setFont(new Font("cooper black",1,20));
        lblFondo.add(lblParaQueSirveTexto);
    }
    private void contePrinc(){
        //Contenedor
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1, 1));
        contPrincipal.add(lblFondo);}
    


    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto) throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen. getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }

    private class BotonSinFondo extends JButton {
        //Constructor
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




