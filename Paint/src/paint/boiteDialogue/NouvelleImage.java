/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.boiteDialogue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import paint.vu.MaFenetre;

/**
 *
 * @author Maxen
 */
public class NouvelleImage extends JDialog implements ActionListener,FocusListener{
    private Dimension tmp = new Dimension(10, 10);
    private JLabel nombrePixel= new JLabel("nombre de Pixel");
    private JLabel nombreHauteur= new JLabel("Hauteur :");
    private JLabel nombreLargeur= new JLabel("Largeur :");
    
    private JTextField temp= new JTextField();
    private JTextField Hauteur= new JTextField("100");
    private JTextField Largeur= new JTextField("100");
    
    private JButton V= new JButton("Valider");
    private JButton C = new JButton("Annuler");
    
   
    
    
    public NouvelleImage(MaFenetre fen){
        super(fen,true);
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
        
        this.setTitle("Nouvelle Image");
        
        
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();
        gc.fill= GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        pano.add(nombrePixel, gc);
        gc.gridx=1;
        gc.gridy=0;
        temp.setEditable(false);
        temp.setBorder(null);
        pano.add(temp, gc);
        
        gc.gridx=0;
        gc.gridy=1;
        pano.add(nombreHauteur, gc);
        gc.gridx=1;
        gc.gridy=1;
        pano.add(Hauteur, gc);
        
        gc.gridx=0;
        gc.gridy=2;
        pano.add(nombreLargeur, gc);
        gc.gridx=1;
        gc.gridy=2;
        pano.add(Largeur, gc);
        
        gc.gridx=0;
        gc.gridy=3;
        C.setBackground(Color.red);
        pano.add(C, gc);
        gc.gridx=1;
        gc.gridy=3;
        V.setBackground(Color.GREEN);
        pano.add(V, gc);
        
        
        this.setContentPane(pano);
        this.pack();
        
        
        V.addActionListener(this);
        C.addActionListener(this);
        
        Hauteur.addFocusListener(this);
        Largeur.addFocusListener(this);
        
    }
    
    public Dimension showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return tmp; //on retourne le résultat
    } 
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==V) {
            tmp= new Dimension(Integer.parseInt(Largeur.getText()) , Integer.parseInt(Hauteur.getText()));
            this.setVisible(false);
        }
        if (e.getSource()==C) {
            tmp=null;
            this.setVisible(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==Hauteur) {
            Hauteur.setText("");
        }
        if (e.getSource()==Largeur) {
            Largeur.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        ;
    }
    
}
