package hr.java.vjezbe.entitet;
/**
 * Dodaje ustanovi diplomski program.
 * 
 * @author Denis Kri�ak
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Odre�uje studenta koji je diobio rektorovu nagradu.
	 * 
	 * @return student koji je osvojio rektorovu nagradu.
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
