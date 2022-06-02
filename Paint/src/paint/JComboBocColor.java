/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author Maxen
 */
public class JComboBocColor {
    public static void main(String[] args) {
 
		JFrame frame = new JFrame("Démo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		ColorItem[] items = {new ColorItem(Color.RED, "Machin"), new ColorItem(Color.CYAN, "Bidule"), new ColorItem(Color.GREEN, "Truc")};
 
		JComboBox<ColorItem> combo = new JComboBox<>(items);
		combo.setRenderer(new DefaultListCellRenderer() {
 
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				ColorItem item = (ColorItem)value;
				if ( !isSelected ) {
					setBackground(item.getBackground());
				} 
				return component;
			}
		}); 
                combo.setEditable(true);
                combo.getEditor().getEditorComponent().setBackground(Color.YELLOW);
                ((JTextField) combo.getEditor().getEditorComponent()).setBackground(Color.YELLOW);
		frame.add(combo, BorderLayout.NORTH);
 
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
 
 
	}
 
 
	public static class ColorItem {
 
		private final Color background;
		private final String value;
 
		public ColorItem(Color color, String background) {
			this.value=background;
			this.background=color;
		}
 
		public Color getBackground() {
			return background;
		}
 
		public String getValue() {
			return value;
		}
 
		@Override
		public String toString() {
			return value;
		}
	}
}
