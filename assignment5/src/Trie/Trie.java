package Trie;

public class Trie<T> implements TrieInterface {

	
	TrieNode<T> root;
	public Trie(){
		this.root=new TrieNode();
	}
	public boolean insert(String word, Object value) {
		char[] chars=word.toCharArray();
		TrieNode<T> r=root;
		//System.out.println(word.length());
		for(int i=0;i<word.length();i++){
			int index=chars[i];
			if(r.childarr[index]==null){
				r.childarr[index]=new TrieNode();
				
				r.childarr[index].tillnow=r.tillnow+chars[i];
				
				//System.out.println(r.childarr[index].tillnow+"1");
				r=r.childarr[index];
			}
			else{
				
				
				r.childarr[index].tillnow=r.tillnow+chars[i];
				r=r.childarr[index];
				//System.out.println(r.tillnow);
				}
			if(i==word.length()-1){
				//System.out.println(r.value);
				r.value=(T)value;
				r.end=true;
				return true;
			}
		}
		return false;
	}

	
	public TrieNode<T> search(String word) {
		//System.out.println(word.length());
		char[] chars=word.toCharArray();
		TrieNode<T> r=root;
		int index=-1;
		 
		for(int i=0;i<word.length();i++){
			 index=chars[i];
			
			if(r.childarr[index]!=null){
				
					r=r.childarr[index];
				
			}
			else{//System.out.println("q");
				return null;}
			
		}
		if(index==-1){
			return root;                        //check this later
		}
		
		else{
			TrieNode<T> n=new TrieNode();
			n.tillnow=r.tillnow;
			//System.out.println(r.tillnow);
			n.value=r.value;
			n.end=r.end;
			n.value=n.value;
			return n;
		}
	}

	
	public TrieNode<T> startsWith(String prefix) {
		char[] chars=prefix.toCharArray();
		TrieNode<T> r=root;
		int index=-1;
		for(int i=0;i<prefix.length();i++){
			 index=chars[i];
			if(r.childarr[index]!=null){
					r=r.childarr[index];
				
			}
			else{return null;}
			
		}
		if(index==-1){
			return root;                        //check this later
		}
		
		else{
			return r;
		}
	}
	
	
	

	
	public void printTrie(TrieNode trieNode) {
		if(trieNode.end){
			System.out.println(trieNode.value.toString());
			
		}
		
		for(int i=0;i<128;i++){
			TrieNode<T> pointer=trieNode;
			if(pointer.childarr[i]!=null){
				printTrie(pointer.childarr[i]);
			}
		}
		
	}


	public boolean delete(String word) {
		char[] chars=word.toCharArray();
		
		TrieNode<T> r=root;
		
		for(int i=0;i<word.length();i++){
			int index=chars[i];
			
			if(r.childarr[index]==null){
				//System.out.println("ERROE DELETING");
				return false;
			}
			
			else{
				if(i==word.length()-1){
					if(r.childarr[index].end && r.childarr[index].havechild()){
						r.childarr[index].end=false;
						r.childarr[index]=null;
						//System.out.println("DELETED");
						return true;
					} 
					else if(r.childarr[index].end && ! r.childarr[index].havechild()){
					//	System.out.println("q");
						r.childarr[index]=null;
						int a=0;
						TrieNode<T> r1=root;
						for(int j=0;j<word.length()-a;j++){
							
							if(j==word.length()-a-2){
								//System.out.print("3");
								if(r1.childarr[chars[j]].end||r1==root||r1.childarr[chars[j]].havechild()){
									//System.out.println("DELETED");
									
									return true;
								}
								else{
									a++;
									r1.childarr[chars[j]]=null;
									r1=root;
									j=-1;
									
								}
							}
							else{
								//System.out.print("!"+j);
								
							r1=r1.childarr[chars[j]];
							}
						}					
					}
					else{
					//	System.out.println("ERROE DELETING");
						return false;}
					
					
				}
				else{
				r=r.childarr[index];
				}
			}
			
		}
		//System.out.println("ERROE DELETING");
		return false;
	}

	
	public void print() {
		//printTrie(root);
		
		System.out.println("-------------");
		System.out.println("Printing Trie");
		int level=1;
		while(true){
			printLevel(level++);
			boolean arrisnull=true;
			for(int i=0;i<128;i++){
				if(arr[i].freq!=0){arrisnull=false;}
			}
			if(arrisnull){
				break;
			}
		}
		System.out.println("-------------");
		
		
	}
	ArrNode[] arr=new ArrNode[128]; 
	
	
	
	void storefrom(int l,TrieNode<T> r){
		if(l==0){
			for(int x=0;x<128;x++){
				if(r.childarr[x]!=null){
					if(r.childarr[x].getchar()!=' '){
					arr[x].freq++;
					arr[x].character=r.childarr[x].getchar();
					}
				}
			}
			
		}
		else if(l>0){
			for(int i=0;i<128;i++){
				 if(r.childarr[i]!=null){
				    	storefrom(l-1,r.childarr[i]);
				 }
			}
		}
		
		
	}
	

	public void printLevel(int level) {
		
		System.out.print("Level "+level+": ");
		level--;
		for(int q=0;q<128;q++){
			arr[q]=new ArrNode();
			
		}
		TrieNode<T> r=root;
		//int height=1;
		if(level>0){
			for(int i=0;i<128;i++ ){
				if(r.childarr[i]!=null){
		    	storefrom(level-1,r.childarr[i]);
				}
		    	
			}
		}
		else if(level==0){
			for(int x=0;x<128;x++){
				if(r.childarr[x]!=null){
					if(r.childarr[x].getchar()!=' '){
					arr[x].freq++;
					arr[x].character=r.childarr[x].getchar();
					}
				}
			}
		}
		
		//Printing
		int s=0;
		for(int i=0;i<128;i++){
			
			for(int j=0;j<arr[i].freq;j++){
				if(s==0){
					System.out.print(arr[i].character);
				}
				else{
					System.out.print(","+arr[i].character);
				}
				s=1;
			}
		}
		System.out.println("");
		    
	}
}
