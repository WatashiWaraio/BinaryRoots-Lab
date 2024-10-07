/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Huffman;

/**
 *
 * @author qwerty2
 */

import Huffman.*;
import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Controlador controlador;
    private Modelo modelo;
    private JTextField nodeInputField;
    private JButton addNodeButton;
    private TreePanel treePanel;

    private static final Color BUTTON_COLOR = new Color(128, 212, 108);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR2 = Color.BLACK;
    private static final Font ARIAL_BLACK = new Font("Arial Black", Font.PLAIN, 12);

    public Vista() {
        setTitle("Huffman Tree Manager");
        setSize(1280, 960);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
        addListeners();
    }

    

    private void initComponents() {
        treePanel = new TreePanel();
        controlador = new Controlador(treePanel);
        nodeInputField = createStyledTextField();
        addNodeButton = createStyledButton("Añadir nodo");

        treePanel.setBackground(Color.WHITE);
        nodeInputField = createStyledTextField();
    }

    private void addComponents() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        controlPanel.add(createStyledLabel("Ingrese un texto:"));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(nodeInputField);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(addNodeButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

       

        JScrollPane scrollPane = new JScrollPane(treePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(controlPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER); 
    }


    private void addListeners() {
       addNodeButton.addActionListener(e -> {
            String inputValue = nodeInputField.getText().trim(); 
            nodeInputField.setText("");
            if (inputValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor.");
                return;
            }

            if (!inputValue.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente letras.");
                return;
            }
            controlador.huffman(inputValue); 
            treePanel.setTree(controlador.getRoot(),controlador.altura()); 
            treePanel.repaint();
            
        });
       
    }


    private JTextField createStyledTextField() {
    JTextField textField = new JTextField(10); 
    textField.setFont(ARIAL_BLACK);
    textField.setForeground(TEXT_COLOR2);
    textField.setBackground(Color.WHITE);
    textField.setCaretColor(TEXT_COLOR2);
    textField.setPreferredSize(new Dimension(50, 30));
    textField.setMaximumSize(textField.getPreferredSize());

 

    return textField;
}


    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(ARIAL_BLACK);
        radioButton.setForeground(TEXT_COLOR);
        radioButton.setBackground(Color.BLACK);
        return radioButton;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(ARIAL_BLACK);
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(ARIAL_BLACK);
        button.setForeground(TEXT_COLOR);
        button.setBackground(BUTTON_COLOR);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }

  


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista app = new Vista();
            app.setVisible(true);
        });
    }
}

class TreePanel extends JPanel {

    private Modelo root;
    private int altura;
    private String recorridoActual = ""; 
    private String tipoRecorrido = "";


 

    public void setTree(Modelo root, int altura) {
        this.root = root;
        this.altura = altura;
    }
    
    public void actualizarRecorrido(String recorrido, String tipoRecorrido) {
        this.recorridoActual = recorrido; 
        this.tipoRecorrido = tipoRecorrido; 
    }
    
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);  

    if (root != null) {
        drawTree(g, root, getWidth() / 2, 50, 15, altura - 1);
    }

    if (!recorridoActual.isEmpty()) {
        g.setColor(Color.BLACK);

        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(tipoRecorrido);
        int recorridoWidth = fm.stringWidth(recorridoActual);
        
        int rectWidth = Math.max(titleWidth, recorridoWidth) + 20;
        int rectHeight = 100;
        int rectXPosition = (getWidth() - rectWidth) / 2;
        int rectYPosition = getHeight() - 150;

        g.fillRect(rectXPosition, rectYPosition, rectWidth, rectHeight);

        g.setColor(Color.WHITE);
        
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(tipoRecorrido, rectXPosition + 10, rectYPosition + fm.getAscent() + 10);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(recorridoActual, rectXPosition + 10, rectYPosition + fm.getAscent() + 50);
    }
}



 private void drawTree(Graphics g, Modelo node, int x, int y, int xOffset,int exp) {
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

 
    String content = node.getLetra()+"("+String.valueOf(node.getContent()+")");
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
        g2.drawLine(x, y + radius,(int) (x - xOffset*Math.pow(2,exp)), y + 100 - radius);
        drawTree(g, node.getizq(), (int) (x - xOffset*Math.pow(2,exp)), y + 100, xOffset,exp-1);
    }

    if (node.getdere() != null) {
        g2.setColor(Color.BLACK); 
        g2.drawLine(x, y + radius,(int) (x + xOffset*Math.pow(2,exp)), y + 100 - radius);
        drawTree(g, node.getdere(),(int) (x + xOffset*Math.pow(2,exp)), y + 100, xOffset,exp-1);
    }
    }
 }