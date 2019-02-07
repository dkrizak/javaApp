package hr.java.vjezbe;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PocetnaController {
	
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
