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
import static java.lang.Math.max;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    
    
    public String iniciarBEA() {
    if (root == null) {
        return "El árbol está vacío.";
    }
    
    String resultado = bea(root); 
    return resultado; 
}

  
    private String bea(Modelo node) {
        if (node == null) {
            return "El árbol está vacío.";
        }

        Queue<Modelo> queue = new LinkedList<>();
        queue.add(node); 
        StringBuilder textoResultado = new StringBuilder("BFS = "); 

        while (!queue.isEmpty()) {
            Modelo current = queue.poll(); 
            textoResultado.append(current.getContent()).append(" -> "); 
            
     
            if (current.getizq() != null) {
                queue.add(current.getizq());
            }
            if (current.getdere() != null) {
                queue.add(current.getdere());
            }
        }

  
        if (textoResultado.length() > 0) {
            textoResultado.setLength(textoResultado.length() - 4);
        }

        return textoResultado.toString(); 
    }
    
    
    public String in_orden(){
         Modelo nodo=getRoot();
       if(nodo==null){
           return null;
       }
       Stack<Modelo> pila= new Stack<>();
       String texto="";
       while(!pila.isEmpty() || nodo!=null){
           if(nodo!=null){
               pila.push(nodo);
               nodo=nodo.getizq();
           }else{
               nodo=pila.pop();
               texto+=nodo.getContent()+"  -> ";
               nodo=nodo.getdere();
           }
       }
       return texto;
    }
    
    public String pre_orden(){
       Modelo nodo=getRoot();
       if(nodo==null){
           return null;
       }
       Stack<Modelo> pila= new Stack<>();
       pila.push(nodo);
       String texto="";
        while (!pila.isEmpty()) {
            Modelo actual=pila.pop();
            texto+=actual.getContent()+"  -> ";
            if(actual.getdere()!=null){
               pila.push(actual.getdere());
            }
            if(actual.getizq()!=null){
               pila.push(actual.getizq());
            }
        }
        return texto;
    }
     
    public String post_orden(){
        Modelo nodo=this.getRoot();
        if(nodo==null){
          return null;
        }
        Stack<Modelo> pila= new Stack<>();
        String texto="";
        Modelo last=null;
        while(!pila.isEmpty() || nodo != null){
            if (nodo!=null){
                pila.push(nodo);
                nodo=nodo.getizq();
            }else{
                Modelo posible=pila.peek();
                if(posible.getdere()!=null && last!=posible.getdere()){
                    nodo=posible.getdere();
                }else{
                    texto+=posible.getContent()+" --> ";
                    last=pila.pop();
                }
            }
        }
        return texto; 
    }  
    
      public int altura(){
        return alturaRecursivo(getRoot());
    }
    public int alturaRecursivo(Modelo nodo){
        if(nodo==null){
            return 0;
        }
        return 1+max(alturaRecursivo(nodo.getdere()),alturaRecursivo(nodo.getizq()));
    }
    
}




 }
}
