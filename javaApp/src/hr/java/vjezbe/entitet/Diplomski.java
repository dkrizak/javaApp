package hr.java.vjezbe.entitet;
/**
 * Dodaje ustanovi diplomski program.
 * 
 * @author Denis Križak
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odreðuje studenta koji je diobio rektorovu nagradu.
	 * 
	 * @return student koji je osvojio rektorovu nagradu.
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
