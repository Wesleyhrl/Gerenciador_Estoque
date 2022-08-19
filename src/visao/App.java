package visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("DisplayLogin.fxml"));
        Parent root = fxml.load();
        Scene cenaLogin = new Scene(root);
        primaryStage.setTitle("Easy Stock");
       
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        primaryStage.setScene(cenaLogin);
        primaryStage.show();
        //teste
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
