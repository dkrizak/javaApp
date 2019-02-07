package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import static java.lang.Math.toIntExact;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {
	
	private static final String DATABASE_FILE = "dat/baza.properties";

	private static Connection spajanjeNaBazu() throws SQLException, IOException {
		
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("url");
		String korisnickoIme = svojstva.getProperty("user");
		String lozinka = svojstva.getProperty("password");
		Connection veza = DriverManager.getConnection(urlBazePodataka,korisnickoIme,lozinka);
		return veza;

	}
	
	public static List<Profesor> dohvatiProfesorePremaKriterijima(Profesor profesor) throws 
	BazaPodatakaException { 
		
		List<Profesor> listaProfesora = new ArrayList<>(); 

		try (Connection veza = spajanjeNaBazu()) {
		 
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PROFESOR WHERE 1 = 1"); 

			if (Optional.ofNullable(profesor).isEmpty() == false) { 

				if (Optional.ofNullable(profesor).map(Profesor::getId).isPresent()) { 
					sqlUpit.append(" AND ID = " + profesor.getId()); 
				} 

				if (Optional.ofNullable(profesor.getSifra()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND SIFRA LIKE '%" + profesor.getSifra() + "%'"); 
				}  

				if (Optional.ofNullable(profesor.getIme()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND IME LIKE '%" + profesor.getIme() + "%'"); 
				} 

				if (Optional.ofNullable(profesor.getPrezime()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND PREZIME LIKE '%" + profesor.getPrezime() + "%'"); 
				} 	 

				if (Optional.ofNullable(profesor.getTitula()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND TITULA LIKE '%" + profesor.getTitula() + "%'"); 
				} 
			} 

			Statement upit = veza.createStatement(); 

			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
	 
			 while (resultSet.next()) {
				 Long id = resultSet.getLong("id"); 
				 String sifra = resultSet.getString("sifra"); 
				 String ime = resultSet.getString("ime"); 
				 String prezime = resultSet.getString("prezime"); 
				 String titula = resultSet.getString("titula"); 
				 Profesor noviProfesor = new Profesor(id, sifra, ime, prezime, titula); 
				 listaProfesora.add(noviProfesor); 
			 } 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka";  
			throw new BazaPodatakaException(poruka, ex); 
		}

		return listaProfesora; 
	}
	
	public static void spremiNovogProfesora(Profesor profesor) throws BazaPodatakaException { 
		
		try (Connection veza = spajanjeNaBazu()) { 
			
			PreparedStatement preparedStatement = veza.prepareStatement( 
					"INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES (?, ?, ?, ?)"); 
			preparedStatement.setString(1, profesor.getIme()); 
			preparedStatement.setString(2, profesor.getPrezime()); 
			preparedStatement.setString(3, profesor.getSifra()); 
			preparedStatement.setString(4, profesor.getTitula()); 
			preparedStatement.executeUpdate(); 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka"; 
			throw new BazaPodatakaException(poruka, ex); 
		} 
	}
	
	public static List<Student> dohvatiStudentePremaKriterijima(Student student) throws 
	BazaPodatakaException { 
		
		List<Student> listaStudenata = new ArrayList<>(); 

		try (Connection veza = spajanjeNaBazu()) {
		 
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM STUDENT WHERE 1 = 1"); 

			if (Optional.ofNullable(student).isEmpty() == false) { 

				if (Optional.ofNullable(student).map(Student::getId).isPresent()) { 
					sqlUpit.append(" AND ID = " + student.getId()); 
				}   

				if (Optional.ofNullable(student.getIme()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND IME LIKE '%" + student.getIme() + "%'"); 
				} 

				if (Optional.ofNullable(student.getPrezime()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND PREZIME LIKE '%" + student.getPrezime() + "%'"); 
				}
				
				if (Optional.ofNullable(student.getJmbag()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND JMBAG LIKE '%" + student.getJmbag() + "%'"); 
				}

				if (Optional.ofNullable(student.getDatumRodjenja()).isPresent()) { 
					sqlUpit.append(" AND DATUM_RODJENJA = '" + student.getDatumRodjenja().format( 
					 DateTimeFormatter.ISO_DATE) + "'"); 
				}  
			} 

			Statement upit = veza.createStatement(); 

			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
	 
			 while (resultSet.next()) {
				 Long id = resultSet.getLong("id");  
				 String ime = resultSet.getString("ime"); 
				 String prezime = resultSet.getString("prezime");
				 String jmbag = resultSet.getString("jmbag");
				 LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant().atZone( 
						 ZoneId.systemDefault()).toLocalDate(); 
				 Student noviStudent = new Student(id, ime, prezime, jmbag, datumRodjenja); 
				 listaStudenata.add(noviStudent); 
			 } 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka";  
			throw new BazaPodatakaException(poruka, ex); 
		}

		return listaStudenata; 
	}
	
	public static void spremiNovogStudenta(Student student) throws BazaPodatakaException { 
			
			try (Connection veza = spajanjeNaBazu()) { 
				
				PreparedStatement preparedStatement = veza.prepareStatement( 
						"INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES (?, ?, ?, ?)"); 
				preparedStatement.setString(1, student.getIme()); 
				preparedStatement.setString(2, student.getPrezime()); 
				preparedStatement.setString(3, student.getJmbag()); 
				preparedStatement.setDate(4, Date.valueOf(student.getDatumRodjenja())); 
				preparedStatement.executeUpdate(); 
			} catch (SQLException | IOException ex) { 
				String poruka = "Došlo je do pogreške u radu s bazom podataka"; 
				throw new BazaPodatakaException(poruka, ex); 
			} 
	}
	
	public static List<Predmet> dohvatiPredmetePremaKriterijima(Predmet predmet) throws 
	BazaPodatakaException { 
		
		List<Predmet> listaPredmeta = new ArrayList<>(); 

		try (Connection veza = spajanjeNaBazu()) {
		 
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PREDMET WHERE 1 = 1"); 

			if (Optional.ofNullable(predmet).isEmpty() == false) { 

				if (Optional.ofNullable(predmet).map(Predmet::getId).isPresent()) { 
					sqlUpit.append(" AND ID = " + predmet.getId()); 
				}
				
				if (Optional.ofNullable(predmet.getSifra()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND SIFRA LIKE '%" + predmet.getSifra() + "%'"); 
				}

				if (Optional.ofNullable(predmet.getNaziv()).map(String::isBlank).orElse(true) == false) { 
					sqlUpit.append(" AND NAZIV LIKE '%" + predmet.getNaziv() + "%'"); 
				} 

				if (Optional.ofNullable(predmet.getBrojEctsBodova()).isPresent()) { 
					sqlUpit.append(" AND BROJ_ECTS_BODOVA = " + predmet.getBrojEctsBodova()); 
				}

				if (Optional.ofNullable(predmet.getNositelj()).isPresent()) { 
					sqlUpit.append(" AND PROFESOR_ID = " + predmet.getNositelj().getId()); 
				}
			} 
			
			Statement upit = veza.createStatement(); 
			
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
	
			 while (resultSet.next()) {
				 Long id = resultSet.getLong("id");
				 String sifra = resultSet.getString("sifra");
				 String naziv = resultSet.getString("naziv"); 
				 Integer ectsBodovi = resultSet.getInt("broj_ects_bodova");
				 Long nositeljId = resultSet.getLong("profesor_id");
				 
				 Statement upit2 = veza.createStatement();
				 ResultSet resultSet2 = upit2.executeQuery("SELECT * FROM PROFESOR WHERE ID = " + nositeljId);
				 resultSet2.next();
				 nositeljId = resultSet2.getLong("id");
				 String nositeljSifra = resultSet2.getString("sifra");
				 String nositeljIme = resultSet2.getString("ime");
				 String nositeljPrezime = resultSet2.getString("prezime");
				 String nositeljTitula = resultSet2.getString("titula");
				 Profesor nositelj = new Profesor(nositeljId, nositeljSifra, nositeljIme, nositeljPrezime, nositeljTitula);
				 
				 Predmet noviPredmet = new Predmet(id, sifra, naziv, ectsBodovi, nositelj); 
				 listaPredmeta.add(noviPredmet); 
			 } 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka";  
			throw new BazaPodatakaException(poruka, ex); 
		}

		return listaPredmeta; 
	}
	
	public static void spremiNoviPredmet(Predmet predmet) throws BazaPodatakaException { 
		
		try (Connection veza = spajanjeNaBazu()) { 
			
			PreparedStatement preparedStatement = veza.prepareStatement( 
					"INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES (?, ?, ?, ?)"); 
			preparedStatement.setString(1, predmet.getSifra()); 
			preparedStatement.setString(2, predmet.getNaziv()); 
			preparedStatement.setInt(3, predmet.getBrojEctsBodova()); 
			preparedStatement.setInt(4, toIntExact(predmet.getNositelj().getId())); 
			preparedStatement.executeUpdate(); 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka"; 
			throw new BazaPodatakaException(poruka, ex); 
		} 
	}
	
	public static List<Ispit> dohvatiIspitePremaKriterijima(Ispit ispit) throws 
	BazaPodatakaException { 
		
		List<Ispit> listaIspita = new ArrayList<>(); 

		try (Connection veza = spajanjeNaBazu()) {
		 
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM ISPIT WHERE 1 = 1"); 

			if (Optional.ofNullable(ispit).isEmpty() == false) { 

				if (Optional.ofNullable(ispit).map(Ispit::getId).isPresent()) { 
					sqlUpit.append(" AND ID = " + ispit.getId()); 
				}   

				if (Optional.ofNullable(ispit.getPredmet()).isPresent()) { 
					sqlUpit.append(" AND PREDMET_ID = " + ispit.getPredmet().getId()); 
				}  

				if (Optional.ofNullable(ispit.getStudent()).isPresent()) { 
					sqlUpit.append(" AND STUDENT_ID = " + ispit.getStudent().getId()); 
				}
				
				if (Optional.ofNullable(ispit.getOcjena()).isPresent()) { 
					sqlUpit.append(" AND OCJENA = " + ispit.getOcjena().getOcjena()); 
				}

				if (Optional.ofNullable(ispit.getDatumIVrijeme()).isPresent()) { 
					 DateTimeFormatter formatter = 
					 DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS"); 
					 sqlUpit.append(" AND datum_i_vrijeme = '" + 
					ispit.getDatumIVrijeme().format(formatter) + "'"); 
				}  
			} 
			
			Statement upit = veza.createStatement(); 

			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
	 
			 while (resultSet.next()) {
				 Long id = resultSet.getLong("id");  
				 Long predmetId = resultSet.getLong("predmet_id"); 
				 Long studentId = resultSet.getLong("student_id");
				 Integer ocjena = resultSet.getInt("ocjena");
				 LocalDateTime datumIVrijemeIspita = 
						 resultSet.getTimestamp("datum_i_vrijeme").toLocalDateTime(); 
				 
				 //PREDMET
				 Statement upit2 = veza.createStatement();
				 ResultSet resultSet2 = upit2.executeQuery("SELECT * FROM PREDMET WHERE ID = " + predmetId);
				 resultSet2.next();
				 predmetId = resultSet2.getLong("id");
				 String predmetSifra = resultSet2.getString("sifra");
				 String predmetNaziv = resultSet2.getString("naziv");
				 Integer predmetBrojEctsa = resultSet2.getInt("broj_ects_bodova");
				 Long predmetNositeljId = resultSet2.getLong("profesor_id");
				 
				 //NOSITELJ
				 upit2 = veza.createStatement();
				 resultSet2 = upit2.executeQuery("SELECT * FROM PROFESOR WHERE ID = " + predmetNositeljId);
				 resultSet2.next();
				 predmetNositeljId = resultSet2.getLong("id");
				 String nositeljSifra = resultSet2.getString("sifra");
				 String nositeljIme = resultSet2.getString("ime");
				 String nositeljPrezime = resultSet2.getString("prezime");
				 String nositeljTitula = resultSet2.getString("titula");
				 Profesor nositelj = new Profesor(predmetNositeljId, nositeljSifra, nositeljIme, nositeljPrezime, nositeljTitula);
				 
				 Predmet predmet = new Predmet(predmetId, predmetSifra, predmetNaziv, predmetBrojEctsa, nositelj);
				 
				 //STUDENT
				 upit2 = veza.createStatement();
				 resultSet2 = upit2.executeQuery("SELECT * FROM STUDENT WHERE ID = " + studentId);
				 resultSet2.next();
				 studentId = resultSet2.getLong("id");
				 String studentIme = resultSet2.getString("ime");
				 String studentPrezime = resultSet2.getString("prezime");
				 String studentJmbag = resultSet2.getString("jmbag");
				 LocalDate studentDatumRodjenja = resultSet2.getTimestamp("datum_rodjenja").toInstant().atZone( 
						 ZoneId.systemDefault()).toLocalDate();
				 Student student = new Student(studentId, studentIme, studentPrezime, studentJmbag, studentDatumRodjenja);
				 
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
					
				 Ispit noviIspit = new Ispit(id, predmet, student, enumOcjena, datumIVrijemeIspita); 
				 listaIspita.add(noviIspit); 
			 } 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka";  
			throw new BazaPodatakaException(poruka, ex); 
		}

		return listaIspita; 
	}
	
	public static void spremiNoviIspit(Ispit ispit) throws BazaPodatakaException { 
		
		try (Connection veza = spajanjeNaBazu()) { 
			
			PreparedStatement preparedStatement = veza.prepareStatement( 
					"INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (?, ?, ?, ?)"); 
			preparedStatement.setLong(1, ispit.getPredmet().getId());
			preparedStatement.setLong(2, ispit.getStudent().getId());
			preparedStatement.setInt(3, ispit.getOcjena().getOcjena()); 
			preparedStatement.setTimestamp(4, Timestamp.valueOf(ispit.getDatumIVrijeme()));
			preparedStatement.executeUpdate(); 
		} catch (SQLException | IOException ex) { 
			String poruka = "Došlo je do pogreške u radu s bazom podataka"; 
			throw new BazaPodatakaException(poruka, ex); 
		} 
	}
	
}