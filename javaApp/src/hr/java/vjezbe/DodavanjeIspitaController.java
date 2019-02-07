package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import tornadofx.control.DateTimePicker;

public class DodavanjeIspitaController implements Initializable{
	
	@FXML
	private ChoiceBox<String> noviPredmet;
	@FXML
	private ChoiceBox<String> noviStudent;
	@FXML
	private ChoiceBox<Integer> novaOcjena;
	@FXML
	private DateTimePicker noviDatum;
	
	private List<Student> studenti = null;
	private List<Predmet> predmeti = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(null);
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(null);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		List<String> predmetiPrikaz = new ArrayList<>();
		List<String> studentiPrikaz = new ArrayList<>();
		List<Integer> ocjene = new ArrayList<>();

		predmeti.stream().forEach(predmet -> {
			predmetiPrikaz.add(predmet.getNaziv());
		});
		
		studenti.stream().forEach(student -> {
			studentiPrikaz.add(student.getIme() + " " + student.getPrezime());
		});
		
		for (int i=1; i<=5; i++) ocjene.add(i);
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList(predmetiPrikaz); 
		noviPredmet.setItems(availableChoices);
		
		ObservableList<String> availableChoices2 = FXCollections.observableArrayList(studentiPrikaz); 
		noviStudent.setItems(availableChoices2);
		
		ObservableList<Integer> availableChoices3 = FXCollections.observableArrayList(ocjene); 
		novaOcjena.setItems(availableChoices3);
		
	}
	
	public void dodajIspit() {
		
		String greska = "";
		if (Optional.ofNullable(noviPredmet.getSelectionModel().getSelectedItem()).isEmpty()) greska += "Predmet je obvezan podatak!\n";
		if (Optional.ofNullable(noviStudent.getSelectionModel().getSelectedItem()).isEmpty()) greska += "Student je obvezan podatak!\n";
		if (Optional.ofNullable(novaOcjena.getSelectionModel().getSelectedItem()).isEmpty()) greska += "Ocjena je obvezan podatak!\n";
		if (Optional.ofNullable(noviDatum.getDateTimeValue()).isPresent() == false) greska += "Datum i vrijeme je obvezan podatak!\n";
		
		if (greska.length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje podataka.");
			alert.setHeaderText("Spremanje podataka o novom ispitu.");
			alert.setContentText("Podaci o novom ispitu su uspješno dodani!");
			alert.showAndWait();
			 
			Predmet predmetNovi = null;
			for (Predmet predmet : predmeti) {
				if (predmet.getNaziv().equals(noviPredmet.getSelectionModel().getSelectedItem())) {
					predmetNovi = predmet;
				}
			}
			
			Student studentNovi = null;
			for (Student student : studenti) {
				if ((student.getIme() + " " + student.getPrezime()).equals(noviStudent.getSelectionModel().getSelectedItem())){
					studentNovi  = student;
				}
			}
			
			Integer ocjena = novaOcjena.getSelectionModel().getSelectedItem();
			Ocjena enumOcjena = Ocjena.nedovoljan;
			
			if (ocjena != null) {
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
			}
			
			LocalDateTime datumIVrijeme = noviDatum.getDateTimeValue();
			
			Ispit noviIspit = new Ispit(null, predmetNovi, studentNovi, enumOcjena, datumIVrijeme);
			
			try {
				BazaPodataka.spremiNoviIspit(noviIspit);
			}
			catch (BazaPodatakaException ex) {
				System.out.println(ex.getMessage());
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka!");
			alert.setHeaderText("Molimo ispravite ljedeæe pogreške:");
			alert.setContentText(greska);
			alert.showAndWait();
		}
	}
	
	public void prikaziPretraguProfesora() { 
		 BorderPane root; 
		 try { 
			 root = (BorderPane)FXMLLoader.load(getClass().getResource("profesori.fxml")); 
			 Main.setMainPage(root); 
		 } catch (IOException e) { 
			 e.printStackTrace(); 
		 } 
	}
	
	public void prikaziPretraguStudenata() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Studenti.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void prikaziPretraguPredmeta() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Predmeti.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}

	public void prikaziPretraguIspita() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Ispiti.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	public void prikaziUnosProfesora() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("DodavanjeProfesora.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	public void prikaziUnosStudenta() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("DodavanjeStudenta.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	public void prikaziUnosPredmeta() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("DodavanjePredmeta.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	
	public void prikaziUnosIspita() {
		BorderPane root; 
		try { 
			root = (BorderPane)FXMLLoader.load(getClass().getResource("DodavanjeIspita.fxml")); 
			Main.setMainPage(root); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}

}
