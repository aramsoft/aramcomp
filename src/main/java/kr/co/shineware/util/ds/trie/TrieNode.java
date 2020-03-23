package kr.co.shineware.util.ds.trie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TrieNode {

	private char key;
	private TrieNode[] children;
	private Object value;

	public TrieNode[] getChildren() {
		return children;
	}
	public void setChildren(TrieNode[] children){
		this.children = children;
	}
	
	public char getKey() {
		return key;
	}
	public void setKey(char key) {
		this.key = key;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getValue(){
		return value;
	}
	
	public void save(String filename) {
		ObjectOutputStream dos;
		try {
			dos = new ObjectOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(filename))));
			write(dos);
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void write(ObjectOutputStream dos) throws Exception {
		dos.writeChar(key);
		dos.writeObject(value);		
		if(children == null) {
			dos.writeInt(0);
		} else {
			dos.writeInt(children.length);
			for(int i=0; i<children.length; i++) {
				children[i].write(dos);
			}
		}
	}
	
	public void load(String filename) {
		ObjectInputStream dis;
		try {			
			dis = new ObjectInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(filename))));
			read(dis);
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read(ObjectInputStream dis) throws Exception {
		key = dis.readChar();		
		value = dis.readObject();
		int length = dis.readInt();
		if(length != 0){
			children = new TrieNode[length];
		}
		for(int i=0; i<length; i++) {
			children[i] = new TrieNode();
			children[i].read(dis);
		}
	}

}
