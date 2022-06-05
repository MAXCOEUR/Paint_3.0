/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import paint.Calque;
import paint.vu.Image.ImageComp;

/**
 *
 * @author Maxen
 */
public class VueCalque extends JApplet implements ActionListener {
    private ArrayList<Calque> calque;
    private ImageComp im;
    public JPanel pano= new JPanel();
    
    private ArrayList<JButton> BoutonUp = new ArrayList<>();
    private ArrayList<JButton> BoutonDown= new ArrayList<>();
    private ArrayList<JButton> BoutonVisible= new ArrayList<>();
    private ArrayList<JButton> BoutonChoix= new ArrayList<>();
    
    private ArrayList<JLabel> NomCalque= new ArrayList<>();
    
    private JScrollPane scroll;
    
    
    GridBagConstraints gc = new GridBagConstraints();
    
    ImageIcon UP = new ImageIcon(new ImageIcon("donnee\\image\\haut.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon DOWN = new ImageIcon(new ImageIcon("donnee\\image\\bas.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon YFerme = new ImageIcon(new ImageIcon("donnee\\image\\yeux-ferme.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon YOuvert = new ImageIcon(new ImageIcon("donnee\\image\\yeux-ouvert.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon CaseVide = new ImageIcon(new ImageIcon("donnee\\image\\case-vide.png").getImage().getScaledInstance(15, 15, 20));
    ImageIcon CaseCocher = new ImageIcon(new ImageIcon("donnee\\image\\case-cocher.png").getImage().getScaledInstance(15, 15, 20));
    
    public VueCalque(ImageComp i) {
        calque=i.getCalque();
        im=i;        
        scroll = new JScrollPane(pano);
        
        
        affichage();
        
        
        //this.add(pano);
        //this.setBorder(new LineBorder(Color.yellow));
        this.add(scroll);
    }
    
    
    public void affichage(){
        BoutonDown.removeAll(BoutonDown);
        BoutonUp.removeAll(BoutonUp);
        BoutonVisible.removeAll(BoutonVisible);
        BoutonChoix.removeAll(BoutonChoix);
        NomCalque.removeAll(NomCalque);
        pano.removeAll();
        pano.setLayout(new GridBagLayout());
        
        for (int i = 0; i < calque.size(); i++) {
            BoutonUp.add(new JButton(UP));
            BoutonUp.get(i).setContentAreaFilled(false);
            BoutonDown.add(new JButton(DOWN));
            BoutonDown.get(i).setContentAreaFilled(false);

            
            
            if (calque.get(i).getVisible()==true) {
                BoutonVisible.add(new JButton(YOuvert));
            }
            else{
                BoutonVisible.add(new JButton(YFerme));
            }
            BoutonVisible.get(i).setContentAreaFilled(false);

            if (im.getCalqueChoisie()==i) {
                BoutonChoix.add(new JButton(CaseCocher));
            }
            else{
                BoutonChoix.add(new JButton(CaseVide));
            }
            BoutonChoix.get(i).setContentAreaFilled(false);


            NomCalque.add(new JLabel(calque.get(i).getName()));

            BoutonDown.get(i).addActionListener(this);
            BoutonUp.get(i).addActionListener(this);
            BoutonVisible.get(i).addActionListener(this);
            BoutonChoix.get(i).addActionListener(this);

            gc.gridx=0;
            gc.gridy=i;
            pano.add(BoutonChoix.get(i),gc);
            gc.gridx=1;
            gc.gridy=i;
            pano.add(BoutonVisible.get(i),gc);
            gc.gridx=2;
            gc.gridy=i;
            pano.add(NomCalque.get(i),gc);
            gc.gridx=3;
            gc.gridy=i;
            pano.add(BoutonUp.get(i),gc);
            gc.gridx=4;
            gc.gridy=i;
            pano.add(BoutonDown.get(i),gc);
        }
        
        BoutonUp.get(0).setEnabled(false);
        BoutonDown.get(BoutonDown.size()-1).setEnabled(false);
        pano.updateUI();
    }
    
    
    
    

    
    
    @Override
    public final Dimension getPreferredSize() {
        Dimension d = new Dimension(MaFenetre.tailleFenetre.width/4-60, (MaFenetre.tailleFenetre.height-100)/3-10);
        return d ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < BoutonUp.size(); i++) {
            if (e.getSource()==BoutonUp.get(i)) {
                Calque tmp=calque.get(i-1);
                calque.remove(i-1);
                
                calque.add(i, tmp);
                if (i==im.getCalqueChoisie()||i-1==im.getCalqueChoisie()) {
                    if (i-1==im.getCalqueChoisie()) {
                    im.setCalqueChoisie(i);
                    }
                    else{
                        im.setCalqueChoisie(i-1);
                    }
                }
                
                
                affichage();
                im.repaint();
            }
            if (e.getSource()==BoutonDown.get(i)){
                Calque tmp=calque.get(i+1);
                calque.remove(i+1);
                
                calque.add(i, tmp);
                 if (i==im.getCalqueChoisie()||i+1==im.getCalqueChoisie()) {
                    if (i+1==im.getCalqueChoisie()) {
                    im.setCalqueChoisie(i);
                    }
                    else{
                        im.setCalqueChoisie(i+1);
                    }
                }
                affichage();
                im.repaint();
            }
            if (e.getSource()==BoutonVisible.get(i)) {
                calque.get(i).setVisible(!calque.get(i).getVisible());
                if (calque.get(i).getVisible()==true) {
                    BoutonVisible.get(i).setIcon(YOuvert);
                }
                else{
                    BoutonVisible.get(i).setIcon(YFerme);
                }
                im.repaint();
            }
            if (e.getSource()==BoutonChoix.get(i)) {
                for(JButton B:BoutonChoix){
                    B.setIcon(CaseVide);
                }
                im.setCalqueChoisie(i);
                BoutonChoix.get(i).setIcon(CaseCocher);
            }
        }
        
    }
    
}
