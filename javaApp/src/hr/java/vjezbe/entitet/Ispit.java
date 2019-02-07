package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;
/**
 * Predstavlja ispit definiran predmetom, studentom, ocjenom te datumom i vremenom.
 * 
 * @author Denis Kri�ak
 *
 */
public class Ispit extends Entitet{
	
	private static final long serialVersionUID = 1489273497640366466L;
	private Predmet predmet;
	private Student student;
	private Ocjena ocjena;
	private LocalDateTime datumIVrijeme;
	/**
	 * Inicijalizira podatke o ispitu.
	 * 
	 * @param predmet predmet iz kojeg je pisan ispit.
	 * @param student student koji je pisao ispit.
	 * @param ocjena ocjena koju je student dobio na ispitu.
	 * @param datumIVrijeme datum i vrijeme pisanja ispita.
	 */
	public Ispit(Long id, Predmet predmet, Student student, Ocjena ocjena, LocalDateTime datumIVrijeme) {
		super(id);
		this.predmet = predmet;
		this.student = student;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}
	/**
	 * Dohva�a predmet iz kojeg je pisan ispit.
	 * 
	 * @return predmet iz kojeg je pisan ispit.
	 */
	public Predmet getPredmet() {
		return predmet;
	}
	/**
	 * Postavlja predmet iz kojeg se pi�e ispit.
	 * 
	 * @param predmet predmet iz kojeg se pi�e ispit.
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	/**
	 * Dohva�a studenta koji je pisao ispit.
	 * 
	 * @return studenta koji je pisao ispit.
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * Postavlja studenta koji pi�e ispit.
	 * 
	 * @param student student koji pi�e ispit.
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * Dohva�a ocjenu koju je student dobio iz ispita.
	 * 
	 * @return ocjenu koju je student dobio iz ispita.
	 */
	public Ocjena getOcjena() {
		return ocjena;
	}
	/**
	 * Postavlja ocjenu koju je student dobio na ispitu.
	 * 
	 * @param ocjena ocjena koju je student dobio na ispitu.
	 */
	public void setOcjena(Ocjena ocjena) {
		this.ocjena = ocjena;
	}
	/**
	 * dohva�a datum i vrijeme pisanja ispita.
	 * 
	 * @return datum i vrijeme pisanja ispita.
	 */
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}
	/**
	 * Postavlja datum i vrijeme pisanja ispita u formatu dd.mm.yyyy.Thh:mm.
	 * 
	 * @param datumIVrijeme datum i vrijeme pisanja ispita.
	 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}	

}
