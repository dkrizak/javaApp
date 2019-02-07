package hr.java.vjezbe.entitet;

public enum Ocjena {
	nedovoljan(1), dovoljan(2), dobar(3), vrlodobar(4), izvrstan(5);
	
	private int ocjena;
	
	Ocjena(int ocjena) {
		this.ocjena = ocjena;
	}
	
	public int getOcjena() {
		return ocjena;
	}
}
