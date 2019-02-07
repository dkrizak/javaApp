package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
/**
 * Predstavlja fakultet raèunarstva definiran nazivom, predmetima, profesorima, studentima i ispitima.
 * 
 * @author Denis Križak
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {
	
	private static final long serialVersionUID = -413443506613963314L;
	/**
	 * Inicijalizira podatke o fakultetu.
	 * 
	 * @param nazivUstanove naziv fakulteta.
	 * @param predmeti predmeti koji se predaju na fakultetu.
	 * @param profesori profesori koji predaju na fakultetu.
	 * @param studenti studenti koji pohaðaju fakultet.
	 * @param ispiti ispiti koji su održani na fkultetu.
	 */
	public FakultetRacunarstva(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
		super(id, nazivUstanove, predmeti, profesori, studenti, ispiti);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);
	/**
	 * Izraèunava konaènu ocjenu studija za studenta.
	 * 
	 * @param ispiti ispiti koje je student pisao.
	 * @param diplomskiRad ocjena koju je student dobio iz diplomskog rada.
	 * @param obrana ocjena koju je student dobio iz obrane diplomskog rada.
	 * @return konaènu ocjenu studenta na studiju.
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int diplomskiRad, int obrana) {
		
		BigDecimal prosjek = new BigDecimal(0);
		try {
			prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
		}
		catch (NemoguceOdreditiProsjekStudentaException ex) {
			System.out.println(ex.getMessage());
			logger.info("Nemoguæe odrediti prosjek Studenta!");
			return new BigDecimal(1);
		}
		
		BigDecimal konacnaOcjena = prosjek.multiply(new BigDecimal(3)).add(new BigDecimal(diplomskiRad + obrana)).divide(new BigDecimal(5));
		
		return konacnaOcjena;
	}
	/**
	 * Odreðuje studenta za rektorovu nagradu.
	 * Ako postoji više studenata koji zaslužuju nagradu, dobija ju najmlaði. Ukoliko postoji više
	 * najmlaðih studenata nagradu ne dobiva nitko, baca se iznimka.
	 * 
	 * @return studenta koji je dobio rektorovu nagradu.
	 * 	 
	 * */
	@Override
	public Student odrediStudentaZaRektorovuNagradu() {
		
		List<Student> najuspjesnijiStudenti = new ArrayList<>();
		najuspjesnijiStudenti.add(this.getStudenti().get(0));
		BigDecimal topProsjek = new BigDecimal(0);
		
		
		for (Student student : this.getStudenti()) {
			
			List<Ispit> ispiti = filtrirajIspitePoStudentu(this.getIspiti(), student);
			
			BigDecimal prosjek = new BigDecimal(1);
			try {
				prosjek = odrediProsjekOcjenaNaIspitima(ispiti);
			}
			catch (NemoguceOdreditiProsjekStudentaException ex){
				System.out.println(ex.getMessage());
				logger.info("Nemoguæe odrediti prosjek Studenta!");
			}
			
			if (prosjek.compareTo(topProsjek) == 0) {
				najuspjesnijiStudenti.add(student);
			} 
			else if (prosjek.compareTo(topProsjek) > 0) {
				topProsjek = prosjek;
				najuspjesnijiStudenti = new ArrayList<>();
				najuspjesnijiStudenti.add(student);
			}
		}
		
		if (najuspjesnijiStudenti.size() == 1) {
			return najuspjesnijiStudenti.get(0);
		} 
		else {
			List<Student> najmladji = new ArrayList<>();
			najmladji.add(najuspjesnijiStudenti.get(0));
			
			for (int i=1; i<najuspjesnijiStudenti.size(); i++) {
				if (najmladji.get(0).getDatumRodjenja().compareTo(najuspjesnijiStudenti.get(i).getDatumRodjenja()) > 0) {
					najmladji = new ArrayList<>();
					najmladji.add(najuspjesnijiStudenti.get(i));
				}
				else if (najmladji.get(0).getDatumRodjenja().compareTo(najuspjesnijiStudenti.get(i).getDatumRodjenja()) == 0) {
					najmladji.add(najuspjesnijiStudenti.get(i));
				}
			}
			
			if (najmladji.size() > 1) {
				String message = "Pronaðeno je više najmlaðih studenata sa istim datumom roðenja, a to su " +
						najmladji.get(0).getIme() + " " + najmladji.get(0).getPrezime();
				for (int i=1; i<najmladji.size(); i++) {
					if (i == najmladji.size() - 1) {
						message += " i " + najmladji.get(i).getIme() + " " + najmladji.get(i).getPrezime(); 
					}
					else message += ", " + najmladji.get(i).getIme() + " " + najmladji.get(i).getPrezime();
				}
				message += ".";
				throw new PostojiViseNajmladjihStudenataException(message);
			}
			else return najmladji.get(0);
		}
	}
	/**
	 * Odreðuje najuspješnijeg studenta u željenoj godini.
	 * 
	 * @param godina tražena godina.
	 * @return najuspješnijeg studenta u traženoj godini.
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		int godinaIspita;
		Student najuspjesnijiStudent = this.getStudenti().get(0);
		int najviseIzvrsnih = 0;
		
		for (Student student : this.getStudenti()) {
			
			List<Ispit> ispiti = filtrirajIspitePoStudentu(this.getIspiti(), student);
			int brojIzvrsnih = 0;
			
			for (Ispit ispit : ispiti) {
				godinaIspita = Integer.parseInt(ispit.getDatumIVrijeme().format(formatter));
				if (godinaIspita == godina) {
					if (ispit.getOcjena().getOcjena() == 5) brojIzvrsnih++;
				}
			}
			
			if (brojIzvrsnih > najviseIzvrsnih) {
				najviseIzvrsnih = brojIzvrsnih;
				najuspjesnijiStudent = student;
			}
		}
		return najuspjesnijiStudent;
	}

}
