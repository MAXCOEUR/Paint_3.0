/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Maxen
 */
public class Interface_ extends JPanel {

    Couleur Couleur;
    VueCalque vueCalque;
    Outils vueOutils;
    
    

    public Interface_(Image im) throws FileNotFoundException {
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        this.setLayout(new GridBagLayout());
        
        vueCalque = new VueCalque(im);
        
        Couleur=new Couleur();  
        
        vueOutils= new Outils();
        
        actualiseInterface();
    }

    public ArrayList<Color> getListeColorJButton() {
        return Couleur.listeColorJButton;
    }

    public ArrayList<JButton> getListeJButton() {
        return Couleur.listeJButton;
    }
    
    
    
    public void actualiseInterface() throws FileNotFoundException{
        GridBagConstraints gc = new GridBagConstraints();
        Couleur.mettreButton();
        
        gc.gridx=0;
        gc.gridy=0;
        this.add(vueOutils,gc);
        
        
        gc.gridx=0;
        gc.gridy=1;
        this.add(Couleur,gc);
        
        gc.gridx=0;
        gc.gridy=2;
        this.add(vueCalque,gc);
        vueCalque.affichage();
        
        
    }
    
    
    
    
    
    
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MaFenetre.tailleFenetre.width/4-50, MaFenetre.tailleFenetre.height-100);
    }
    
    
    
    
    
}
