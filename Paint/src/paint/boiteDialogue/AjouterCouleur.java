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
import java.awt.event.MouseListener;
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
public class AjouterCouleur extends JDialog implements ActionListener, FocusListener{
    
    private JLabel RL,GL,BL,AL,zero;
    private JTextField R,G,B,A;
    private JTextField temp;
    
    private JButton V,C;
    
    private Color tmp;
    
    public AjouterCouleur(MaFenetre fen) {
        super(fen,true);
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
        
        RL = new JLabel("Rouge :");
        GL = new JLabel("Vert :");
        BL = new JLabel("Bleu :");
        AL = new JLabel("Alpha :");
        zero = new JLabel("0-255");
        
        temp = new JTextField("");
        
        R = new JTextField("0-255");
        G = new JTextField("0-255");
        B = new JTextField("0-255");
        A = new JTextField("255");
        
        V = new JButton("Valider");
        C = new JButton("Annuler");
        
        this.setTitle("Ajout de couleur");
        
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();
        gc.fill= GridBagConstraints.BOTH;
        
        gc.gridx=0;
        gc.gridy=0;
        temp.setEditable(false);
        temp.setBorder(null);
        pano.add(temp, gc);
        
        gc.gridx=1;
        gc.gridy=0;
        pano.add(zero, gc);
        
        gc.gridx=0;
        gc.gridy=1;
        pano.add(RL, gc);
        gc.gridx=1;
        gc.gridy=1;
        pano.add(R, gc);
        
        gc.gridx=0;
        gc.gridy=2;
        pano.add(GL, gc);
        gc.gridx=1;
        gc.gridy=2;
        pano.add(G, gc);
        
        gc.gridx=0;
        gc.gridy=3;
        pano.add(BL, gc);
        gc.gridx=1;
        gc.gridy=3;
        pano.add(B, gc);
        
        gc.gridx=0;
        gc.gridy=4;
        pano.add(AL, gc);
        gc.gridx=1;
        gc.gridy=4;
        pano.add(A, gc);
        
        gc.gridx=0;
        gc.gridy=5;
        C.setBackground(Color.red);
        pano.add(C, gc);
        gc.gridx=1;
        gc.gridy=5;
        V.setBackground(Color.GREEN);
        pano.add(V, gc);
        
        
        this.setContentPane(pano);
        this.pack();
        
        
        V.addActionListener(this);
        C.addActionListener(this);
        
        R.addFocusListener(this);
        G.addFocusListener(this);
        B.addFocusListener(this);
        A.addFocusListener(this);
    }

    public Color showDialog(){ 
        this.setVisible(true); //on ouvre la fenêtre
        return tmp;
    } 
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==V) {
            tmp= new Color(Integer.parseInt(R.getText()), Integer.parseInt(G.getText()), Integer.parseInt(B.getText()), Integer.parseInt(A.getText()));
            this.setVisible(false);
        }
        if (e.getSource()==C) {
            tmp= null;
            this.setVisible(false);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==R) {
            R.setText("");
        }
        if (e.getSource()==G) {
            G.setText("");
        }
        if (e.getSource()==B) {
            B.setText("");
        }
        if (e.getSource()==A) {
            A.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        ;
    }
    
}
