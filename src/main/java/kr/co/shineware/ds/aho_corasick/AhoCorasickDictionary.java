package kr.co.shineware.ds.aho_corasick;

import java.util.*;

import kr.co.shineware.ds.aho_corasick.model.AhoCorasickNode;

public class AhoCorasickDictionary<V> {

	private AhoCorasickNode<V> root;

	public AhoCorasickDictionary(){
		this.root = new AhoCorasickNode<>();
		this.root.setDepth(0);
	}

	public void load(String filename){
		root.load(filename);
	}
	
	public void save(String filename){
		root.save(filename);
	}

	public void put(String keys, V value){
		this.put(keys.toCharArray(), value);
	}

	@SuppressWarnings("unchecked")
	private void put(char[] keys, V value) {
		AhoCorasickNode<V> currentNode = this.root;
		for(int i=0;i<keys.length;i++){
			char key = keys[i];

			AhoCorasickNode<V>[] children = currentNode.getChildren();

			if(children == null){
				children = new AhoCorasickNode[1];
				AhoCorasickNode<V> initNode = new AhoCorasickNode<>();
				initNode.setParent(currentNode);
				initNode.setDepth(i+1);
				initNode.setKey(key);
				children[0] = initNode;
				currentNode.setChildren(children);
				currentNode = currentNode.getChildren()[0];
			}else{
				//retrieve children to find index.
				int idx = this.retrieveNode(children,key);
				//if children has no key.
				if(idx == -1){
					int head = 0;
					int tail = children.length-1;
					idx = 0;

					while(head<=tail){
						idx = (head+tail)/2;
						if(children[idx].getKey() < key){
							head = idx+1;
						}else if(children[idx].getKey() > key){
							tail = idx-1;
						}else if(children[idx].getKey() == key){
							break;
						}			
					}

					AhoCorasickNode<V>[] newArray = new AhoCorasickNode[children.length + 1];
					System.arraycopy(children, 0, newArray, 0, head);
					newArray[head] = new AhoCorasickNode<V>();
					newArray[head].setParent(currentNode);
					newArray[head].setDepth(i+1);
					newArray[head].setKey(key);
					System.arraycopy(children, head, newArray, head+1, children.length-head);
					currentNode.setChildren(newArray);
					idx = head;
				}
				currentNode = currentNode.getChildren()[idx];
			}
		}
		currentNode.setValue(value);
	}
	
	private int retrieveNode(AhoCorasickNode<V>[] children, char key) {
		int head = 0;
		int tail = children.length-1;
		int idx = 0;
		while(head<=tail){
			idx = (head+tail)/2;
			if(children[idx].getKey() < key){
				head = idx+1;
			}else if(children[idx].getKey() > key){
				tail = idx-1;
			}else if(children[idx].getKey() == key){
				return idx;
			}
		}
		return -1;
	}

	public V getValue(String keys){
		return this.getValue(keys.toCharArray());
	}
	public V getValue(char[] keys){
		AhoCorasickNode<V> node = this.root;		
		for(int i=0;i<keys.length;i++){
			char key = keys[i];
			AhoCorasickNode<V>[] children = node.getChildren();
			if(children == null){
				return null;
			}

			int idx = retrieveNode(children, key);
			if(idx == -1){
				return null;
			}
			node = children[idx];
		}
		return node.getValue();
	}
	
	public boolean hasChild(char[] keys){
		AhoCorasickNode<V> node = this.root;		
		for(int i=0;i<keys.length;i++){
			char key = keys[i];
			AhoCorasickNode<V>[] children = node.getChildren();
			if(children == null){
				return false;
			}

			int idx = retrieveNode(children, key);
			if(idx == -1){
				return false;
			}
			node = children[idx];
		}
		return node.getChildren() != null;
	}

	public Map<String,V> get(FindContext<V> context, char key) {
		final Map<String,V> resultMap = new HashMap<>();

		while (true) {
			final AhoCorasickNode<V>[] children = context.getCurrentChildren();

			if (children == null) {
				final AhoCorasickNode<V> currentNode = context.getCurrentFailNode();
				if (currentNode == null) {
					return new HashMap<>();
				} else {
					context.setCurrentNode(currentNode);
				}
				continue;
			}

			final int idx = this.retrieveNode(children, key);
			if (idx == -1) {
				if (context.getCurrentNode() != this.root) {
					context.setCurrentNode(context.getCurrentFailNode());
					continue;
				}
			} else {
				AhoCorasickNode<V> childNode = children[idx];

				if (childNode.getValue() != null) {
					resultMap.put(this.getKeyFromNode(childNode), childNode.getValue());
				}

				while (childNode.getFailNode() != null) {
					if(childNode.getFailNode().getValue() != null){
						resultMap.put(this.getKeyFromNode(childNode.getFailNode()), childNode.getFailNode().getValue());
					}
					childNode = childNode.getFailNode();
				}
				context.setCurrentNode(children[idx]);
			}
			break;
		}

		return resultMap;
	}	

	private String getKeyFromNode(AhoCorasickNode<V> childNode){
		AhoCorasickNode<V> currentNode = childNode;
		String key = "";
		while(currentNode != this.root){
			key = currentNode.getKey()+key;
			currentNode = currentNode.getParent();
		}
		return key;
	}

	public FindContext<V> newFindContext() {
		return new FindContext<>(this.root);
	}

	public Map<String, V> get(String keys) {
		return this.get(newFindContext(), keys);
	}

	public Map<String, V> get(char[] keys) {
		return this.get(newFindContext(), keys);
	}

	public Map<String, V> get(char key) {
		return this.get(newFindContext(), key);
	}

	public Map<String,V> get(FindContext<V> context, String keys){
		return this.get(context, keys.toCharArray());
	}

//	public Map<String,V> getorigin(FindContext<V> context, char[] keys) {
//		final Map<String,V> resultMap = new HashMap<>();
//
//		for (int i = 0; i < keys.length; i++) {
//			resultMap.putAll(get(context, keys[i]));
//		}
//		return resultMap;
//	}

	public Map<String,V> get(FindContext<V> context, char[] keys) {
		Map<String,V> resultMap = new HashMap<>();
		if(context.getCurrentNode() == null){
			context.setCurrentNode(this.root);
		}
		for(int i=0;i<keys.length;i++){
			char key = keys[i];
			if(context.getCurrentNode().getChildren() == null){
				context.setCurrentNode(context.getCurrentNode().getFailNode());
				i--;
				continue;
			}
			int idx = this.retrieveNode(context.getCurrentChildren(),key);
			if(idx == -1){
				if(context.getCurrentNode() == this.root){
					context.setCurrentNode(this.root);
				}else{
					context.setCurrentNode(context.getCurrentNode().getFailNode());
					i--;
				}
			}else{
				AhoCorasickNode<V> childNode = context.getCurrentChildren()[idx];
				if(childNode.getValue() != null){
					resultMap.put(this.getKeyFromNode(childNode), childNode.getValue());
				}
				while(childNode.getFailNode() != null){
					if(childNode.getFailNode().getValue() != null){
						resultMap.put(this.getKeyFromNode(childNode.getFailNode()), childNode.getFailNode().getValue());
					}
					childNode = childNode.getFailNode();
				}
				context.setCurrentNode(context.getCurrentChildren()[idx]);
			}
		}
		return resultMap;
	}

	@SuppressWarnings("unused")
	private void printNodeAndValue(AhoCorasickNode<V> childNode) {
		AhoCorasickNode<V> currentNode = childNode;
		String key = "";
		while(currentNode != this.root){
			key = currentNode.getKey()+key;
			currentNode = currentNode.getParent();
		}
		System.out.println("key : "+key);
		System.out.println("value : "+childNode.getValue());
	}

	public void buildFailLink() {
		AhoCorasickNode<V> currentNode = this.root;
		Queue<AhoCorasickNode<V>> queue = new LinkedList<>();
		queue.clear();
		queue.add(currentNode);

		while(!queue.isEmpty()){
			currentNode = queue.remove();
			this.linkFailNode(currentNode);
			if(currentNode.getChildren() == null)continue;
			if(currentNode.getChildren().length != 0){
				this.insertNodes(queue,currentNode.getChildren());
			}
		}
	}

	private void linkFailNode(AhoCorasickNode<V> currentNode) {
		if(currentNode == this.root){
			;
		}
		else if(currentNode.getParent() == this.root){
			currentNode.setFailNode(this.root);
		}
		else{
			AhoCorasickNode<V> travaseNode = currentNode.getParent().getFailNode();
			while(travaseNode != this.root){
				if(travaseNode.getChildren() == null){
					travaseNode = travaseNode.getFailNode();
					continue;
				}
				//
				int idx = this.retrieveNode(travaseNode.getChildren(),currentNode.getKey());				
				//
				if(idx != -1){
					currentNode.setFailNode(travaseNode.getChildren()[idx]);
					break;
				}
				travaseNode = travaseNode.getFailNode();
			}
			if(currentNode.getFailNode() == null){
				int idx = this.retrieveNode(this.root.getChildren(),currentNode.getKey());
				if(idx != -1){
					AhoCorasickNode<V> rootChildNode = this.root.getChildren()[idx];
					currentNode.setFailNode(rootChildNode);
				}else{
					currentNode.setFailNode(this.root);
				}
			}
		}
	}

	public void travaseNodes(){
		AhoCorasickNode<V> currentNode = this.root;
		Queue<AhoCorasickNode<V>> queue = new LinkedList<>();
		queue.clear();
		queue.add(currentNode);
		Map<Integer,List<AhoCorasickNode<V>>> depthKeyMap = new HashMap<Integer, List<AhoCorasickNode<V>>>();
		while(!queue.isEmpty()){
			currentNode = queue.remove();			
			this.logNode(depthKeyMap,currentNode);
			if(currentNode.getChildren() == null)continue;
			if(currentNode.getChildren().length != 0){
				insertNodes(queue,currentNode.getChildren());
			}
		}
		for(int i=0;;i++){
			List<AhoCorasickNode<V>> keyList = depthKeyMap.get(i);
			if(keyList == null)break;
			String keys = "";
			for (AhoCorasickNode<V> ahoCorasickNode : keyList) {
				String failNode = "";
				if(ahoCorasickNode.getDepth() != 0){
					failNode = "("+ahoCorasickNode.getFailNode().getDepth()+":"+ahoCorasickNode.getFailNode().getKey()+")";
				}
				keys += ahoCorasickNode.getKey()+failNode+", ";
			}
			System.out.println("["+i+"]"+keys);
		}
	}

	private void logNode(Map<Integer, List<AhoCorasickNode<V>>> depthKeyMap, AhoCorasickNode<V> currentNode) {
		List<AhoCorasickNode<V>> keyList = depthKeyMap.get(currentNode.getDepth());
		if(keyList == null){
			keyList = new ArrayList<AhoCorasickNode<V>>();
		}
		keyList.add(currentNode);
		depthKeyMap.put(currentNode.getDepth(), keyList);
	}

	private void insertNodes(Queue<AhoCorasickNode<V>> queue, AhoCorasickNode<V>[] ahoCorasickNodes) {
		for (AhoCorasickNode<V> ahoCorasickNode : ahoCorasickNodes) {
			queue.add(ahoCorasickNode);
		}
	}
	
}
