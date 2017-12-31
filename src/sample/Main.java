package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Marcin Dobienko
 * <p>
 * Application can open any file (first button ont the left), client could also focus on special type of file,
 * because the program may specify which type of files are able to select (txt, pdf, jpg, gif, png).
 * The second option (second button) opens the certificate in pdf file, it could be replace by other pdf file.
 * The third button opens webpage.
 * The fourth button create and save to file (Orders.dat) a new order for pizza. It include all necessary information for the order.
 * The fifth button opens saved orders from the file (Orders.dat).
 * The sixth button exit the application.
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainController.fxml"));
        primaryStage.setTitle("PizzaMaker");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }
}
