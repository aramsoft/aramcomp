package kr.co.shineware.util.ds.trie;

public class TrieGetResult {
	
	private Object value;
	private boolean hasNext;
	
	public TrieGetResult(Object value, boolean hasNext)	{
		setValue(value);
		setHasNext(hasNext);
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean hasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	@Override
	public String toString() {
		return "TrieGetResult [value=" + value 
				+ ", hasNext=" + hasNext 
				+ "]";
	}
	
}
