package hr.java.vjezbe.entitet;
/**
 * Predstavlja profesora koji je definiran �ifrom, imenom, prezimenom i titulom.
 * 
 * @author Denis Kri�ak
 *
 */
public class Profesor extends Osoba{
	
	private static final long serialVersionUID = -1213882983260791233L;
	private String sifra, titula;
	/**
	 * Inicijalizira podatke o profesoru.
	 * 
	 * @param sifra �ifra profesora.
	 * @param ime ime profesora.
	 * @param prezime prezime profesora.
	 * @param titula titula profesora u obrazovnoj ustanovi.
	 */
	public Profesor(Long id, String sifra, String ime, String prezime, String titula) {
		super(id, ime, prezime);
		this.sifra = sifra;
		this.titula = titula;
	}
	/**
	 * Dohva�a �ifru profesora.
	 * 
	 * @return �ifru profesora.
	 */
	public String getSifra() {
		return sifra;
	}
	/**
	 * Postavlja �ifru na novu vrijednost.
	 * 
	 * @param sifra nova vrijednost �ifre.
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**
	 * Dohva�a titulu profesora.
	 * 
	 * @return titulu profesora.
	 */
	public String getTitula() {
		return titula;
	}
	/**
	 * Postavlja vrijednost titule na novu vrijednost.
	 * 
	 * @param titula nova vrijednost titule.
	 */
	public void setTitula(String titula) {
		this.titula = titula;
	}

}
