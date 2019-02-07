package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
/**
 * Predstavlja veleu�ili�te jave definirano nazivom, predmetima, profesorima, studentima i ispitima.
 * 
 * @author Denis Kri�ak
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
	
	private static final long serialVersionUID = 144694252454246903L;
	/**
	 * Inicijalizira podatke veleu�ili�ta.
	 * 
	 * @param nazivUstanove naziv veleu�ili�ta.
	 * @param predmeti predmeti koji se odr�avaju na veleu�ili�tu.
	 * @param profesori profesori koji predaju na veleu�ili�tu.
	 * @param studenti studenti koji pohađaju veleu�ili�te.
	 * @param ispiti ispiti koji su održani na veleu�ili�tu.
	 */
	public VeleucilisteJave(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id, nazivUstanove, predmeti, profesori, studenti, ispiti);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);
	/**
	 * Izra�unava kona�nu ocjenu studija za studenta.
	 * 
	 * @param ispiti ispiti koje je student polagao.
	 * @param zavrsniRad ocjena koju je student dobio iz zavr�nog rada.
	 * @param obrana ocjena koju je student dobio iz obrane zavr�nog rada.
	 * @return kona�nu ocjenu studija za studenta.
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int zavrsniRad, int obrana) {
		
		BigDecimal prosjek = new BigDecimal(0);
		try {
			prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
		}
		catch (NemoguceOdreditiProsjekStudentaException ex) {
			System.out.println(ex.getMessage());
			logger.info("Nemogu�e odrediti prosjek Studenta!");
			return new BigDecimal(1);
		}
		
		BigDecimal konacnaOcjena = prosjek.multiply(new BigDecimal(2)).add(new BigDecimal(zavrsniRad + obrana)).divide(new BigDecimal(4));
		
		return konacnaOcjena;
	}
	/**
	 * Odre�uje najuspje�nijeg studenta na godini.
	 * 
	 * @param godina godina studija u kojoj se tra�i najbolji student.
	 * @return najuspje�nijeg studenta u tra�enoj godini.
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
				logger.info("Nemogu�e odrediti prosjek Studenta!");
			}
			if (prosjek.compareTo(topProsjek) >= 0) {
				topProsjek = prosjek;
				najuspjesnijiStudent = student;
			}
		}
		return najuspjesnijiStudent;
	}
}
