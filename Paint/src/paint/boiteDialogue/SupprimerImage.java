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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import paint.vu.AjouterCalqueRenvoyer;
import paint.vu.Général;
import paint.vu.MaFenetre;

/**
 *
 * @author Maxen
 */
public class SupprimerImage extends JDialog implements ActionListener{
    private JLabel image=new JLabel("Choix de l'image :");
    
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");
    
    private JComboBox JComboboxImage;
    
    private int tmp;

    public SupprimerImage(MaFenetre fen) {
        super(fen,true);
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
        
        ArrayList<Général> GénéralListe =fen.getGénéral();
        
        String[] petStrings = new String[GénéralListe.size()];
        for (int i = 0; i < petStrings.length; i++) {
            petStrings[i]=GénéralListe.get(i).getName();
        }
        JComboboxImage = new JComboBox(petStrings);
        JComboboxImage.setSelectedIndex(MaFenetre.imageSéléctionne);
        
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
        pano.add(JComboboxImage ,gc);
        
        gc.gridx=0;
        gc.gridy=2;
        annuler.setBackground(Color.red);
        pano.add(annuler ,gc);
        
        gc.gridx=1;
        gc.gridy=2;
        valider.setBackground(Color.GREEN);
        pano.add(valider ,gc);
        
        
        
        this.setContentPane(pano);
        this.pack();
        
        
        
        valider.addActionListener(this);
        annuler.addActionListener(this);
    }
    
    public int showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return tmp;
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==valider) {
            tmp=JComboboxImage.getSelectedIndex();
            this.setVisible(false);
        }
        if (e.getSource()==annuler) {
            tmp=-1;
            this.setVisible(false);
        }
    }
    
}
