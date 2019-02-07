package hr.java.vjezbe.entitet;

import java.time.LocalDate;
/**
 * Predstavlja studenta koji je definiran imenom, prezimenom, jmbag-om te datumom roðenja.
 * 
 * @author Denis Križak
 *
 */
public class Student extends Osoba {
	
	private static final long serialVersionUID = 5191536960000581032L;
	private String jmbag;
	private LocalDate datumRodjenja;
	/**
	 * Inicijalizira podatke o studentu.
	 * 
	 * @param ime ime studenta.
	 * @param prezime prezime studenta.
	 * @param jmbag jmbag studenta.
	 * @param datumRodjenja datum roðenja studenta.
	 */
	public Student (Long id, String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
		super(id, ime, prezime);
		this.jmbag = jmbag;
		this.datumRodjenja = datumRodjenja;
	}
	/**
	 * Dohvaæa jmbag studenta.
	 * 
	 * @return jmbag studenta.
	 */
	public String getJmbag() {
		return jmbag;
	}
	/**
	 * Postavlja jmbag na novu vrijednost.
	 * 
	 * @param jmbag vrijednost novog jmbag-a.
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}
	/**
	 * Dohvaæa datum roðenja studenta.
	 * 
	 * @return datum roðenja studenta.
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	/**
	 * Postavlja datum roðenja studenta na novu vrijednost.
	 * 
	 * @param datumRodjenja vrijednost novog datuma roðenja.
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodjenja == null) ? 0 : datumRodjenja.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
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
		Student other = (Student) obj;
		if (datumRodjenja == null) {
			if (other.datumRodjenja != null)
				return false;
		} else if (!datumRodjenja.equals(other.datumRodjenja))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}

}
