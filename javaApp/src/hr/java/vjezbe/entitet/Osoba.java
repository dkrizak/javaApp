package hr.java.vjezbe.entitet;
/**
 * Predstavlja osobu definiranu imenom i prezimenom.
 * 
 * @author Denis Kri�ak
 *
 */
public abstract class Osoba extends Entitet{
	
	private static final long serialVersionUID = -6700775030123806533L;
	private String ime;
	private String prezime;
	/**
	 * Inicijalizira podatke o osobi.
	 * 
	 * @param ime ime osobe.
	 * @param prezime prezime osobe.
	 */
	public Osoba(Long id, String ime, String prezime) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
	}
	/**
	 * Dohva�a ime osobe.
	 * 
	 * @return ime osobe.
	 */
	public String getIme() {
		return ime;
	}
	/**
	 * Postavlja ime osobe.
	 * 
	 * @param ime ime osobe.
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**
	 * Dohva�a prezime osobe.
	 * 
	 * @return prezime osobe.
	 */
	public String getPrezime() {
		return prezime;
	}
	/**
	 * Postavlja prezime osobe.
	 * 
	 * @param prezime prezime osobe.
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}	
}
