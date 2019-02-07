package hr.java.vjezbe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class Datoteke {
	
	public static final String PROFESORI = "dat/profesori.txt";
	public static final String PREDMETI = "dat/predmeti.txt";
	public static final String STUDENTI = "dat/studenti.txt";
	public static final String ISPITI = "dat/ispiti.txt";
	
	public static List<Profesor> dohvatiProfesore(){
		System.out.println("Uèitavanje profesora...");
		List<Profesor> profesori = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(PROFESORI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String sifra, ime, prezime, titula;
				line = in.readLine();
				sifra = line;
				line = in.readLine();
				ime = line;
				line = in.readLine();
				prezime = line;
				line = in.readLine();
				titula = line;
				profesori.add(new Profesor(id, sifra, ime, prezime, titula));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return profesori;
	}
	
	public static List<Student> dohvatiStudente(){
		System.out.println("Uèitavanje studenata...");
		List<Student> studenti = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(STUDENTI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String ime, prezime, jmbag;
				LocalDate datumRodjenja;
				line = in.readLine();
				ime = line;
				line = in.readLine();
				prezime = line;
				line = in.readLine();
				jmbag = line;
				line = in.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				datumRodjenja = LocalDate.parse(line, formatter);
				studenti.add(new Student(id, ime, prezime, jmbag, datumRodjenja));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return studenti;
	}
	
	public static List<Predmet> dohvatiPredmete(List<Profesor> profesori, List<Student> studenti){
		System.out.println("Uèitavanje predmeta...");
		List<Predmet> predmeti = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(PREDMETI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String sifra, naziv;
				Integer brojEcts;
				Profesor nositelj = null;
				Predmet predmet;
				line = in.readLine();
				sifra = line;
				line = in.readLine();
				naziv = line;
				line = in.readLine();
				brojEcts = Integer.parseInt(line);
				line = in.readLine();
				Long profId = Long.parseLong(line);
				for (Profesor profesor : profesori) {
					if (profesor.getId() == profId) {
						nositelj = profesor;
						break;
					}
				}
				predmet = new Predmet(id, sifra, naziv, brojEcts, nositelj);
				line = in.readLine();
	
				if (line != "0") {
					Set<Student> studentitmp = new HashSet<Student>();
					String[] split = line.split(" ");
					for (int i=0; i<split.length; i++) {
						for (Student student : studenti) {
							if (student.getId() == Long.parseLong(split[i])) {
								studentitmp.add(student);
								break;
							}
						}
					}
					predmet.setStudenti(studentitmp);
				}
				predmeti.add(predmet);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return predmeti;
	}
	
	public static List<Ispit> dohvatiIspite(List<Predmet> predmeti, List<Student> studenti){
		System.out.println("Uèitavanje ispita i ocjena...");
		List<Ispit> ispiti = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(ISPITI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				line = in.readLine();
				Long predmetId = Long.parseLong(line);
				Predmet predmettmp = null;
				for (Predmet predmet : predmeti) {
					if (predmet.getId() == predmetId) {
						predmettmp = predmet;
						break;
					}
				}
				line = in.readLine();
				Long studentId = Long.parseLong(line);
				Student studenttmp = null;
				for (Student student : studenti) {
					if (student.getId() == studentId) {
						studenttmp = student;
						break;
					}
				}
				line = in.readLine();
				int ocjena = Integer.parseInt(line);
				
				Ocjena enumOcjena = Ocjena.nedovoljan;
				switch (ocjena) {
					case 1:
						enumOcjena = Ocjena.nedovoljan;
						break;
					case 2:
						enumOcjena = Ocjena.dovoljan;
						break;
					case 3:
						enumOcjena = Ocjena.dobar;
						break;
					case 4:
						enumOcjena = Ocjena.vrlodobar;
						break;
					case 5:
						enumOcjena = Ocjena.izvrstan;
						break;
				}
				
				line = in.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
				LocalDateTime datumIVrijeme = LocalDateTime.parse(line, formatter);
				
				ispiti.add(new Ispit(id, predmettmp, studenttmp, enumOcjena, datumIVrijeme));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ispiti;
	}
	
	public static void zapisiProfesora(Profesor profesor) {
		
		try {
			
			String content = "\n";
	        content += profesor.getId() + "\n";
	        content += profesor.getSifra() + "\n";
	        content += profesor.getIme() + "\n";
	        content += profesor.getPrezime() + "\n";
	        content += profesor.getTitula();

	        File file = new File(PROFESORI);

	        // if file doesnt exists, then create it
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        
	        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(content);
	        bw.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void zapisiStudenta(Student student) {
		
		try {
			
			String content = "\n";
	        content += student.getId() + "\n";
	        content += student.getIme() + "\n";
	        content += student.getPrezime() + "\n";
	        content += student.getJmbag() + "\n";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	        content += student.getDatumRodjenja().format(formatter);

	        File file = new File(STUDENTI);

	        // if file doesnt exists, then create it
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        
	        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(content);
	        bw.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void zapisiPredmet(Predmet predmet) {
	
		try {
			
			String content = "\n";
	        content += predmet.getId() + "\n";
	        content += predmet.getSifra() + "\n";
	        content += predmet.getNaziv() + "\n";
	        content += predmet.getBrojEctsBodova() + "\n";
	        content += predmet.getNositelj().getId() + "\n";
	        content += 0;
	
	        File file = new File(PREDMETI);
	
	        // if file doesnt exists, then create it
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        
	        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(content);
	        bw.close();
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void zapisiIspit(Ispit ispit) {
		
		try {
			
			String content = "\n";
	        content += ispit.getId() + "\n";
	        content += ispit.getPredmet().getId() + "\n";
	        content += ispit.getStudent().getId() + "\n";
	        content += ispit.getOcjena().getOcjena() + "\n";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
	        content += ispit.getDatumIVrijeme().format(formatter);
	
	        File file = new File(ISPITI);
	
	        // if file doesnt exists, then create it
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        
	        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(content);
	        bw.close();
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
