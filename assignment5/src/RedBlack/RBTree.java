package RedBlack;
import java.util.LinkedList;
import java.util.Queue;

public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {


    int size;
    RedBlackNode<T,E> root;
    public RBTree(){
    	size=0;
    	root=null;
    	
    }
    RedBlackNode<T,E> ins(RedBlackNode<T,E> r,T k,E v){
    	if(r==null){
    			
        		return new RedBlackNode(k,v);
         }
    	if(r.key.compareTo(k)<0){
    		r.right=ins(r.right,k,v);
    		r.right.parent=r;
    		
    		
    	}
    	else if(r.key.compareTo(k)>0){
    		r.left=ins(r.left,k,v);
    		r.left.parent=r;
    	}
    	else {
    		r.list.add(v);
    	}
    	
    	
		return r;
    	
    }
    
    public void insert(T key, E value) {
    	if(size==0){
    		root=new  RedBlackNode(key,value);
    		root.color=1;
    		size++;
    		return;
    	}
    	RedBlackNode<T,E> r=root;
    	if(r.key.compareTo(key)<0){
    		
    		root.right=ins(r.right,key,value);
    		root.right.parent=root;
    		
    	}
    	else if(r.key.compareTo(key)>0){
    		root.left=ins(r.left,key,value);
    		root.left.parent=root;
    	}
    	else {
    		root.list.add(value);
    	}
    	
    	while(r.key.compareTo(key)!=0){
    		if(r.key.compareTo(key)<0){
    			r=r.right;
    		}
    		else{
    			r=r.left;
    		}
    	}
    	//   print();
    		Reconstruct(r);	
    	//	System.out.println("----------");
      //	print();
    	
    }
    
    void Reconstruct(RedBlackNode<T,E> r){ 
    if(r.parent==null){r.color=1;}
    else{
    	if(r.parent.color==0){	
    	RedBlackNode<T,E> p=r.parent;
		if(r.parent.parent!=null){
			
			RedBlackNode<T,E> gp=r.parent.parent;
			if(gp.left!=null&&gp.right!=null){
				if(gp.left.color==0&&gp.right.color==1||gp.left.color==1&&gp.right.color==0){
					
			if(gp.key.compareTo(p.key)>0&&p.key.compareTo(r.key)>0){
				p.parent=gp.parent;
				if(gp.parent!=null){
					if(gp.parent.key.compareTo(gp.key)>0){
						gp.parent.left=p;
					}
					else if(gp.parent.key.compareTo(gp.key)<0){
						gp.parent.right=p;
					}
				}
				else{
					root=p;
				}
				gp.parent=p;
				gp.left=p.right;
				if(p.right!=null){
					p.right.parent=gp;
				}
				p.right=gp;
				p.color=1;
				gp.color=0;
			}
			else if(gp.key.compareTo(p.key)>0&&p.key.compareTo(r.key)<0){
				//System.out.println("h");
				r.parent=gp.parent;
				if(gp.parent!=null){
					if(gp.parent.key.compareTo(gp.key)>0){
						gp.parent.left=p;
					}
					else if(gp.parent.key.compareTo(gp.key)<0){
						gp.parent.right=p;
					}
				}
				else{
					root=r;
				}
				p.parent=r;
				p.right=r.left;
				if(r.left!=null){r.left.parent=p;}
				r.left=p;
				gp.parent=r;
				gp.left=r.right;
				if(r.right!=null){r.right.parent=gp;}
				r.right=gp;
				
				r.color=1;
				p.color=0;
				gp.color=0;
				
				
			}
			else if(gp.key.compareTo(p.key)<0&&p.key.compareTo(r.key)>0){
				
				r.parent=gp.parent;
				if(gp.parent!=null){
					if(gp.parent.key.compareTo(gp.key)>0){
						gp.parent.left=p;
					}
					else if(gp.parent.key.compareTo(gp.key)<0){
						gp.parent.right=p;
					}
				}
				else{
					root=r;
				}
				p.parent=r;
				p.left=r.right;
				if(r.right!=null){r.right.parent=p;}
				r.right=p;
				gp.parent=r;
				gp.right=r.left;
				if(r.left!=null){r.left.parent=gp;}
				r.left=gp;
				
				r.color=1;
				p.color=0;
				gp.color=0;
			}
			else{
				p.parent=gp.parent;
				if(gp.parent!=null){
					if(gp.parent.key.compareTo(gp.key)>0){
						gp.parent.left=p;
					}
					else if(gp.parent.key.compareTo(gp.key)<0){
						gp.parent.right=p;
					}
				}
				else{
					root=p;
				}
				gp.parent=p;
				gp.right=p.left;
				p.left=gp;
				p.color=1;
				gp.color=0;
			}
		}
				else if(gp.left.color==0&&gp.right.color==0){
					gp.right.color=1;
					gp.left.color=1;
					gp.color=0;
					Reconstruct(gp);
				}
				
				
			}
			
			else{
				if(gp.key.compareTo(p.key)>0&&p.key.compareTo(r.key)>0){
					//System.out.println("1");
					p.parent=gp.parent;
					if(gp.parent!=null){
						if(gp.parent.key.compareTo(gp.key)>0){
							gp.parent.left=p;
						}
						else if(gp.parent.key.compareTo(gp.key)<0){
							gp.parent.right=p;
						}
					}
					else{
						root=p;
					}
					gp.parent=p;
					gp.left=p.right;
					if(p.right!=null){
						p.right.parent=gp;
					}
					p.right=gp;
					p.color=1;
					gp.color=0;
				}
				else if(gp.key.compareTo(p.key)>0&&p.key.compareTo(r.key)<0){
					//System.out.println("h");
				//	System.out.println("...");
					p.right=r.left;
					if(r.left!=null){
						r.left.parent=p;
					}
					r.left=p;
					p.parent=r;
					r.parent=gp;
					gp.left=r;
					Reconstruct(p);
					
					//print();
					
					
				}
				else if(gp.key.compareTo(p.key)<0&&p.key.compareTo(r.key)>0){
				//	System.out.println("...");
					p.left=r.right;
					if(r.right!=null){
						r.right.parent=p;
					}
					r.right=p;
					p.parent=r;
					r.parent=gp;
					gp.right=r;
					Reconstruct(p);
					
				}
				else{
					p.parent=gp.parent;
					if(gp.parent!=null){
						if(gp.parent.key.compareTo(gp.key)>0){
							gp.parent.left=p;
						}
						else if(gp.parent.key.compareTo(gp.key)<0){
							gp.parent.right=p;
						}
					}
					else{
						root=p;
					}
					gp.parent=p;
					gp.right=p.left;
					p.left=gp;
					p.color=1;
					gp.color=0;
				}
			}
		}
    	}
		}
    }
    RedBlackNode<T, E> sch(RedBlackNode<T,E> r,T key){
    	//RedBlackNode<T,E> r=root;
    	
    	if(r!=null){
    	//	System.out.print(r.key.toString());
    	//	System.out.print(key.toString());
    	//	System.out.print(r.key.compareTo(key));
    		if(r.key.compareTo(key)==0){
    			
    		return r;
    		}
    		else if(r.key.compareTo(key)>0){
    			//System.out.print("l ");
    			
    			return sch(r.left,key);
    		}
    		else{
    			//System.out.print("r ");
    			return sch(r.right,key);
    		}
    	}
    	
        return null;
    	
    }

    
    public RedBlackNode<T, E> search(T key) {
    	
        return sch(root,key);
    }
    
    public void print(){
    	if(root==null){
    		return;
    	}
    	Queue<RedBlackNode<T,E>> q=new LinkedList<RedBlackNode<T,E>>();
    	q.add(root);
    	 while(true) 
         { 
               
             // nodeCount (queue size) indicates number of nodes 
             // at current level. 
             int nodeCount = q.size(); 
             if(nodeCount == 0) 
                 break; 
               
             // Dequeue all nodes of current level and Enqueue all 
             // nodes of next level 
             while(nodeCount > 0) 
             { 
                 RedBlackNode<T,E> node = q.peek(); 
                 System.out.print(node.getValue().toString() + " "+node.color); 
                 q.remove(); 
                 if(node.left != null) 
                     q.add(node.left); 
                 if(node.right != null) 
                     q.add(node.right); 
                 nodeCount--; 
             } 
             System.out.println(); 
         } 
    }
    
    
    
    
    
    
}