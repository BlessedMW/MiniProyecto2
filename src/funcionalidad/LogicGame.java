/*
Archivo: LogicGame.java
Profesor: Luis Yovany Romo Portilla.
Miniproyecto 2 - FPOE Gr. 01.
Autor: 
- Jean Martinez <jean.morcillo@correounivalle.edu.co>.
Licencia: GNU-GPL.
*/

package funcionalidad;

import java.util.ArrayList;

public class LogicGame {
    //Declaracion
    private int numBaldosasX;
    private ArrayList<Integer> BaldosaOcult;
    private ArrayList<Integer> BaldosaPantalla;
    private int Vidas;
    private int Puntos;
    private int PtsMas;
    private double TimeVaria;
    private int Aciertos;
    private int Fallos;
    //Constructor
    public LogicGame(){
        numBaldosasX = 3;
        Vidas = 3;
        Puntos = 0;
        PtsMas = 10;
        TimeVaria = 2;
        Aciertos = 0;
        Fallos = 0;
        BaldosaOcult = new ArrayList<>();
        //Ciclo For
        for(int i = 0; i<8; i++){
            BaldosaOcult.add(i);
        }
        
        BaldosaPantalla = new ArrayList<>();
        //Ciclo For
        for(int i = 0; i<numBaldosasX; i++){
            int cualBaldosa = (int) (Math.random() * BaldosaOcult.size());
            BaldosaPantalla.add(BaldosaOcult.get(cualBaldosa));
            BaldosaOcult.remove(cualBaldosa);
        }
        
    }
    
    public boolean SaleBaldosa(int cualNumero){
        return BaldosaPantalla.indexOf(cualNumero) != -1;
    }
    
    public int CambiaBaldosas(){
        return BaldosaPantalla.get((int) (Math.random() *
                BaldosaPantalla.size()));
    }
    
    public ArrayList<Integer> getSaleBaldosa(){
        return BaldosaPantalla;
    }
    
    public void BaldosasIguales(){
        Vidas--;
        if(numBaldosasX > 3) {
            numBaldosasX--;
        }
        if(PtsMas > 10) {
            PtsMas /= 2;
        }
    }
    
    public int getVidas() {
        return Vidas;
    }
    
    public void rnBaldosaOcult(){
        BaldosaOcult.clear();
        for(int i = 0; i<8; i++){
            BaldosaOcult.add(i);
        }
    }
    
    public void SaleBaldosaNueva() {
        rnBaldosaOcult();
        
        BaldosaPantalla.clear();
        for(int i = 0; i<numBaldosasX; i++){
            int cualBaldosa = (int) (Math.random() * BaldosaOcult.size());
            BaldosaPantalla.add(BaldosaOcult.get(cualBaldosa));
            BaldosaOcult.remove(cualBaldosa);
        }
        
    }

    
    public void BaldosaMas(){
        if(numBaldosasX < 8) {
            numBaldosasX++;
        }
        
    }
    
    public void PuntosMas(){
        Puntos += PtsMas;
    }
    
   
    public void PtsMas(){
        PtsMas *= 2;
    }
    
    public int getPuntaje() {
        return Puntos;
    }
    
    public void rduCambio() {
        if(TimeVaria > 1){
            TimeVaria -= 0.3;
        }
        
    }
    
    
    public void aumCambio() {
        if(TimeVaria < 2){
            TimeVaria += 0.3;
        }
        
    }
    
    public double getTiempoDeCambio() {
        return TimeVaria;
    }
    
    public int getAciertos() {
        return Aciertos;
    }
    
    public int getFallos() {
        return Fallos;
    }

    public void AciertosMas() {
        Aciertos++;
    }

    public void FallosMas() {
        Fallos++;
    }
}
