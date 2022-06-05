/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.Dimension;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Maxen
 */
public class ImageJApple extends JApplet {
    public Image image;
    private JPanel pano;
    private JScrollPane scroll;
    
    
    public ImageJApple(int H, int L) {
        pano=new JPanel();
        scroll = new JScrollPane(pano);
        
        image = new Image(H, L);
        pano.add(image);
        
        this.add(scroll);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return image.getPreferredSize();
    }
}
