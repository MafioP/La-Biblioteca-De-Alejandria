/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class TagTree<String> {
    private Node<String> root;

    public TagTree(String rootData) {
        root = new Node<String>(rootData);
        root.children = new ArrayList<Node<String>>();
    }
    
    public String getData() {
        return root.data;
    }
    
    public void addNode(Node<String> node) {
        root.children.add(node);
    }

    public static class Node<String> {
        private String data;
        private Node<String> parent;
        private List<Node<String>> children;
        
        /**
         * Añadir una opcion al selector de tags
         * @param nombre
         * @return 
         */
        public Node(String nombre) {
            data = nombre;
        }
        
        public Node<String> addTag(String nombre){
            Node<String> nuevo = new Node<String>(nombre);
            nuevo.parent = this;
            this.children.add(nuevo);
            return nuevo;
        }
    }
}
