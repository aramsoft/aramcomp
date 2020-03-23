package kr.co.shineware.util.ds.trie;

public class TrieDictionary {

	private TrieNode root;
	
	public TrieDictionary(){
		root = new TrieNode();
	}
	public TrieNode getRoot(){
		return root;
	}
	
	public void put(String str,Object i){
		this.put(str.toCharArray(),i);
	}
	
	public void put(char[] keys, Object value){
		
		TrieNode node = root;
		TrieNode[] children = null;
		for(int i=0;i<keys.length;i++){
			
			char key = keys[i];
			
			children = node.getChildren();
			if(children == null){
				node.setChildren(initNode(key));
				node = node.getChildren()[0];
			}
			else{
				int idx = retrieveNode(children,key);
				if(idx == -1){
					int head = 0;
					int tail = children.length-1;
					int index = 0;

					while(head<=tail){
						index = (head+tail)/2;
						if(children[index].getKey() < key){
							head = index+1;
						}else if(children[index].getKey() > key){
							tail = index-1;
						}else if(children[index].getKey() == key){
							break;
						}			
					}
					TrieNode[] newArray = new TrieNode[children.length + 1];
					System.arraycopy(children, 0, newArray, 0, head);
					newArray[head] = new TrieNode();
					newArray[head].setKey(key);
					System.arraycopy(children, head, newArray, head+1, children.length-head);
					node.setChildren(newArray);
					idx = head;
				}
				node = node.getChildren()[idx];
			}
		}
		node.setValue(value);
	}
	
	public TrieGetResult get(String str){
		return this.get(str.toCharArray());
	}
	
	public TrieGetResult get(char[] keys){
		TrieNode node = root;
		for(int i=0;i<keys.length;i++){
			char key = keys[i];
			TrieNode[] children = node.getChildren();
			if(children == null){				
				return new TrieGetResult(null, false);
			}
			int idx = retrieveNode(children,key);
			if(idx == -1){
				return new TrieGetResult(null, false);
			}			
			node = children[idx];
		}
		
		if(node.getChildren() == null){
			return new TrieGetResult(node.getValue(), false);
		}else{
			return new TrieGetResult(node.getValue(), true);
		}
	}
	
	private int retrieveNode(TrieNode[] children, char key) {
		
		int head = 0;
		int tail = children.length-1;
		int index = 0;
		while(head<=tail){
			index = (head+tail)/2;
			if(children[index].getKey() < key){
				head = index+1;
			}else if(children[index].getKey() > key){
				tail = index-1;
			}else if(children[index].getKey() == key){
				return index;
			}		
		}	
		return -1;
	}
	
	private TrieNode[] initNode(char key) {
		TrieNode[] nodes = new TrieNode[1];
		nodes[0] = new TrieNode();
		nodes[0].setKey(key);
		return nodes;
	}
	
	public void save(String filename){
		root.save(filename);
	}
	
	public void load(String filename){
		root.load(filename);
	}
	
}
