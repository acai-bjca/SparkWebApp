package edu.escuelaing.arep.calculator;

/**
 * Un nodo es el elemento fundamental de una linkedList.
 * Se conforma por un dato, y un nodo al que vincula (next).
 *
 */
public class Node 
{
    private double value;
    private Node next;
    
    /**
     * Crea un nuevo nodo, asignando un dato y dejando al elemento next null
     * @param value Dato correspondiente al nuevo nodo
     */
    public Node(double value) {
        this.value = value;
        next = null;
    }
    
    /**
     * isEquals compara el nodo con otro nodo dado
     * @param nodeToCompare Nodo con el que se va a comparar
     * @return Devuelve true, si su dato y el nodo siguiente, son iguales a lso del nodo dado. De lo contrario devuelve false
     */
    public boolean isEquals(Node nodeToCompare) {
        boolean equal = false;
        if(nodeToCompare.getValue() == this.value){
            if((nodeToCompare.getNext() == null && this.next == null) || (nodeToCompare.getNext().getValue() == this.next.getValue())){
                equal = true;
            }            
        }
        //System.out.println("ES IGUALL? "+equal);
        return equal;
    }
    
    /**
     * setNext Asigna el nodo al que enlazar�
     * @param next Nodo a ser enlazado
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * getValue Devuelve el dato del nodo
     * @return Dato del nodo
     */
    public double getValue() {
        return value;
    }

    /**
     * getNext Devuelve el elemento o nodo al que enlaza este nodo
     * @return Nodo enlazado
     */
    public Node getNext() {
        return next;
    }  
}
