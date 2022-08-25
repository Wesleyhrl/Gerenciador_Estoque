package visao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    private static Stage stage;
    private static Stage secondaryStage = new Stage();
    private static Stage helpStage = new Stage();
    private static Scene main;
    private static Scene login;
    private static Scene newProduto;
    private static Scene editProduto;
    private static Scene helpImp;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        
        //Login
        FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("DisplayLogin.fxml"));
        Parent rootLogin = fxmlLogin.load();
        login = new Scene(rootLogin);
        //Main
        FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent rootMain = fxmlMain.load();
        main = new Scene(rootMain);
        //Novo Produto
        FXMLLoader fxmlNewProduto = new FXMLLoader(getClass().getResource("NewProduto.fxml"));
        Parent rootNewProduto = fxmlNewProduto.load();
        newProduto = new Scene(rootNewProduto);
        //Editar Produto
        
        //Main
        stage.setTitle("Easy Stock");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setScene(login);
        stage.show();
        
        //Janela Secundaria
        secondaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        //Janela Help
        helpStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        helpStage.initStyle(StageStyle.UNDECORATED);
        helpStage.initOwner(stage);
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.focusedProperty();
    }
    public static void cenaLogin(){
        stage.setScene(login);
    }
    public static void cenaMain(){
        stage.setScene(main);
        
    }
    public static void cenaNewProduto(){
        secondaryStage.setTitle("Novo Produto");
        secondaryStage.setScene(newProduto);
        secondaryStage.show();
    }
    public static void cenaEditProduto() throws IOException{
        FXMLLoader fxmlEditProduto = new FXMLLoader(App.class.getResource("Editar.fxml"));
        Parent rootEditProduto = fxmlEditProduto.load();
        editProduto = new Scene(rootEditProduto);
        secondaryStage.setTitle("Editar Produto");
        secondaryStage.setScene(editProduto);
        secondaryStage.show();
        
    }
    public static void cenaHelpImp() throws IOException{
        FXMLLoader fxml = new FXMLLoader(App.class.getResource("HelpImp.fxml"));
        Parent root = fxml.load();
        helpImp = new Scene(root);
        
        helpStage.setTitle("Como Importar Arquivo");
        helpStage.setScene(helpImp);
        helpStage.show();
    }
    public static void closeSecondary(){
        secondaryStage.close();
    }
    public static void closeHelpStage(){
        helpStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
