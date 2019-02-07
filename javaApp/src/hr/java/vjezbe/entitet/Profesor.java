package hr.java.vjezbe.entitet;
/**
 * Predstavlja profesora koji je definiran šifrom, imenom, prezimenom i titulom.
 * 
 * @author Denis Križak
 *
 */
public class Profesor extends Osoba{
	
	private static final long serialVersionUID = -1213882983260791233L;
	private String sifra, titula;
	/**
	 * Inicijalizira podatke o profesoru.
	 * 
	 * @param sifra šifra profesora.
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
	 * Dohvaæa šifru profesora.
	 * 
	 * @return šifru profesora.
	 */
	public String getSifra() {
		return sifra;
	}
	/**
	 * Postavlja šifru na novu vrijednost.
	 * 
	 * @param sifra nova vrijednost šifre.
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**
	 * Dohvaæa titulu profesora.
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
