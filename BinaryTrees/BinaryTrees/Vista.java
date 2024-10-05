/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTrees;

/**
 *
 * @author qwerty2
 */

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Controlador controlador;
    private Modelo modelo;
    private JTextField nodeInputField, levelCalculationField, deleteInputField;
    private JRadioButton inOrderButton;
    private JRadioButton preOrderButton, postOrderButton;
    private JLabel heightLabel, levelLabel;
    private JButton addNodeButton, traverseButton, bfsButton, calculateLevelButton, deleteNodeButton, changeTreeTypeButton;
    private TreePanel treePanel;
    private boolean isNumberTree;

    private static final Color BUTTON_COLOR = new Color(128, 212, 108);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR2 = Color.BLACK;
    private static final Font ARIAL_BLACK = new Font("Arial Black", Font.PLAIN, 12);

    public Vista() {
        setTitle("Binary Tree Manager");
        setSize(1280, 960);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
        addListeners();
        type();
    }

    private void type() {
        String[] options = {"Números", "Letras"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "¿Qué tipo de árbol deseas manejar?",
            "Tipo de Árbol",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        switch (choice) {
            case 0 -> isNumberTree = true;
            case 1 -> isNumberTree = false;
            default -> System.exit(0);
        }
    }

    private void initComponents() {
        treePanel = new TreePanel();
        controlador = new Controlador(treePanel);
        nodeInputField = createStyledTextField();
        levelCalculationField = createStyledTextField();
        deleteInputField = createStyledTextField();

        inOrderButton = createStyledRadioButton("In-Order");
        preOrderButton = createStyledRadioButton("Pre-Order");
        postOrderButton = createStyledRadioButton("Post-Order");
        ButtonGroup traversalGroup = new ButtonGroup();
        traversalGroup.add(inOrderButton);
        traversalGroup.add(preOrderButton);
        traversalGroup.add(postOrderButton);

        heightLabel = createStyledLabel("Altura: 0");
        levelLabel = createStyledLabel("Nivel: 0");

        addNodeButton = createStyledButton("Añadir nodo");
        traverseButton = createStyledButton("Recorrer");
        bfsButton = createStyledButton("Busqueda en amplitud");
        calculateLevelButton = createStyledButton("Calcular Nivel");
        deleteNodeButton = createStyledButton("Eliminar Nodo");
        changeTreeTypeButton = createStyledButton("Cambiar Tipo de Árbol"); // Nuevo botón

        treePanel.setBackground(Color.WHITE);
    }

    private void addComponents() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        controlPanel.add(createStyledLabel("Ingrese valor del Nodo:"));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(nodeInputField);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(addNodeButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        controlPanel.add(createStyledLabel("Recorrer:"));
        controlPanel.add(inOrderButton);
        controlPanel.add(preOrderButton);
        controlPanel.add(postOrderButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(traverseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        controlPanel.add(bfsButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        controlPanel.add(heightLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        controlPanel.add(createStyledLabel("Calcular Nivel:"));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(levelCalculationField);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(calculateLevelButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(levelLabel);

        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(createStyledLabel("Valor Nodo a eliminar:"));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(deleteInputField);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(deleteNodeButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        
        controlPanel.add(changeTreeTypeButton);

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
            if (isNumberTree) {
                if (!inputValue.matches("-?\\d+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente números.");
                    return;
                }
                try {
                    int nodeValue = Integer.parseInt(inputValue);
                    controlador.addNode(nodeValue);
                    treePanel.setTree(controlador.getRoot(),controlador.altura());
                    treePanel.repaint(); 
                    heightLabel.setText("Altura: "+controlador.altura());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
                }
            } else {
                if (!inputValue.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente letras.");
                    return;
                }
                controlador.addNode(inputValue); 
                treePanel.setTree(controlador.getRoot(),controlador.altura());
                treePanel.repaint();
                heightLabel.setText("Altura: "+controlador.altura());
            }
        });
       deleteNodeButton.addActionListener(e -> {
            String inputValue = deleteInputField.getText().trim(); 
            deleteInputField.setText("");
            if (inputValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor.");
                return;
            }
            if (isNumberTree) {
                if (!inputValue.matches("-?\\d+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente números.");
                    return;
                }
                try {
                    int nodeValue = Integer.parseInt(inputValue);
                    controlador.removeNode(nodeValue); 
                    treePanel.setTree(controlador.getRoot(),controlador.altura()); 
                    treePanel.repaint(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
                }
            } else {
                if (!inputValue.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente letras.");
                    return;
                }
                    controlador.removeNode(inputValue); 
                    treePanel.setTree(controlador.getRoot(),controlador.altura()); 
                    treePanel.repaint();  
            }
        });
       
        traverseButton.addActionListener(e -> {
            String resultadoRecorrido = "";
            String nombreRecorrido = "";

            if (inOrderButton.isSelected()) {
                resultadoRecorrido = controlador.in_orden();
                nombreRecorrido = "In-Order"; 
            } else if (preOrderButton.isSelected()) {
                resultadoRecorrido = controlador.pre_orden();
                nombreRecorrido = "Pre-Order";
            } else if (postOrderButton.isSelected()) {
                resultadoRecorrido = controlador.post_orden();
                nombreRecorrido = "Post-Order";
            }

            treePanel.actualizarRecorrido(resultadoRecorrido, nombreRecorrido); 
            treePanel.repaint();  
        });
        
         bfsButton.addActionListener(e -> {
           String resultadoRecorrido = controlador.iniciarBEA(); 
           String nombreRecorrido = "Búsqueda en Amplitud"; 

            treePanel.actualizarRecorrido(resultadoRecorrido, nombreRecorrido); 
            treePanel.repaint();  
        });
        
         changeTreeTypeButton.addActionListener(e -> { 
            dispose();
            Vista nuevaVista = new Vista(); 
            nuevaVista.setVisible(true); 
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
        drawTree(g, root, getWidth() / 2, 50, 100, altura - 1);
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
        g2.drawLine(x, y + radius, x - xOffset*exp/2, y + 100 - radius);
        drawTree(g, node.getizq(), x - xOffset*exp/2, y + 100, newOffset,exp-1);
    }

    if (node.getdere() != null) {
        g2.setColor(Color.BLACK); 
        g2.drawLine(x, y + radius, x + xOffset*exp/2, y + 100 - radius);
        drawTree(g, node.getdere(), x + xOffset*exp/2, y + 100, newOffset,exp-1);
    }


