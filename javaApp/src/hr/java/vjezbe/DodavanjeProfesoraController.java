package hr.java.vjezbe;

import java.io.IOException;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DodavanjeProfesoraController {
	
	@FXML
	private TextField novaSifra;
	@FXML
	private TextField novoPrezime;
	@FXML
	private TextField novoIme;
	@FXML
	private TextField novaTitula;
	
	public void dodajProfesora() {
		
		String greska = "";
		if (novaSifra.getText().isEmpty()) greska += "�ifra je obvezan podatak!\n";
		if (novoPrezime.getText().isEmpty()) greska += "Prezime je obvezan podatak!\n";
		if (novoIme.getText().isEmpty()) greska += "Ime je obvezan podatak!\n";
		if (novaTitula.getText().isEmpty()) greska += "Titula je obvezan podatak!\n";
		
		if (greska.length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspje�no spremanje podataka.");
			alert.setHeaderText("Spremanje podataka o novom profesoru.");
			alert.setContentText("Podaci o novom profesoru su uspje�no dodani!");
			alert.showAndWait();
			
			Profesor noviProfesor = new Profesor(null, novaSifra.getText(), 
					 novoIme.getText(), novoPrezime.getText(), novaTitula.getText());
			
			try {
				BazaPodataka.spremiNovogProfesora(noviProfesor);
			}
			catch (BazaPodatakaException ex) {
				System.out.println(ex.getMessage());
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogre�an unos podataka!");
			alert.setHeaderText("Molimo ispravite ljede�e pogre�ke:");
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
