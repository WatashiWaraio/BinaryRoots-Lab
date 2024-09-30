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
    private Modelo Modelo; 
    private JTextField nodeInputField, levelCalculationField,deleteInputField;
    private JRadioButton inOrderButton, preOrderButton, postOrderButton;
    private JLabel heightLabel, levelLabel;
    private JButton addNodeButton, traverseButton, bfsButton, calculateLevelButton, deleteNodeButton;
    private TreePanel treePanel;
    private boolean isNumberTree; 

    private static final Color BUTTON_COLOR = new Color(128, 212, 108);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR2 = Color.BLACK;
    private static final Font ARIAL_BLACK = new Font("Arial Black", Font.PLAIN, 12);

    public Vista() {
        type();
        setTitle("Binary Tree Manager");
        setSize(1280, 960);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
        addListeners();
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

        treePanel.setBackground(Color.WHITE);
    }

private void addComponents() {
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setBackground(Color.BLACK);
    controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    
    if (controlPanel.getComponentCount() == 0) {
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
    }

    
    if (this.getContentPane().getComponentCount() < 2) {
        add(controlPanel, BorderLayout.WEST);
        add(treePanel, BorderLayout.CENTER);
    }
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
                    treePanel.setTree(controlador.getRoot());
                    treePanel.repaint(); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
                }
            } else {
                if (!inputValue.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese únicamente letras.");
                    return;
                }
                controlador.addNode(inputValue); 
                treePanel.setTree(controlador.getRoot());
               treePanel.repaint(); 
            }
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


