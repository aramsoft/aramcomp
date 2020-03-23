package kr.co.shineware.util.common.model;

public class Syllable {
	
	private char chosung;
	private char jungsung;
	private char jongsung;
	private int chosungIndex;
	private int jungsungIndex;
	private int jongsungIndex;
	
	public Syllable(char cho, int choIndex, char jung, int jungIndex, char jong, int jongIndex){
		
		setChosung(cho);
		setJungsung(jung);
		setJongsung(jong);
		
		setChosungIndex(choIndex);
		setJungsungIndex(jungIndex);
		setJongsungIndex(jongIndex);
	}
	
	public char getChosung() {
		return chosung;
	}
	public void setChosung(char chosung) {
		this.chosung = chosung;
	}
	
	public char getJungsung() {
		return jungsung;
	}
	public void setJungsung(char jungsung) {
		this.jungsung = jungsung;
	}
	
	public char getJongsung() {
		return jongsung;
	}
	public void setJongsung(char jongsung) {
		this.jongsung = jongsung;
	}

	public int getChosungIndex() {
		return chosungIndex;
	}
	public void setChosungIndex(int chosungIndex) {
		this.chosungIndex = chosungIndex;
	}
	
	public int getJungsungIndex() {
		return jungsungIndex;
	}
	public void setJungsungIndex(int jungsungIndex) {
		this.jungsungIndex = jungsungIndex;
	}
	
	public int getJongsungIndex() {
		return jongsungIndex;
	}
	public void setJongsungIndex(int jongsungIndex) {
		this.jongsungIndex = jongsungIndex;
	}

	@Override
	public String toString() {
		return "Jaso [chosung=" + chosung 
				+ ", jungsung=" + jungsung 
				+ ", jongsung=" + jongsung 
				+ ", chosungIndex=" + chosungIndex 
				+ ", jungsungIndex=" + jungsungIndex 
				+ ", jongsungIndex=" + jongsungIndex 
				+ "]";
	}
		
}
