/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.boiteDialogue;

import paint.vu.AjouterCalqueRenvoyer;
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
import javax.swing.JTextField;
import paint.vu.Général;
import paint.vu.MaFenetre;

/**
 *
 * @author Maxen
 */
public class AjouterCalque extends JDialog implements ActionListener{
    
    AjouterCalqueRenvoyer retourner;
            
    private JLabel nomDuCalque = new JLabel("Nom du Calque :");
    private JLabel numeroCalque = new JLabel("Numero du Calque :");
    
    private JTextField nomJT = new JTextField();
    private JComboBox Image;
    
    private JButton valider = new JButton("Valider");
    private JButton annuler = new JButton("Annuler");

    public AjouterCalque(MaFenetre fen) {
        super(fen,true);
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
    
        ArrayList<Général> tmp =fen.getGénéral();
        
        String[] petStrings = new String[tmp.size()];
        for (int i = 0; i < petStrings.length; i++) {
            petStrings[i]=tmp.get(i).getName();
        }
        Image = new JComboBox(petStrings);
        
        
        
        this.setTitle("Ajouter Calque");
        
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();
        gc.fill= GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        pano.add(nomDuCalque ,gc);
        
        gc.gridx=1;
        gc.gridy=0;
        pano.add(nomJT ,gc);
        
        gc.gridx=0;
        gc.gridy=1;
        pano.add(numeroCalque ,gc);
        
        gc.gridx=1;
        gc.gridy=1;
        pano.add(Image ,gc);
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==valider) {
            retourner = new AjouterCalqueRenvoyer(nomJT.getText(), Image.getSelectedIndex());
            this.setVisible(false);
        }
        if (e.getSource()==annuler) {
            this.setVisible(false);
        }
    }
    
    public AjouterCalqueRenvoyer showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return retourner; //on retourne le résultat
    } 
    
}


