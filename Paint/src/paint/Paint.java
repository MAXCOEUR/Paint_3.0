/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paint;

import java.io.FileNotFoundException;
import paint.vu.MaFenetre;

/**
 *
 * @author Maxen
 */
public class Paint {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        
        MaFenetre fen=new MaFenetre();
        fen.setLocation(0, 0);
        fen.setVisible(true);
    }
    
}
