package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
/**
 * Opisuje ustanovu kao visoku �kolu svojim metodama.
 * 
 * @author Denis Kri�ak
 *
 */
public interface Visokoskolska {
	
	/**
	 * Izra�unava kona�nu ocjenu studija za studenta.
	 * 
	 * @param ispiti ispiti koje je student polagao.
	 * @param zavrsni ocjena koju je student dobio iz zavr�nog rada.
	 * @param obrana ocjena koju je student dobio iz obrane zavr�nog rada.
	 * @return kona�nu ocjenu studija za studenta.
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int zavrsni, int obrana);
	
	/**
	 * Izra�unava prosjek ocjena studenta na ispitima.
	 * 
	 * @param ispiti ispiti koje je student polagao.
	 * @return prosjek ocjena na ispitima.
	 * @throws NemoguceOdreditiProsjekStudentaException ukoliko student ima negativnu ocjenu prosjek se ne mo�e izra�unati.
	 */
	default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {
		
		BigDecimal prosjek = new BigDecimal(0);
		int brojOcjena = 0;
		
		for (Ispit ispit : ispiti) {
			if (ispit.getOcjena().getOcjena() == 1) throw new NemoguceOdreditiProsjekStudentaException("Student "+
					ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime() + " zbog negativne ocjene na jednom od ispita ima prosjek \"nedovoljan (1)\"!");
			prosjek = prosjek.add(new BigDecimal(ispit.getOcjena().getOcjena()));
			brojOcjena++;
		}
		
		prosjek = prosjek.divide(new BigDecimal(brojOcjena));
		return prosjek;
	}
	/**
	 * filtrira ispite studenta tako da vra�a polje polo�enih ispita.
	 * 
	 * @param ispiti ispiti koje je student polagao.
	 * @return polje polo�enih ispita.
	 */
	private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {
		
		List<Ispit> polozeniIspiti = new ArrayList<>();
		
		for (Ispit ispit : ispiti) {
			if (Integer.valueOf((ispit.getOcjena().getOcjena())).compareTo(1) > 0) {
				polozeniIspiti.add(ispit);
			}
		}
		return polozeniIspiti;
	}
	/**
	 * filtrira ispite tako da vra�a polje ispita kojima je pristupio odre�eni student.
	 * 
	 * @param ispiti ispiti koji su pisani u ustanovi.
	 * @param student student �iji se ispiti tra�e.
	 * @return polje ispita kojima je pristupio tra�eni student.
	 */
	default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {
		
		List<Ispit> filterIspiti = new ArrayList<>();
		
		filterIspiti = ispiti.stream().filter(ispit -> ispit.getStudent().equals(student)).collect(Collectors.toList());
		
		return filterIspiti;
	}
}
