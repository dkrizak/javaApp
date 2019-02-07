package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja obrazovnu ustanovu definiranu nazivom, predmetima, profesorima, studentima i ispitima.
 * 
 * @author Denis Križak
 *
 */
public abstract class ObrazovnaUstanova extends Entitet {
	
	private static final long serialVersionUID = -2206734259436007520L;
	private String nazivUstanove;
	private List<Predmet> predmeti = new ArrayList<>();
	private List<Profesor> profesori = new ArrayList<>();
	private List<Student> studenti = new ArrayList<>();
	private List<Ispit> ispiti = new ArrayList<>();
	/**
	 * Inicijalizira podatke o ustanovi.
	 * 
	 * @param nazivUstanove naziv obrazovne ustanove.
	 * @param predmeti predmeti koji se mogu pohaðati u ustanovi.
	 * @param profesori profesori koji predaju u ustanovi.
	 * @param studenti studenti koji su upisani u ustanovu.
	 * @param ispiti ispiti koji su se polagali u ustanovi.
	 */
	public ObrazovnaUstanova(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**
	 * Dohvaæa naziv obrazovne ustanove.
	 * 
	 * @return naziv obrazovne ustanove.
	 */
	public String getNazivUstanove() {
		return nazivUstanove;
	}
	/**
	 * Postavlja naziv obrazovne ustanove.
	 * 
	 * @param nazivUstanove naziv obrazovne ustanove.
	 */
	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}
	/**
	 * Dohvaæa predmete koji se predaju u ustanovi.
	 * 
	 * @return predmeti koji se predaju u ustanovi.
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	/**
	 * Postavlja predmete koji se predaju u ustanovi.
	 * 
	 * @param predmeti predmeti koji se predaju u ustanovi.
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	/**
	 * Dohvaæa profesore koji predaju u ustanovi.
	 * 
	 * @return profesore koji predaju u ustanovi.
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}
	/**
	 * Postavlja profesore koji predaju u ustanovi.
	 * 
	 * @param profesori profesori koji predaju u ustanovi.
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	/**
	 * Dohvaæa studente koji su upisani u ustanovi.
	 * 
	 * @return studente koji su upisani u ustanovi.
	 */
	public List<Student> getStudenti() {
		return studenti;
	}
	/**
	 * Postavlja studente koji su upisani u ustanovi.
	 * 
	 * @param studenti studenti koji su upisani u ustanovi.
	 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	/**
	 * Dohvaæa ispite koji su pisani u ustanovi.
	 * 
	 * @return ispite koji su pisani u ustanovi.
	 */
	public List<Ispit> getIspiti() {
		return ispiti;
	}
	/**
	 * Postavlja ispite koji su pisani u ustanovi.
	 * 
	 * @param ispiti ispiti koji su pisani u ustanovi.
	 */
	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}
	
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina);
}
