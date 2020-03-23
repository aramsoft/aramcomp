package kr.co.shineware.util.common.model;

public class Jaso {
	
	public enum TYPE{
		CHOSUNG, JUNGSUNG, JONGSUNG, ETC
	}
	private char jaso;
	private int index;
	private TYPE type;
	
	public Jaso(){
		;
	}
	
	public Jaso(char jaso,TYPE type,int index){
		this.jaso = jaso;
		this.type = type;
		this.index = index;
	}
	
	public char getJaso() {
		return jaso;
	}
	public void setJaso(char jaso) {
		this.jaso = jaso;
	}
	
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toKey(){
		StringBuffer sb = new StringBuffer();		
		sb.append(index*10);
		sb.append(type.ordinal());
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Jaso [jaso=" + jaso 
				+ ", index=" + index 
				+ ", type=" + type	
				+ "]";
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result + index*10;
		result = result + ((type == null) ? 0 : type.ordinal());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jaso other = (Jaso) obj;
		if (index != other.index)
			return false;
		if (jaso != other.jaso)
			return false;
		if (type != other.type)
			return false;
		return true;
	}	
	
}
