/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.boiteDialogue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import paint.Calque;
import paint.vu.Image;
import paint.vu.MaFenetre;
import static paint.vu.MaFenetre.tailleFenetre;

/**
 *
 * @author Maxen
 */
public class SupprimerCouleur extends JDialog implements ActionListener{
    
    private JLabel choix=new JLabel("Choix de la couleur :");
    private JLabel ilusteationColor;
    
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    
    //private JComboBox Couleur;
    
    private ArrayList<Color> ListeColor;
    private ArrayList<JButton> listeJButton;
    
    private JPanel Couleur;
    
    int choixC=-1;

    public SupprimerCouleur(MaFenetre fen) {
        super(fen,true);
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
        
        Image im=fen.getGénéral().get(0).image;
        
        ListeColor =fen.getGénéral().get(0).interface_.getListeColorJButton();
        listeJButton = new ArrayList<>();
        for (int i = 0; i < fen.getGénéral().get(0).interface_.getListeJButton().size(); i++) {
            JButton tmp= new JButton(fen.getGénéral().get(0).interface_.getListeJButton().get(i).getText());
            tmp.setBackground(fen.getGénéral().get(0).interface_.getListeJButton().get(i).getBackground());
            tmp.setForeground(fen.getGénéral().get(0).interface_.getListeJButton().get(i).getForeground());
            tmp.setBorder(fen.getGénéral().get(0).interface_.getListeJButton().get(i).getBorder());
            tmp.setBorderPainted(false);
            tmp.setPreferredSize(new Dimension(50,50));
            listeJButton.add(tmp);
        }
        
        
        Couleur=new JPanel();
        Couleur.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill=GridBagConstraints.BOTH;
        int y=0;
        for (int i = 0; i < listeJButton.size(); i++) {
            
            int t=getPreferredSize().width/50;
            
            if (i%t==0) {
                y++;
            }
            gc.gridx=i%t;
            gc.gridy=y;
            
            Couleur.add(listeJButton.get(i),gc);
        }
        
        
        this.setLayout(new GridBagLayout());
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=2;
        this.add(Couleur,gc);
        
        gc.gridx=0;
        gc.gridy=y+1;
        gc.gridwidth=1;
        annuler.setBackground(Color.red);
        this.add(annuler,gc);
        
        gc.gridx=1;
        gc.gridy=y+1;
        gc.gridwidth=1;
        valider.setBackground(Color.GREEN);
        this.add(valider,gc);
        this.pack();
        
        
        valider.addActionListener(this);
        annuler.addActionListener(this);
        for (JButton L : listeJButton){
            L.addActionListener(this);
        }
    }
    
    public class ColorItem {
 
		private final Color background;
		private final String value;
 
		public ColorItem(Color color, String background) {
			this.value=background;
			this.background=color;
		}
 
		public Color getBackground() {
			return background;
		}
 
		public String getValue() {
			return value;
		}
 
		@Override
		public String toString() {
			return value;
		}
	}
    
    
    public int showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return choixC; //on retourne le résultat
    } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < listeJButton.size(); i++) {
            listeJButton.get(i).setBorderPainted(false);
            if (e.getSource()==listeJButton.get(i)) {
                choixC=i;
                listeJButton.get(i).setBorderPainted(true);
            }
        }
        
        if (e.getSource()==valider) {
            this.setVisible(false);
        }
        if (e.getSource()==annuler) {
            choixC=-1;
            this.setVisible(false);
        }
    }
    
    @Override
    public final Dimension getPreferredSize() {
        
        return new Dimension(getParent().getPreferredSize().width/4-50,500);
        
    }
}
