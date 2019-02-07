package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
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
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import tornadofx.control.DateTimePicker;

public class IspitiController implements Initializable {
	
	@FXML
	private TableView<Ispit> tablicaIspita;
	@FXML
	private TableColumn<Ispit, String> nazivPredmeta;
	@FXML
	private TableColumn<Ispit, String> studentIspita;
	@FXML
	private TableColumn<Ispit, String> ocjenaIspita;
	@FXML
	private TableColumn<Ispit, String> datumIspita;
	@FXML
	private ChoiceBox<String> trazeniPredmet;
	@FXML
	private ChoiceBox<String> trazeniStudent;
	@FXML
	private ChoiceBox<Integer> trazenaOcjena;
	@FXML
	private DateTimePicker trazeniDatum;
	
	private Ispit ispit = null;
	private List<Ispit> ispiti = null;
	private List<Predmet> predmeti = null;
	private List<Student> studenti = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(null);
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
		trazeniPredmet.setItems(availableChoices);
		
		ObservableList<String> availableChoices2 = FXCollections.observableArrayList(studentiPrikaz); 
		trazeniStudent.setItems(availableChoices2);
		
		ObservableList<Integer> availableChoices3 = FXCollections.observableArrayList(ocjene); 
		trazenaOcjena.setItems(availableChoices3);
		
		ObservableList<Ispit> ispitiTablica = FXCollections.observableArrayList(ispiti);
		
		nazivPredmeta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override 
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getPredmet().getNaziv());
						return property;
					}
				});
				
		studentIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override 
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(ispit.getValue().getStudent().getIme() + " " + 
								ispit.getValue().getStudent().getPrezime());
						return property;
					}
				});
		
		ocjenaIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override 
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(String.valueOf(ispit.getValue().getOcjena().getOcjena()));
						return property;
					}
				});
		
		datumIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override 
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> ispit) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.' 'HH:mm");
						property.setValue(ispit.getValue().getDatumIVrijeme().format(formatter));
						return property;
					}
				});
		
		tablicaIspita.setItems(ispitiTablica);
		
	}
	
	public void pretraziIspite() {
		
		/*List<Ispit> ispitiTmp = ispiti.stream()
				.filter(ispit -> ispit.getPredmet().getNaziv().toLowerCase().contains(trazeniPredmet.getText().toLowerCase())).collect(Collectors.toList());
				
		ispitiTmp = ispitiTmp.stream()
				.filter(ispit -> (ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime()).toLowerCase()
				.contains(trazeniStudent.getText().toLowerCase())).collect(Collectors.toList());
		
		if (!trazenaOcjena.getText().isEmpty())
		ispitiTmp = ispitiTmp.stream()
				.filter(ispit -> ispit.getOcjena().getOcjena() == Integer.parseInt(trazenaOcjena.getText())).collect(Collectors.toList());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
		
		if (!trazeniDatum.getText().isEmpty())
		ispitiTmp = ispitiTmp.stream()
				.filter(ispit -> ispit.getDatumIVrijeme().equals(LocalDateTime.parse(trazeniDatum.getText(), formatter))).collect(Collectors.toList());
		*/
		
		Predmet odabraniPredmet = null;
		for (Predmet predmet : predmeti) {
			if (predmet.getNaziv()
					.equals(trazeniPredmet.getSelectionModel().getSelectedItem())) {
				odabraniPredmet = predmet;
			}
		}
		
		Student odabraniStudent = null;
		for (Student student : studenti) {
			if ((student.getIme() + " " + student.getPrezime())
					.equals(trazeniStudent.getSelectionModel().getSelectedItem())) {
				odabraniStudent = student;
			}
		}
		
		Integer ocjena = trazenaOcjena.getSelectionModel().getSelectedItem();
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
		} else enumOcjena = null;
	
		ispit = new Ispit(null,odabraniPredmet,odabraniStudent,enumOcjena,trazeniDatum.getDateTimeValue());
		
		try {
		 ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(ispit);
		}
		catch (BazaPodatakaException ex) {
			System.out.println(ex.getMessage());
		}
		
		ObservableList<Ispit> ispitiTablica = FXCollections.observableArrayList(ispiti);
		
		tablicaIspita.setItems(ispitiTablica);
		
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
