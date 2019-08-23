package edu.escuelaing.arep.calculator;

/**
 * Una Lista enlazada es una agrupaci�n de nodos, de los
 * que el primero se denomina cabeza(head).
 * Puede agregar y eliminar nodos, co�mo tambi�n
 * obtener su tama�o.
 * 
 * @author Amalia Alfonso
 */
public class LinkedList {    
    private Node head;
    private int length;    
    

    /**
     * addNode Agrega un nuevo nodo al final de la linkedList
     * @param newValue nuevo dato que se va a agregar
     */
    public void addNode(double newValue) {
        Node newNode = new Node(newValue);
        if(head == null){
            length = 1;
            head = newNode;
        }else{
            Node item = head;
            while(item.getNext() != null){
                item = item.getNext();
            }
            item.setNext(newNode);
            length++;
        }        
    }
    
    /**
     * findLength Determina la longitud de la linkedList
     * @return Devuelve el tama�o de la linkedList
     */
    public int findLength() {
        int length = 0;
        if(head != null){
            length ++;
            Node item = head.getNext();
            while(item != null){
                length++;
                item = item.getNext();                
            }
        }
        return length;
    }
    
    /**
     * deleteNode Elimina de la linkedList el nodo que tiene el dato que coincide con el dato dado
     * @param dateToDelete Dato del nodo a eliminaar
     */
    public void deleteNode(double dateToDelete) {
        if(head != null){            
            Node item = head;
            if(item.getValue() == dateToDelete){
                head = item.getNext();
            }else{
                while(item.getNext() != null){
                    if(item.getNext().getValue() == dateToDelete){
                        item.setNext(item.getNext().getNext());
                        length--;
                        break;
                    }
                    item = item.getNext();                
                }
            }
        }
    }
    
    /**
     * getHead Devuelve el primer nodo de la linkedList
     * @return Nodo que est� al inicio de la linkedList
     */
    public Node getHead(){
        return head;
    }
    
    /**
     * getLength Devuelve el tama�o de la linkedList
     * @return Tama�o de la linkedList
     */
    public int getLength() {
        return length;
    }
    
    /**
     * showLinkedList Convierte en una cadena la linkedList para poder ser mostrada de uan forma entendible
     * @return Cadena con los elementos de la linkedList
     */
    public String showLinkedList(){
        String linkedListAsSring = "";
        if(head != null){
            Node item = head;
            Node next = item.getNext();
            linkedListAsSring = "["+item.getValue()+"]";           
            while(next != null){
                linkedListAsSring += "->["+next.getValue()+"]";
                next = next.getNext();        
            }               
        }        
        //System.out.println(linkedListAsSring);
        return linkedListAsSring;
    }    
}
