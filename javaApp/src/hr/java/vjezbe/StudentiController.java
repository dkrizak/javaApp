package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class StudentiController implements Initializable {
	
	@FXML
	private TableView<Student> tablicaStudenata;
	@FXML
	private TableColumn<Student, String> jmbagStudenta;
	@FXML
	private TableColumn<Student, String> prezimeStudenta;
	@FXML
	private TableColumn<Student, String> imeStudenta;
	@FXML
	private TableColumn<Student, String> datumStudenta;
	@FXML
	private TextField trazeniJmbag;
	@FXML
	private TextField trazenoPrezime;
	@FXML
	private TextField trazenoIme;
	@FXML
	private DatePicker trazeniDatum;
	
	private Student student = null;
	private List<Student> studenti = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		ObservableList<Student> studentiTablica = FXCollections.observableArrayList(studenti);
		
		jmbagStudenta.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		prezimeStudenta.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));
		imeStudenta.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));
		
		datumStudenta.setCellValueFactory( 
				 new Callback<TableColumn.CellDataFeatures<Student, String>, 
				ObservableValue<String>>() { 
				 @Override 
				 public ObservableValue<String> call( 
				TableColumn.CellDataFeatures<Student, String> student) { 
				 SimpleStringProperty property = new SimpleStringProperty(); 
				 DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("dd.MM.yyyy."); 
				 property.setValue(student.getValue().getDatumRodjenja().format(formatter));
				 return property; 
				 } 
				 }); 
		
		tablicaStudenata.setItems(studentiTablica);
	}
	
	public void pretraziStudente() {
		
		student = new Student(null,trazenoIme.getText(),trazenoPrezime.getText(),trazeniJmbag.getText(), trazeniDatum.getValue());
		try {
		 studenti = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		ObservableList<Student> studentiTablica = FXCollections.observableArrayList(studenti);
		
		tablicaStudenata.setItems(studentiTablica);	
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
