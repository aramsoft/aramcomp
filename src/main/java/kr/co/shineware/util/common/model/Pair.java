package kr.co.shineware.util.common.model;

import java.io.Serializable;

/**
 * 
 * @author shin
 *
 * @param <F> Object type of first value;
 * @param <S> Object type of second value;
 */
public class Pair<F,S> implements Serializable{

	private static final long serialVersionUID = 1L;

	private F first;
	private S second;	
	
	public Pair(){
		;
	}
	
	public Pair(F first,S second){
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return first;
	}
	public void setFirst(F first) {
		this.first = first;
	}
	
	public S getSecond() {
		return second;
	}
	public void setSecond(S second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair [first=" + first 
				+ ", second=" + second 
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
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
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
		
}
