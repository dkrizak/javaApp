package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

public class DodavanjePredmetaController implements Initializable{
	
	@FXML
	private TextField novaSifra;
	@FXML
	private TextField noviNaziv;
	@FXML
	private TextField noviBrojEctsa;
	@FXML
	private ChoiceBox<String> noviNositelj;
	
	private List<Profesor> profesori = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(null);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		List<String> nositelji = new ArrayList<>();

		profesori.stream().forEach(profesor -> {
			nositelji.add(profesor.getIme() + " " + profesor.getPrezime());
		});
		
		ObservableList<String> availableChoices = FXCollections.observableArrayList(nositelji); 
		noviNositelj.setItems(availableChoices);
	}
	
public void dodajPredmet() {
		
		String greska = "";
		if (novaSifra.getText().isEmpty()) greska += "Šifra je obvezan podatak!\n";
		if (noviNaziv.getText().isEmpty()) greska += "Naziv je obvezan podatak!\n";
		if (noviBrojEctsa.getText().isEmpty()) greska += "Broj ECTS-a je obvezan podatak!\n";
		if (Optional.ofNullable(noviNositelj.getSelectionModel().getSelectedItem()).isEmpty()) greska += "Nositelj predmeta je obvezan podatak!\n";
		
		if (greska.length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje podataka.");
			alert.setHeaderText("Spremanje podataka o novom predmetu.");
			alert.setContentText("Podaci o novom predmetu su uspješno dodani!");
			alert.showAndWait();
			
			Profesor nositelj = null;
			for (Profesor profesor : profesori) {
				if ((profesor.getIme() + " " + profesor.getPrezime())
						.equals(noviNositelj.getSelectionModel().getSelectedItem())) {
					nositelj = profesor;
				}
			}
			
			Predmet noviPredmet = new Predmet(null, novaSifra.getText(), 
					 noviNaziv.getText(), Integer.parseInt(noviBrojEctsa.getText()), nositelj);
			
			try {
				BazaPodataka.spremiNoviPredmet(noviPredmet);
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
