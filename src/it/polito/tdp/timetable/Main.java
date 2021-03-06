package it.polito.tdp.timetable;
	
import it.polito.tdp.timetable.model.Model;
import it.polito.tdp.timetable.panel.Launcher;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			StartController controller = loader.getController();
			Model model = new Model();
			
			controller.setModel(model);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Timetable School Creator");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
			Launcher.setStage(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
