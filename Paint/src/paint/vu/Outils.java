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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import paint.vu.Image.ImageComp;

/**
 *
 * @author Maxen
 */
public class Outils extends JPanel implements ActionListener{
    
    ArrayList<JButton> ListeOutil= new ArrayList<>();
    
    JLabel taillePixel = new JLabel("Taille des pixels :");
    JLabel vuTaillePixel = new JLabel(Image.taillePixel+"");
    JButton moinPixel = new JButton("-");
    JButton plusPixel = new JButton("+");
    
    JLabel taillePinceau = new JLabel("Taille du Pinceau :");
    JLabel vuTaillePinceau = new JLabel(Image.taillePinceau+"");
    JButton moinPinceau = new JButton("-");
    JButton plusPinceau = new JButton("+");
    
    ImageIcon bucket = new ImageIcon(new ImageIcon("donnee\\image\\bucket.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon cercle = new ImageIcon(new ImageIcon("donnee\\image\\cercle.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon Horizo = new ImageIcon(new ImageIcon("donnee\\image\\Horizo.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon pinceau = new ImageIcon(new ImageIcon("donnee\\image\\pinceau.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon rect = new ImageIcon(new ImageIcon("donnee\\image\\rect.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon Ver = new ImageIcon(new ImageIcon("donnee\\image\\Ver.png").getImage().getScaledInstance(15, 15, 20));
    
    ImageComp im;
    
    
    public Outils(ImageComp im) {
        this.im=im;
        ListeOutil.add(new JButton(pinceau));
        ListeOutil.add(new JButton(Ver));
        ListeOutil.add(new JButton(Horizo));
        ListeOutil.add(new JButton(cercle));
        ListeOutil.add(new JButton(bucket));
        ListeOutil.add(new JButton(rect));
        
        for(JButton b: ListeOutil){
            b.setBackground(Color.WHITE);
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
            b.setBorderPainted(false);
            b.setRolloverEnabled(false);
            b.setPreferredSize(new Dimension(50,50));
        }
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=2;
        this.add(taillePixel,gc);
        gc.gridx=2;
        gc.gridy=0;
        gc.gridwidth=1;
        this.add(moinPixel,gc);
        gc.gridx=3;
        this.add(vuTaillePixel,gc);
        gc.gridx=4;
        this.add(plusPixel,gc);
        
        gc.gridx=0;
        gc.gridy=1;
        gc.gridwidth=2;
        this.add(taillePinceau,gc);
        gc.gridx=2;
        gc.gridy=1;
        gc.gridwidth=1;
        this.add(moinPinceau,gc);
        gc.gridx=3;
        this.add(vuTaillePinceau,gc);
        gc.gridx=4;
        this.add(plusPinceau,gc);
        
        gc.fill=GridBagConstraints.BOTH;
        for (int i = 0; i < ListeOutil.size(); i++) {
            gc.gridx=i;
            gc.gridy=2;
            this.add(ListeOutil.get(i),gc);
        }
        
        
        for(JButton b: ListeOutil){
            b.setBackground(Color.WHITE);
            b.addActionListener(this);
        }
        
        actualiserChoisie();
                
        moinPinceau.addActionListener(this);
        plusPinceau.addActionListener(this);
        moinPixel.addActionListener(this);
        plusPixel.addActionListener(this);
    }
    
    
    public void actualiserChoisie(){
        for (int i = 0; i < ListeOutil.size(); i++) {
            ListeOutil.get(i).setBorderPainted(false);
            if (i==Image.ModeChoisie) {
                ListeOutil.get(i).setBorderPainted(true);
            }
        }
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MaFenetre.tailleFenetre.width/4-60, (MaFenetre.tailleFenetre.height-100)/3-10);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < ListeOutil.size(); i++) {
            
            if (e.getSource()==ListeOutil.get(i)) {
                Image.ModeChoisie=i;
                }
            }   
            actualiserChoisie();
        if (e.getSource()==moinPinceau) {
            if (Image.taillePinceau-1>0) {
                Image.taillePinceau-=1;
                vuTaillePinceau.setText(Image.taillePinceau+"");
            }
        }
        if (e.getSource()==plusPinceau) {
            Image.taillePinceau+=1;
            vuTaillePinceau.setText(Image.taillePinceau+"");
        }
        
        if (e.getSource()==moinPixel) {
            if (Image.taillePixel-1>0) {
                Image.taillePixel-=1;
                vuTaillePixel.setText(Image.taillePixel+"");
                im.repaint();
            }
        }
        if (e.getSource()==plusPixel) {
            Image.taillePixel+=1;
            vuTaillePixel.setText(Image.taillePixel+"");
            im.repaint();
        }
    }
    
}
