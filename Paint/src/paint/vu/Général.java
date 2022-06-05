/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

/**
 *
 * @author Maxen
 */
public class Général extends JPanel {
    public Image image;
    public Interface_ interface_;

    public Général(int H, int L) throws FileNotFoundException {
        this.image = new Image(H, L);
        this.interface_ = new Interface_(image.im);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        gc.gridx=0;
        gc.gridy=0;
        this.add(image,gc);


        gc.gridx=1;
        gc.gridy=0;
        this.add(interface_,gc);
        
    }
    
    
    public void actualiserGeneral() throws FileNotFoundException{
        interface_.actualiseInterface();
        //interface_.vueCalqueaffichage();
    }
    
    
}
