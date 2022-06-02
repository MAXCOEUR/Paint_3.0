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
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import paint.Calque;
import paint.vu.AjouterCalqueRenvoyer;
import paint.vu.Général;
import paint.vu.Image;
import paint.vu.MaFenetre;

/**
 *
 * @author Maxen
 */
public class SupprimerCalque extends JDialog implements ActionListener{
    
    private JLabel image=new JLabel("Choix de l'image :");
    private JLabel choix=new JLabel("Choix du calque :");
    
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    
    private JComboBox pizza;
    private JComboBox JComboboxImage;
    
    private MaFenetre fen;
    
    private AjouterCalqueRenvoyer tmp;
    
    int choixC=-1;

    public SupprimerCalque(MaFenetre fen) {
        super(fen,true);
        this.fen=fen;
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
        
        ArrayList<Général> GénéralListe =fen.getGénéral();
        
        String[] petStrings = new String[GénéralListe.size()];
        for (int i = 0; i < petStrings.length; i++) {
            petStrings[i]=GénéralListe.get(i).getName();
        }
        JComboboxImage = new JComboBox(petStrings);
        
        
        Image im=fen.getGénéral().get(JComboboxImage.getSelectedIndex()).image;
        
        ArrayList<Calque> tmp =im.getCalque();
        
        String[] petStrings2 = new String[tmp.size()];
        for (int i = 0; i < petStrings2.length; i++) {
            petStrings2[i]=tmp.get(i).getName();
        }
        
        pizza = new JComboBox(petStrings2);
        
        this.setTitle("Suppression Calque");
        
        actualiser();
        
    }
    
    public void actualiser(){
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();
        gc.fill= GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        pano.add(image ,gc);
        
        gc.gridx=1;
        gc.gridy=0;
        gc.gridwidth=2;
        pano.add(JComboboxImage ,gc);
        
        gc.gridx=0;
        gc.gridy=1;
        pano.add(choix ,gc);
        
        gc.gridx=1;
        gc.gridy=1;
        gc.gridwidth=2;
        pano.add(pizza ,gc);
        
        gc.gridx=0;
        gc.gridy=2;
        gc.gridwidth=1;
        annuler.setBackground(Color.red);
        pano.add(annuler ,gc);
        
        gc.gridx=1;
        gc.gridy=2;
        gc.gridwidth=1;
        valider.setBackground(Color.GREEN);
        pano.add(valider ,gc);
        
        
        
        this.setContentPane(pano);
        this.pack();
        
        
        
        valider.addActionListener(this);
        annuler.addActionListener(this);
        JComboboxImage.addActionListener(this);
    }
    
    
    public AjouterCalqueRenvoyer showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return tmp= new AjouterCalqueRenvoyer(JComboboxImage.getSelectedIndex()+"", choixC); //on retourne le résultat
    } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==valider) {
            choixC=pizza.getSelectedIndex();
            this.setVisible(false);
        }
        if (e.getSource()==annuler) {
            choixC=-1;
            this.setVisible(false);
        }
        if (e.getSource()==JComboboxImage) {
            
            Image im=fen.getGénéral().get(JComboboxImage.getSelectedIndex()).image;
        
            ArrayList<Calque> tmp =im.getCalque();

            String[] petStrings2 = new String[tmp.size()];
            for (int i = 0; i < petStrings2.length; i++) {
                petStrings2[i]=tmp.get(i).getName();
            }
            pizza = new JComboBox(petStrings2);
            actualiser();
        }
    }
    
}
