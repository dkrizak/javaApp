package hr.java.vjezbe.entitet;

import java.util.HashSet;
import java.util.Set;

/**
 * Predstavlja predmet koji je definiran šifrom, nazivom, brojem ECTS-a, nositeljem predmeta
 * te brojem upisanih studenata.
 * 
 * @author Denis Križak
 *
 */
public class Predmet extends Entitet{
	
	private static final long serialVersionUID = -1223190242096776912L;
	private String sifra, naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Set<Student> studenti = new HashSet<Student>();
	/**
	 * Inicijalizira podatke o predmetu.
	 * 
	 * @param sifra šifra predmeta.
	 * @param naziv naziv predmeta.
	 * @param brojEctsBodova broj ECTS bodova predmeta.
	 * @param nositelj nositelj predmeta.
	 * @param brojStudenata broj studenata upisanih na predmet.
	 */
	public Predmet(Long id, String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj) {
		super(id);
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
	}
	/**
	 * Dohvaæa šifru predmeta.
	 * 
	 * @return šifru predmeta.
	 */
	public String getSifra() {
		return sifra;
	}
	/**
	 * Postavlja novu vrijednost šifre predmeta.
	 * 
	 * @param sifra nova vrijednost šifre predmeta.
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**
	 * Dohvaæa naziv predmeta.
	 * 
	 * @return naziv predmeta.
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * Postavlja novi naziv predmeta.
	 * 
	 * @param naziv novi naziv predmeta.
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	/**
	 * Dohvaæa broj ECTS bodova predmeta.
	 * 
	 * @return broj ECTS bodova predmeta.
	 */
	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}
	/**
	 * Postavlja novu vrijednost ECTS bodova predmeta.
	 * 
	 * @param brojEctsBodova nova vrijednost ECTS bodova predmeta.
	 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}
	/**
	 * Dohvaæa nositelja predmeta.
	 * 
	 * @return nositelja predmeta.
	 */
	public Profesor getNositelj() {
		return nositelj;
	}
	/**
	 * Postavlja novu vrijednost nositelja predmeta.
	 * 
	 * @param nositelj novi nositelj predmeta.
	 */
	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}
	/**
	 * Dohvaæa polje studenata koji su upisali predmet.
	 * 
	 * @return polje studenata koji su upisali predmet.
	 */
	public Set<Student> getStudenti() {
		return studenti;
	}
	/**
	 * Postavlja novo polje upisanih studenata na predmet.
	 * 
	 * @param studenti polje upisanih studenata.
	 */
	public void setStudenti(Set<Student> studenti) {
		this.studenti = studenti;
	}

}
