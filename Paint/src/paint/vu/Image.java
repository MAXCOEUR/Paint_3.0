/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.vu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.BUTTON1;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import paint.Calque;

/**
 *
 * @author Maxen
 */
public class Image extends JComponent implements MouseListener,MouseMotionListener{
    private ArrayList<Calque> calque= new ArrayList<>();
    
    public int taillePixel,HauteurPixel,LargeurPixel;
    
    private int calqueChoisie=0;
    public static int ModeChoisie=0;
    public static int couleurChoisie=0;
    public static int taillePinceau=1;
    
    public static Color pinceau;
    
    public static int nbrImage=0;
    public int nbrCalque=0;
    
    private boolean Mouspressed=false;
    int xVCercle=-1,yVCercle=-1;
    int xVRectangle=-1,yVRectangle=-1;
    int xVHorizon=-1,yVHorizon=-1;
    int xVVertical=-1,yVVertical=-1;
    int xVLigne=-1,yVLigne=-1;
    
    Color colorV;
    
    public Image(int hauteur,int largeur) {
        nbrImage++;
        this.setName(nbrImage+"");
        calque.add(new Calque(hauteur, largeur));
        taillePixel=min(getPreferredSize().width/largeur,getPreferredSize().height/hauteur);
        HauteurPixel=hauteur;
        LargeurPixel=largeur;
    }
    
//    public boolean getVisibleCalque(int i){
//        return calque.get(i).getVisible();
//    }
//    
//    public void changeVisibleCalque(int i,boolean b){
//        calque.get(i).setVisible(!calque.get(i).getVisible());
//    }

      
    
    public void addCalque(String name){
        calque.add(new Calque(HauteurPixel, LargeurPixel,name,this));
    }
    
    public void delCalque(int i){
        calque.remove(i);
    }
    
    public ArrayList<Calque> getCalque() {
        return calque;
    }
    
    public int getCalqueChoisie() {
        return calqueChoisie;
    }

    public void setCalqueChoisie(int calqueChoisie) {
        this.calqueChoisie = calqueChoisie;
    }
    
    
    private int min(double a,double b){
        if (a<b) return (int) a;
        else return (int) b;
    }
    /**
     * 
     * @param nbrCalque le numero du calque dans la liste
     * @param C la couleur en CouleurRGBA
     * @param i la hauteur en int
     * @param j la largeur en int
     */
    public void setpixel(int nbrCalque,Color C, int i,int j){
        Calque tmp=calque.get(nbrCalque);
        tmp.setPixelCouleurRGBA(C, i, j);
        calque.set(nbrCalque, tmp);
        repaint();
    }
    
    public Color getpixel(int nbrCalque,int i,int j){
        Calque tmp=calque.get(nbrCalque);
        ArrayList<ArrayList<Color>> getP= tmp.getPixel();
        return getP.get(i).get(j);
    }
    
    
    public void saveImage(String s){
        String encoding = "UTF-8";
        PrintWriter writer;
        try {
            writer = new PrintWriter(s+".svg", encoding);
            writer.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                            "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\"\n" +
                            "\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n" +
                            "<svg width=\""+taillePixel*LargeurPixel+"px\" height=\""+taillePixel*HauteurPixel+"px\" xml:lang=\"fr\"\n" +
                            "xmlns=\"http://www.w3.org/2000/svg\"\n" +
                            "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n" +
                            "   <title></title>\n" +
                            "   <desc></desc>");
            for (int z = calque.size()-1; z >=0; z--) {
                if (calque.get(z).getVisible()==true) {
                    for (int i = 0; i < HauteurPixel; i++) {
                        for (int j = 0; j < LargeurPixel; j++) {
                            if (calque.get(z).getPixelCouleurRGBA(i, j).getAlpha()!=0) {
                                writer.println("<rect x=\""+j*taillePixel+"px\" y=\""+i*taillePixel+"px\" width=\""+taillePixel+"\" height=\""+taillePixel+"\" style=\"fill:rgb("+calque.get(z).getPixelCouleurRGBA(i, j).getRed()+","+calque.get(z).getPixelCouleurRGBA(i, j).getGreen()+","+calque.get(z).getPixelCouleurRGBA(i, j).getBlue()+");fill-opacity:"+calque.get(z).getPixelCouleurRGBA(i, j).getAlpha()/255.0+"\"/>");
                        
                            }
                        }
                    }
                }
            
        }
            writer.println("</svg>");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.setColor(new Color(0, 0, 0,255));
        g.drawRect(0, 0, LargeurPixel*taillePixel, HauteurPixel*taillePixel-1);
        //g.drawLine(getPreferredSize().width-1, 0, getPreferredSize().width-1, getPreferredSize().height);
        
        for (int z = calque.size()-1; z >=0; z--) {
            if (calque.get(z).getVisible()==true) {
                for (int i = 0; i < HauteurPixel; i++) {
                    for (int j = 0; j < LargeurPixel; j++) {
                        g.setColor(calque.get(z).getPixelCouleurRGBA(i, j));
                        g.fillRect(j*taillePixel, i*taillePixel, taillePixel, taillePixel);
                    }
                }
            }
            
        }
    }
    
    
    @Override
    public final Dimension getPreferredSize() {
        //a modifier
//        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//        dimension.width=((dimension.width-150)/4)*3;
//        dimension.height-=150;
        return new Dimension((MaFenetre.tailleFenetre.width/4-50)*3, MaFenetre.tailleFenetre.height-100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    
    private void ModePinceau(int x, int y){
        for (int i = -taillePinceau/2; i < Math.ceil(taillePinceau/2.0); i++) {
            for (int j = -taillePinceau/2; j < Math.ceil(taillePinceau/2.0); j++) {
                this.setpixel(calqueChoisie, pinceau, y+i, x+j);
            }
            
        }
    }
    
    private void ModePinceau(int x, int y,Color c){
        for (int i = -taillePinceau/2; i < Math.ceil(taillePinceau/2.0); i++) {
            for (int j = -taillePinceau/2; j < Math.ceil(taillePinceau/2.0); j++) {
                this.setpixel(calqueChoisie, c, y+i, x+j);
            }
            
        }
        
    }
    
    private void ModeTraitVertical(int x, int y){
        if (yVVertical==-1) {
            xVVertical=x;
            yVVertical=y;
            colorV=this.getpixel(nbrCalque, y, x);
            ModePinceau(x, y,inversColor(colorV));
        }
        else{
            ModePinceau(xVVertical, yVVertical,colorV);
            int dify= Math.abs(yVVertical-y);
            if (yVVertical-y<0) {
                for (int i = 0; i <= dify; i++) {
                    ModePinceau(xVVertical, yVVertical+i);
                }
            }
            else{
                for (int i = 0; i <= dify; i++) {
                    ModePinceau(xVVertical, y+i);
                }
            }
            xVVertical=-1;
            yVVertical=-1;
        }
    }
    
    private void ModeTraitHorizontal(int x, int y){
        if (xVHorizon==-1) {
            xVHorizon=x;
            yVHorizon=y;
            colorV=this.getpixel(nbrCalque, y, x);
            ModePinceau(x, y,inversColor(colorV));
        }
        else{
            ModePinceau(xVHorizon, yVHorizon,colorV);
            int difx= Math.abs(xVHorizon-x);
            if (xVHorizon-x<0) {
                for (int i = 0; i <= difx; i++) {
                    ModePinceau( xVHorizon+i, yVHorizon);
                }
            }
            else{
                for (int i = 0; i <= difx; i++) {
                    ModePinceau( x+i, yVHorizon);
                }
            }
            xVHorizon=-1;
            yVHorizon=-1;
        }
    }
    
    private Color inversColor(Color c){
        return new Color((int)Math.abs(c.getRed()-255), (int)Math.abs(c.getGreen()-255), (int)Math.abs(c.getBlue()-255), 255);
    }
    
    private void ModeRectangle(int x, int y){
        if (xVRectangle==-1&&yVRectangle==-1) {
            xVRectangle=x;
            yVRectangle=y;
            
            colorV=this.getpixel(nbrCalque, y, x);
            ModePinceau(x, y,inversColor(colorV));
        }
         else{
            ModePinceau(xVRectangle, yVRectangle,colorV);
            
            ModeTraitHorizontal(xVRectangle, yVRectangle);
            ModeTraitHorizontal(x, y);
            
            ModeTraitHorizontal(xVRectangle, y);
            ModeTraitHorizontal(x, y);
            
            ModeTraitVertical(xVRectangle, yVRectangle);
            ModeTraitVertical(x, y);
            
            ModeTraitVertical(x, yVRectangle);
            ModeTraitVertical(x, y);
            
            xVRectangle=-1;
            yVRectangle=-1;
        }
    }
    
    
    
    private void ModeCercle(int x, int y){
         if (xVCercle==-1&&yVCercle==-1) {
            xVCercle=x;
            yVCercle=y;
            colorV=this.getpixel(nbrCalque, y, x);
            ModePinceau(x, y,inversColor(colorV));
        }
         else{
            ModePinceau(xVCercle, yVCercle,colorV);
            int r = Math.abs(x-xVCercle)*Math.abs(x-xVCercle)+Math.abs(y-yVCercle)*Math.abs(y-yVCercle);
            r=(int) Math.sqrt(r);
            
            double PI = 3.1415926535;
            double i, angle, x1, y1;

            for(i = 0; i < 360; i += 0.1)
            {
                  angle = i;
                  
                  x1 = r * Math.cos(angle * Math.PI / 180);
                  y1 = r * Math.sin(angle * Math.PI / 180);
                  ModePinceau( (int)(xVCercle + x1), (int)(yVCercle+y1));
            }
            
            xVCercle=-1;
            yVCercle=-1;
         }
    }
    
    private void ModePeauDeCouleur(int x, int y){
        boolean dejaPasser[][]= new boolean[calque.get(calqueChoisie).getPixel().size()][calque.get(calqueChoisie).getPixel().get(0).size()];
        for (int i = 0; i < dejaPasser.length; i++) {
            for (int j = 0; j < dejaPasser[i].length; j++) {
                dejaPasser[i][j]=false;
            }
        }
        Color couleurRemplacer = calque.get(calqueChoisie).getPixel().get(y).get(x);
        Point t=new Point(x, y);
        
        ArrayList<Point> FIFO=new ArrayList<>();
        FIFO.add(t);
        dejaPasser[t.y][t.x]=true;
        this.setpixel(calqueChoisie, pinceau,  t.y, t.x);
        
        while (FIFO.size() != 0) {
            t=FIFO.get(0);
            FIFO.remove(0);
            
            
            if (t.y-1>=0&& couleurRemplacer.equals(calque.get(calqueChoisie).getPixel().get(t.y-1).get(t.x)) && dejaPasser[t.y-1][t.x]==false ) {
                FIFO.add(new Point(t.x, t.y-1));
                dejaPasser[t.y-1][t.x]=true;
                this.setpixel(calqueChoisie, pinceau,  t.y-1, t.x);
            }
            if (t.x-1>=0&& couleurRemplacer.equals(calque.get(calqueChoisie).getPixel().get(t.y).get(t.x-1)) && dejaPasser[t.y][t.x-1]==false ) {
                FIFO.add(new Point(t.x-1,t.y));
                dejaPasser[t.y][t.x-1]=true;
            this.setpixel(calqueChoisie, pinceau,  t.y, t.x-1);
            }
            if (t.y+1<dejaPasser.length && couleurRemplacer.equals(calque.get(calqueChoisie).getPixel().get(t.y+1).get(t.x)) && dejaPasser[t.y+1][t.x]==false ) {
                FIFO.add(new Point( t.x,t.y+1));
                dejaPasser[t.y+1][t.x]=true;
            this.setpixel(calqueChoisie, pinceau,  t.y+1, t.x);
            }
            if (t.x+1<dejaPasser.length && couleurRemplacer.equals(calque.get(calqueChoisie).getPixel().get(t.y).get(t.x+1)) && dejaPasser[t.y][t.x+1]==false ) {
                FIFO.add(new Point(t.x+1,t.y));
                dejaPasser[t.y][t.x+1]=true;
            this.setpixel(calqueChoisie, pinceau,  t.y, t.x+1);
            }
        }
        
        
    }
    
    private void ModAuto(int x,int y){
        if (y<HauteurPixel&&x<LargeurPixel) {
                if (calque.get(calqueChoisie).getVisible()==true) {
                    switch (ModeChoisie) {
                        case 0:
                            ModePinceau(x, y);
                            break;
                        case 1:
                            ModeTraitVertical(x, y);
                            break;
                        case 2:
                            ModeTraitHorizontal(x, y);
                            break;
                        case 3:
                            ModeCercle(x, y);
                            break;
                        case 4:
                            ModePeauDeCouleur(x, y);
                            break;
                        case 5:
                            ModeRectangle(x, y);
                            break;
                    }
                    
                }
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if (e.getButton()==BUTTON1) {
            Mouspressed=true;
            int x=e.getX()/(LargeurPixel*taillePixel/LargeurPixel);
            int y = e.getY()/(HauteurPixel*taillePixel/HauteurPixel);
            ModAuto(x,y);
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton()==BUTTON1) {
            Mouspressed=false;
            
        }
        
    }
    
    

    @Override
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x=e.getX()/(LargeurPixel*taillePixel/LargeurPixel);
        int y = e.getY()/(HauteurPixel*taillePixel/HauteurPixel);
        ModAuto(x,y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ;
    }
    
}
