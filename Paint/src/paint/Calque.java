/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import paint.vu.Image;

/**
 *
 * @author Maxen
 */
public class Calque {
    private ArrayList<ArrayList<Color>> pixel;
    private String name;
    private Boolean visible=true;

    public Calque() {
        pixel= new ArrayList();
        name="par default";
    }
    
    /**
     * constructeur null de taille hauteur par largeur
     * @param hauteur hauteur
     * @param largeur largeur
     */
    public Calque(int hauteur,int largeur,String n,Image im) {
        pixel= new ArrayList();
        for (int i = 0; i < hauteur; i++) {
            pixel.add(new ArrayList() );
        }
        
        for(ArrayList<Color> A:pixel){
            for (int j = 0; j < largeur; j++) {
                A.add(new Color(255,255,255,0));
            }
        }
        if (n.equals("")) {
            name=im.nbrCalque+"";
            im.nbrCalque++;
        }
        else{
            name=n;
            System.out.println("no");
        }
        
    }
    
    public Calque(int hauteur,int largeur) {
        pixel= new ArrayList();
        for (int i = 0; i < hauteur; i++) {
            pixel.add(new ArrayList() );
        }
        
        for(ArrayList<Color> A:pixel){
            for (int j = 0; j < largeur; j++) {
                A.add(new Color(255,255,255,0));
            }
        }
        name="par default";
    }
    
    public void reset(){
        for(ArrayList<Color> AC: pixel){
            for(Color C: AC){
                C=new Color(255, 255, 255, 0);
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public ArrayList<ArrayList<Color>> getPixel() {
        return pixel;
    }
    
    
    
    
    
    /**
     * retourne class couleur en fonction dune cooredonne
     * @param i la hauteur voulue 
     * @param j la largeur voulue
     * @return CouleurRGBA
     */
    public Color getPixelCouleurRGBA(int i, int j){
        return pixel.get(i).get(j);
    }
    
    /**
     * changer la couleur en i j
     * @param C la couleur voulue en CouleurRGBA
     * @param i la hauteur i voulu int
     * @param j la largeur j voulue int
     */
    public void setPixelCouleurRGBA(Color C,int i,int j){
        try {
            ArrayList<Color> tmp = pixel.get(i);
            tmp.set(j, C);
            pixel.set(i, tmp);
        } catch (Exception e) {
        }
    }


    @Override
    public String toString() {
        String tmp="";
        for (ArrayList<Color> A:pixel) {
            tmp+=A.toString();
            tmp+="\n";
        }
        return tmp;
    }
    
    
    
    
}
