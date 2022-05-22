
package modelo;

import java.util.ArrayList;


public class TagTreeDB {
    private static TagTree<String> tagTree = new TagTree<String>("Universidades");
    
    public static void insert(String uni, String carrera, String curso, String cuatri, String asig){
        if (!tagTree.getSuccessors(tagTree.getHead()).contains(uni)) {
            TagTree tagUni = tagTree.addLeaf(uni);
            TagTree tagCar = tagUni.addLeaf(carrera);
            TagTree tagCur = tagCar.addLeaf(curso);
            TagTree tagCua = tagCur.addLeaf(cuatri);
            TagTree tagAsig = tagCua.addLeaf(asig);
        } else {
            TagTree tagUni = tagTree.getTree(uni);
            if (!tagUni.getSuccessors(tagUni.getHead()).contains(carrera)) {
                TagTree tagCar = tagUni.addLeaf(carrera);
                TagTree tagCur = tagCar.addLeaf(curso);
                TagTree tagCua = tagCur.addLeaf(cuatri);
                TagTree tagAsig = tagCua.addLeaf(asig);
            } else {
                TagTree tagCar = tagUni.getTree(carrera);
                if (!tagCar.getSuccessors(tagCar.getHead()).contains(curso)) {
                    TagTree tagCur = tagCar.addLeaf(curso);
                    TagTree tagCua = tagCur.addLeaf(cuatri);
                    TagTree tagAsig = tagCua.addLeaf(asig);
                } else {
                    TagTree tagCur = tagCar.getTree(curso);
                    if (!tagCur.getSuccessors(tagCur.getHead()).contains(cuatri)) {
                        TagTree tagCua = tagCur.addLeaf(cuatri);
                        TagTree tagAsig = tagCua.addLeaf(asig);
                    } else {
                        TagTree tagCua = tagCur.getTree(cuatri);
                        if (!tagCua.getSuccessors(tagCua.getHead()).contains(asig)) {
                            tagCua.addLeaf(asig);
                        }
                    }
                }
            }
        }
        System.out.println(tagTree.toString());
    }
}
