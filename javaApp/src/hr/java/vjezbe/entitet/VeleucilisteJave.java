package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
/**
 * Predstavlja veleuèilište jave definirano nazivom, predmetima, profesorima, studentima i ispitima.
 * 
 * @author Denis Križak
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
	
	private static final long serialVersionUID = 144694252454246903L;
	/**
	 * Inicijalizira podatke veleuèilišta.
	 * 
	 * @param nazivUstanove naziv veleuèilišta.
	 * @param predmeti predmeti koji se održavaju na veleuèilištu.
	 * @param profesori profesori koji predaju na veleuèilištu.
	 * @param studenti studenti koji pohaÄ‘aju veleuèilište.
	 * @param ispiti ispiti koji su odrÅ¾ani na veleuèilištu.
	 */
	public VeleucilisteJave(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id, nazivUstanove, predmeti, profesori, studenti, ispiti);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);
	/**
	 * Izraèunava konaènu ocjenu studija za studenta.
	 * 
	 * @param ispiti ispiti koje je student polagao.
	 * @param zavrsniRad ocjena koju je student dobio iz završnog rada.
	 * @param obrana ocjena koju je student dobio iz obrane završnog rada.
	 * @return konaènu ocjenu studija za studenta.
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int zavrsniRad, int obrana) {
		
		BigDecimal prosjek = new BigDecimal(0);
		try {
			prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
		}
		catch (NemoguceOdreditiProsjekStudentaException ex) {
			System.out.println(ex.getMessage());
			logger.info("Nemoguæe odrediti prosjek Studenta!");
			return new BigDecimal(1);
		}
		
		BigDecimal konacnaOcjena = prosjek.multiply(new BigDecimal(2)).add(new BigDecimal(zavrsniRad + obrana)).divide(new BigDecimal(4));
		
		return konacnaOcjena;
	}
	/**
	 * Odreðuje najuspješnijeg studenta na godini.
	 * 
	 * @param godina godina studija u kojoj se traži najbolji student.
	 * @return najuspješnijeg studenta u traženoj godini.
	 */
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		int godinaIspita;
		Student najuspjesnijiStudent = this.getStudenti().get(0);
		BigDecimal topProsjek = new BigDecimal(0);
		
		for (Student student : this.getStudenti()) {
			
			List<Ispit> ispiti = filtrirajIspitePoStudentu(this.getIspiti(), student);
			List<Ispit> ispitiGodine = new ArrayList<>();
			
			for (Ispit ispit : ispiti) {
				godinaIspita = Integer.parseInt(ispit.getDatumIVrijeme().format(formatter));
				if (godinaIspita == godina) {
					ispitiGodine.add(ispit);
				}
			}
			
			BigDecimal prosjek = new BigDecimal(1);
			try {
				prosjek = odrediProsjekOcjenaNaIspitima(ispitiGodine);
			}
			catch (NemoguceOdreditiProsjekStudentaException ex){
				System.out.println(ex.getMessage());
				logger.info("Nemoguæe odrediti prosjek Studenta!");
			}
			if (prosjek.compareTo(topProsjek) >= 0) {
				topProsjek = prosjek;
				najuspjesnijiStudent = student;
			}
		}
		return najuspjesnijiStudent;
	}
}
