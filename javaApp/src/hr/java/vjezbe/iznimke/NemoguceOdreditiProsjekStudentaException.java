package hr.java.vjezbe.iznimke;
/**
 * Predstavlja iznimku koja se baca pri nemoguænosti izraèuna prosjeka studenta.
 * 
 * @author Denis Križak
 *
 */
public class NemoguceOdreditiProsjekStudentaException extends Exception{
	
	private static final long serialVersionUID = 2354675407061208204L;
	
	public NemoguceOdreditiProsjekStudentaException() {
		super("Nemoguce odrediti prosjek studenta!");
	}
	
	public NemoguceOdreditiProsjekStudentaException(String message) {
		super(message);
	}
	
	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}
	
	public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
		super(message, cause);
	}
}
