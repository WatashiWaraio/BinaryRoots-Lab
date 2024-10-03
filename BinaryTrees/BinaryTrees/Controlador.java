/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTrees;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author qwerty2
 */
public class Controlador {
    
    private TreePanel treePanel; 
    private Modelo root;
    
    public Controlador(TreePanel treePanel) {
       this.treePanel = treePanel;
    }

    public void addNode(Comparable content) {
     root = addRecursive(root, content);
    }

    private Modelo addRecursive(Modelo current, Comparable content) {
        if (current == null) {
            return new Modelo(content); 
        }
        int comparison = content.compareTo(current.getContent());
        if (comparison < 0) {
            current.setizq(addRecursive(current.getizq(), content)); 
        } else if (comparison > 0) {
            current.setdere(addRecursive(current.getdere(), content)); 
        }
        return current;
    }

    public Modelo getRoot() {
        return root; 
    }

    public void removeNode(Comparable content) {
    root = removeRecursive(root, content);
}

    private Modelo removeRecursive(Modelo current, Comparable content) {
        if (current == null) {
            return null;
        }
        int comparison = content.compareTo(current.getContent());
        if (comparison < 0) {
            current.setizq(removeRecursive(current.getizq(), content)); 
        } else if (comparison > 0) {
            current.setdere(removeRecursive(current.getdere(), content)); 
        } else {
            if (current.getizq() == null && current.getdere() == null) {
                return null; 
            }

      
            if (current.getizq() == null) {
                return current.getdere(); 
            } else if (current.getdere() == null) {
                return current.getizq(); 
            }

           

            Comparable smallestValue = findSmallestValue(current.getdere());
            current.setContent(smallestValue); 
            current.setdere(removeRecursive(current.getdere(), smallestValue)); 
        }

        return current; 
    }

    private Comparable findSmallestValue(Modelo root) {
        return root.getizq() == null ? root.getContent() : findSmallestValue(root.getizq());
    }
    
    public void recorrido(){
        
    }
    
    public void BEA(){
        
    }
    
    public void calcularNivel(){
        
    }
    
    private void in_orden(){
        
    }
    
     private void pre_orden(){
        
    }
     
      private void post_orden(){
        
    } 
      
    
}


class TreePanel extends JPanel {

    private Modelo root;

    public void setTree(Modelo root) {
        this.root = root;
       
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  

        if (root != null) {
            System.out.println("Dibujando el árbol con raíz: " + root.getContent());
            drawTree(g, root, getWidth() / 2, 50, 100);
        } else {
            System.out.println("El árbol está vacío.");
        }
    }

 private void drawTree(Graphics g, Modelo node, int x, int y, int xOffset) {
    if (node == null) {
        return;
    }

    Graphics2D g2 = (Graphics2D) g;
    int radius = 30; 

 
    g2.setColor(new Color(108, 218, 81)); 
    g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);

 
    g2.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(3)); 
    g2.drawOval(x - radius, y - radius, radius * 2, radius * 2);

 
    String content = String.valueOf(node.getContent());
    if (content == null || content.isEmpty()) {
        content = "N/A";
    }


    g2.setFont(new Font("Arial", Font.BOLD, 14));
    g2.setColor(Color.WHITE);

  
    FontMetrics fm = g2.getFontMetrics();
    int textWidth = fm.stringWidth(content);
    int textHeight = fm.getAscent() - fm.getDescent();
    

    g2.drawString(content, x - textWidth / 2, y + textHeight / 2);

 
    g2.setStroke(new BasicStroke(2)); 
    int newOffset = Math.max(xOffset - 20, 40);

    if (node.getizq() != null) {
        g2.setColor(Color.BLACK);
        g2.drawLine(x, y + radius, x - xOffset, y + 100 - radius);
        drawTree(g, node.getizq(), x - xOffset, y + 100, newOffset);
    }

    if (node.getdere() != null) {
        g2.setColor(Color.BLACK); 
        g2.drawLine(x, y + radius, x + xOffset, y + 100 - radius);
        drawTree(g, node.getdere(), x + xOffset, y + 100, newOffset);
    }
 }
}
