package Servlet;

import Trees.BinaryTree;
import Trees.LinkedlistIS;

public class Method {
	BinaryTree bt = new BinaryTree();
    public static int x=0;

    public LinkedlistIS method(LinkedlistIS dragonList){
        Message request= new Message();
        LinkedlistIS newList= null;
        switch(x){
            case 0:
            //    newList= request.Method("selection",dragonList);
            	dragonList.selectionSort(dragonList.head);
                x++;
                break;
            case 1:
             //   newList = request.Method("insertion", dragonList);
            	dragonList.insertionSort(dragonList.head);
                x++;
                break;

            case 2:
             //   newList =request.Method("quicksort",dragonList);
            	dragonList.quickSort(dragonList.head);
                x++;
                break;

            case 3:
             //   newList =request.Method("BinaryTree",dragonList);
            	bt.add(dragonList, bt);
                x++;
                break;

            case 4:
             //   newList =request.Method("avl",dragonList);
            	dragonList.insertarAux(dragonList);
                x=0;
                break;
        }
        return newList;

    }


}
