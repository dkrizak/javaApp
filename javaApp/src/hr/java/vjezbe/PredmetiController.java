package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class PredmetiController implements Initializable {
	
	@FXML
	private TableView<Predmet> tablicaPredmeta;
	@FXML
	private TableColumn<Predmet, String> sifraPredmeta;
	@FXML
	private TableColumn<Predmet, String> nazivPredmeta;
	@FXML
	private TableColumn<Predmet, String> brojEctsa;
	@FXML
	private TableColumn<Predmet, String> nositeljPredmeta;
	@FXML
	private TextField trazenaSifra;
	@FXML
	private TextField trazeniNaziv;
	@FXML
	private TextField trazeniBrojEctsa;
	@FXML
	private ChoiceBox<String> trazeniNositelj;
	
	private Predmet predmet = null;
	private List<Predmet> predmeti = null;
	private List<Profesor> profesori = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(predmet);
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
		trazeniNositelj.setItems(availableChoices);
		
		ObservableList<Predmet> predmetiTablica = FXCollections.observableArrayList(predmeti);
		
		sifraPredmeta.setCellValueFactory(new PropertyValueFactory<Predmet, String>("sifra"));
		nazivPredmeta.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));
		brojEctsa.setCellValueFactory(new PropertyValueFactory<Predmet, String>("brojEctsBodova"));
		
		nositeljPredmeta.setCellValueFactory( 
				 new Callback<TableColumn.CellDataFeatures<Predmet, String>, 
				ObservableValue<String>>() { 
				 @Override 
				 public ObservableValue<String> call( 
				TableColumn.CellDataFeatures<Predmet, String> predmet) { 
				 SimpleStringProperty property = new SimpleStringProperty(); 
				 
				 property.setValue(predmet.getValue().getNositelj().getIme() 
						 + " " + predmet.getValue().getNositelj().getPrezime());
				 return property; 
				 } 
				 }); 
		
		tablicaPredmeta.setItems(predmetiTablica);
		
	}
	
	public void pretraziPredmete() {
		
		Profesor nositelj = null;
		for (Profesor profesor : profesori) {
			if ((profesor.getIme() + " " + profesor.getPrezime())
					.equals(trazeniNositelj.getSelectionModel().getSelectedItem())) {
				nositelj = profesor;
			}
		}
		
		Integer trazeniBroj = null;
		if (!trazeniBrojEctsa.getText().isEmpty()) trazeniBroj = Integer.parseInt(trazeniBrojEctsa.getText());
	
		predmet = new Predmet(null,trazenaSifra.getText(),trazeniNaziv.getText(),trazeniBroj,nositelj);
		
		try {
		 predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(predmet);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		ObservableList<Predmet> predmetiTablica = FXCollections.observableArrayList(predmeti);
		
		tablicaPredmeta.setItems(predmetiTablica);
		
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
