package RedBlack;

import Util.RBNodeInterface;
import java.util.ArrayList;

import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {

    T key;
    int color;
    List<E> list;
    RedBlackNode<T,E> left;
    RedBlackNode<T,E> right;
    RedBlackNode<T,E> parent;
    RedBlackNode(T k,E v){
    	color=0;
    	list=new ArrayList();
    	list.add(v);
    	this.key=k;
    	this.left=null;
    	this.right=null;
    	this.parent=null;
    }
    public E getValue() {
        return list.get(0);
    }

    
    public List<E> getValues() {
        return this.list;
    }
}
