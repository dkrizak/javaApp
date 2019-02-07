package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProfesoriController implements Initializable{
	
	@FXML
	private TableView<Profesor> tablicaProfesora;
	@FXML
	private TableColumn<Profesor, String> sifraProfesora;
	@FXML
	private TableColumn<Profesor, String> prezimeProfesora;
	@FXML
	private TableColumn<Profesor, String> imeProfesora;
	@FXML
	private TableColumn<Profesor, String> titulaProfesora;
	@FXML
	private TextField trazenaSifra;
	@FXML
	private TextField trazenoPrezime;
	@FXML
	private TextField trazenoIme;
	@FXML
	private TextField trazenaTitula;
	
	private Profesor profesor = null;
	private List<Profesor> profesori = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		ObservableList<Profesor> profesoriTablica = FXCollections.observableArrayList(profesori);
		
		sifraProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));
		prezimeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		imeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		titulaProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));
		
		tablicaProfesora.setItems(profesoriTablica);

	}
	
	public void pretraziProfesore() {
		
		profesor = new Profesor(null,trazenaSifra.getText(),trazenoIme.getText(),trazenoPrezime.getText(),trazenaTitula.getText());
		try {
		 profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
			
		ObservableList<Profesor> profesoriTablica = FXCollections.observableArrayList(profesori);
		
		tablicaProfesora.setItems(profesoriTablica);
		
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
