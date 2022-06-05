/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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

/**
 *
 * @author Maxen
 */
public class Couleur extends JApplet implements ActionListener{
    
    public ArrayList<JButton> listeJButton=new ArrayList<>();
    public ArrayList<Color> listeColorJButton=new ArrayList<>();
    private JScrollPane scroll;
    
    private JPanel pano= new JPanel();

    public Couleur() throws HeadlessException, FileNotFoundException {
        scroll = new JScrollPane(pano);
        
        mettreButton();
        actualiserChoisie();
        
        this.add(scroll);
    }
    
    
    
    
    public void mettreButton() throws FileNotFoundException{
        listeJButton.clear();
        listeColorJButton.clear();
        
        pano.removeAll();
        pano.setLayout(new GridBagLayout());
        
        
        
        
        Scanner obj = new Scanner(new File("donnee\\couleur.txt"));
        
        
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill=GridBagConstraints.BOTH;
        int t=getPreferredSize().width/50-1;
        int x=0;
        int y=0;
        while (obj.hasNextInt()) {
            int R, G, B, A;
            R=obj.nextInt();
            G=obj.nextInt();
            B=obj.nextInt();
            A=obj.nextInt();
            JButton tmp=new JButton();
            tmp.setForeground(colorInverse(new Color(R, G, B)));
            tmp.setText(A+"");
            tmp.setBackground(new Color(R,G,B));
            
            
            
            if (x%t==0) {
                y++;
            }
            gc.gridx=x%t;
            gc.gridy=y;
            tmp.setPreferredSize(new Dimension(50,50));
            tmp.setBorder(BorderFactory.createLineBorder(tmp.getForeground(),5));
            tmp.setBorderPainted(false);
            
            tmp.setRolloverEnabled(false);
            listeJButton.add(tmp);
            listeColorJButton.add(new Color(R, G, B, A));
            pano.add(tmp,gc);
            x++;
        }
        
        
        for (JButton L : listeJButton){
            L.addActionListener(this);
        }
        
        Image.pinceau=listeColorJButton.get(0);
        listeJButton.get(0).setBorderPainted(true);
        
        pano.updateUI();
        
    }
    
    private Color colorInverse (Color c){
       return new Color(Math.abs(c.getRed()-255),Math.abs(c.getGreen()-255),Math.abs(c.getBlue()-255));
    }
    
    public void actualiserChoisie(){
        for (int i = 0; i < listeJButton.size(); i++) {
            listeJButton.get(i).setBorderPainted(false);
            if (i==Image.couleurChoisie) {
                System.out.println(i);
                listeJButton.get(i).setBorderPainted(true);
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MaFenetre.tailleFenetre.width/4-60, (MaFenetre.tailleFenetre.height-100)/3-10);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < listeJButton.size(); i++) {
            if (e.getSource()==listeJButton.get(i)) {
                Image.pinceau=listeColorJButton.get(i);
                Image.couleurChoisie=i;
            }
        }
        actualiserChoisie();
    }
    
}
