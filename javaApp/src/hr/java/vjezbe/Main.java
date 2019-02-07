package hr.java.vjezbe;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Pocetna.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setMainPage(BorderPane root) { 
		 Scene scene = new Scene(root,400,500); 
		 stage.setScene(scene); 
		 stage.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
