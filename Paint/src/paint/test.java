import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


public class test extends JPanel {
   private static final int PREF_W = 500;
   private static final int PREF_H = 500;

   public test() {

      JScrollPane scrollpane = new JScrollPane(new LongImagePanel());

      setLayout(new BorderLayout());
      add(scrollpane);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   private class LongImagePanel extends JComponent {
      private static final int LI_PREF_W = 5000;
      private static final int LI_PREF_H = 5000;

      @Override
      public Dimension getPreferredSize() {
         return new Dimension(LI_PREF_W, LI_PREF_H);
      }
   }

   

   public static void main(String[] args) {
    test mainPanel = new test();

      JFrame frame = new JFrame("PreferredSizeEg");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}