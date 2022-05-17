/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class TagTreeDB {
    private static ArrayList<TagTree<String>> tagTrees = new ArrayList<>();
    
    public static void insert(String uni, String carrera, String curso, String cuatri, String asig){
        for (int i = 0; i < tagTrees.size(); i++) {
            if (!tagTrees.get(i).getData().equals(uni)){
                tagTrees.add(new TagTree<>(uni));
                tagTrees.get(i).addNode(new TagTree.Node<String>(carrera));
            }
        }
    }
}
