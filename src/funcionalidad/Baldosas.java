/*
Archivo: Baldosas.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.io.File;

public class Baldosas {
    //Declaracion
    private ArrayList<ImageIcon> listBaldosas;
    private final int altoBaldosa = 100;
    private final int anchoBaldosa = 100;
    //Constructor
    public Baldosas() throws IOException {
        listBaldosas = new ArrayList<>();
        apareceBaldosas();
    }

    private void apareceBaldosas() throws IOException {
        //Declaracion
        ImageIcon baldosa;
        String rutaAux = "\\src\\diseños\\numero.png";
        //Ciclo For
        for (int i = 1; i <= 16; i++) {
            String CaminoDif = rutaAux.replace("numero",i+"");
            baldosa = establecerIcono(CaminoDif, anchoBaldosa, altoBaldosa);
            listBaldosas.add(baldosa);
        }
    }

    private ImageIcon establecerIcono(String rutaArchivo, int ancho, int alto) throws IOException {
        //Declaracion
        String rutaAbsoluta = new File("").getAbsolutePath();
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }
    
    public ImageIcon getImgBaldosa(int xBaldosa){
        return listBaldosas.get(xBaldosa);
    }
    
    public ImageIcon getImgBaldosaAleatoria(){
        return listBaldosas.get((int) (Math.random()*listBaldosas.size()));
    }

}
