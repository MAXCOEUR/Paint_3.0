/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import paint.boiteDialogue.SupprimerCalque;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import paint.boiteDialogue.AjouterCalque;
import paint.boiteDialogue.AjouterCouleur;
import paint.boiteDialogue.NouvelleImage;
import paint.boiteDialogue.Save;
import paint.boiteDialogue.SupprimerCouleur;
import paint.boiteDialogue.SupprimerImage;

/**
 *
 * @author Maxen
 */
public class MaFenetre extends JFrame implements ActionListener,ChangeListener{
    
    private JMenuBar menuBar =new JMenuBar();
    
    
    private JMenu menuFichier = new JMenu("Fichier");
    private JMenuItem menuItemExporter = new JMenuItem("Exporter : svg");
    private JMenuItem menuItemNouveauImage = new JMenuItem("Nouvelle image");
    private JMenuItem menuItemSupprimerImage = new JMenuItem("Supprimer une image");
    
    private JMenu menuCalque = new JMenu("Calque");
    private JMenuItem menuItemAjouterCalque = new JMenuItem("Ajouter");
    private JMenuItem menuItemSupprimerCalque = new JMenuItem("Supprimer");
    
    private JMenu menuCouleur = new JMenu("Couleur");
    private JMenuItem menuItemAjouterCouleur = new JMenuItem("Ajouter");
    private JMenuItem menuItemSupprimerCouleur = new JMenuItem("Supprimer");
    
    
    public static Dimension tailleFenetre;
    public static int imageSéléctionne=0;
    
    JTabbedPane tp=new JTabbedPane();
    
    ArrayList<Général> Général = new ArrayList<>();
    JPanel Fenetre;
    
    //Image image;
    JTabbedPane tabbedPane;

    public ArrayList<Général> getGénéral() {
        return Général;
    }
    
    
    
    
    

    public MaFenetre() throws FileNotFoundException {
        
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screen.width/2-getPreferredSize().width/2,screen.height/2-getPreferredSize().height/2);
    
        this.setJMenuBar(menuBar);
            menuBar.add(menuFichier);
                menuFichier.add(menuItemExporter);
                menuFichier.add(menuItemNouveauImage);
                menuFichier.add(menuItemSupprimerImage);
            menuBar.add(menuCalque);
                menuCalque.add(menuItemAjouterCalque);
                menuCalque.add(menuItemSupprimerCalque);
            menuBar.add(menuCouleur);
                menuCouleur.add(menuItemAjouterCouleur);
                menuCouleur.add(menuItemSupprimerCouleur);
                
        tailleFenetre = getPreferredSize();
        
        
        
        
        
        this.setTitle("Paint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //on place les éléments
        
        //gc.fill=GridBagConstraints.BOTH;
        //on place le composant perso (on le gère comme tout autre composant)
        
        
        ajoutImage();
        
        
        
        //mettre les acouteur ici
        
        
        
        
        
        this.pack();
        
        
        
        
        tp.addChangeListener(this);
        menuItemExporter.addActionListener(this);
        menuItemAjouterCalque.addActionListener(this);
        menuItemSupprimerCalque.addActionListener(this);
        menuItemAjouterCouleur.addActionListener(this);
        menuItemSupprimerCouleur.addActionListener(this);
        menuItemNouveauImage.addActionListener(this);
        menuItemSupprimerImage.addActionListener(this);
    }
    
    
    
    public void ajoutImage() throws FileNotFoundException{
        NouvelleImage nImage= new NouvelleImage(this);
        Dimension tmp = nImage.showDialog();
        if (tmp!=null) {
            
            Général.add (new Général(tmp.width,tmp.height));
            actualiserFenetre();
        }
    }
    
    public void suppImage(int i) throws FileNotFoundException{
        Général.remove(i);
        actualiserFenetre();
    }
    
    public void actualiserFenetre() throws FileNotFoundException{
        Fenetre = new JPanel();
        tp.removeAll();
        
        
        for (int i = 0; i < Général.size(); i++) {
            Général.get(i).actualiserGeneral();
            Général.get(i).setName(Général.get(i).image.getName());
            tp.add(Général.get(i).image.getName(),Général.get(i));
        }
        
        Fenetre.add(tp);
        
        Fenetre.updateUI();
        //interface_.updateUI();
        
        this.setContentPane(Fenetre);
        //this.setContentPane(Général);
    }
    
    public void actualiserInterface(){
        for (int i = 0; i < Général.size(); i++) {
            try {
                Général.get(i).interface_.actualiseInterface();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    private void addCouleur(Color c){
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("donnee\\couleur.txt", true);
            bufWriter = new BufferedWriter(fileWriter);
            //InsÃ©rer un saut de ligne
            bufWriter.newLine();
            bufWriter.write(c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" "+c.getAlpha());
            bufWriter.close();
        } catch (IOException ex) {
            
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                
            }
        }
    }
    
    public void suppCouleur(int i){
        
        Général.get(0).interface_.getListeColorJButton().remove(i);
        
        try {
            String encoding = "UTF-8";
            PrintWriter writer;
            writer = new PrintWriter("donnee\\couleur.txt", encoding);
            for (Color c: Général.get(0).interface_.getListeColorJButton()) {
                writer.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" "+c.getAlpha());
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    protected void paintComponent(Graphics g) {
//        JPanel pano = new JPanel();
//        for (int i = 0; i < listeJButton.size(); i++) {
//            pano.add(listeJButton.get(i));
//        }
//        pano.setVisible(true);
//                
//    }
    
    
    
    
    
    @Override
    public final Dimension getPreferredSize() {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            
        dimension.height-=50;
        tailleFenetre= dimension;
        
        return dimension;
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menuItemExporter) {
            Save ajout = new Save(this);
            AjouterCalqueRenvoyer tmp = ajout.showDialog();
            if (tmp!=null) {
                Général.get(tmp.ChoixCalque).image.saveImage("save\\"+tmp.Name);                               // a changer le zero pour quelle save a faire
            
                File fils =new File("save");
                try {
                    Desktop.getDesktop().open(new File(fils.getAbsolutePath()));
                } catch (IOException ex) {
                    Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        if (e.getSource()==menuItemAjouterCalque) {
//            String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du calque");
            AjouterCalque ajout = new AjouterCalque(this);
            AjouterCalqueRenvoyer tmp = ajout.showDialog();
            if (tmp!=null) {
                Général.get(tmp.ChoixCalque).image.addCalque(tmp.Name);                                        // a changer le zero pour ajoutezr le calque ou bon endroit
                
                actualiserInterface();
            }
            
        }
        if (e.getSource()==menuItemSupprimerCalque) {
            SupprimerCalque supp =new SupprimerCalque(this);
            AjouterCalqueRenvoyer tmp = supp.showDialog();
            Général.get(Integer.parseInt(tmp.Name)).image.delCalque(tmp.ChoixCalque);
            
            actualiserInterface();
        }
        if (e.getSource()==menuItemAjouterCouleur) {
            AjouterCouleur aj= new AjouterCouleur(this);
            Color tmp=aj.showDialog();
            if (tmp!=null) {
                
                addCouleur(tmp);
                actualiserInterface();
            }

        }
        if (e.getSource()==menuItemSupprimerCouleur) {
            SupprimerCouleur supp =new SupprimerCouleur(this);
            suppCouleur(supp.showDialog());
            actualiserInterface();
        }
        if (e.getSource()==menuItemNouveauImage) {
            try {
                ajoutImage();
            } catch (FileNotFoundException ex) {
                
            }
        }
        if (e.getSource()==menuItemSupprimerImage) {
            SupprimerImage supp = new SupprimerImage(this);
            try {
                suppImage(supp.showDialog());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    @Override
    public void stateChanged(ChangeEvent e) {
        if (tp.getSelectedIndex()!=-1) {
            Général.get(tp.getSelectedIndex()).interface_.vueOutils.vuTaillePinceau.setText(Image.taillePinceau+"");
            imageSéléctionne=tp.getSelectedIndex();
            for (int i = 0; i < Général.size(); i++) {
                    Général.get(i).interface_.vueOutils.actualiserChoisie();
                    Général.get(i).interface_.Couleur.actualiserChoisie();
                }
        }
        
    }
    
}
