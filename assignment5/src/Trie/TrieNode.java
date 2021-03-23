package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {

    T value;
    String tillnow;
    TrieNode<T>[] childarr=new TrieNode[128];;// chech here 128
    boolean end;
    
    TrieNode(){
    	this.value = null;
    	for(int i=0;i<128;i++){
    		childarr[i]=null;
    	}
    	tillnow="";
    	end=false;
    }
    public T getValue() {
        return this.value;
    }
    public char getchar(){
    	return tillnow.charAt(tillnow.length()-1);
    }
    
    public boolean havechild(){
    	for(int i=0;i<128;i++){
    		if(childarr[i]!=null){
    			return true;
    		}
    		
    	}
    	return false;
    }

}