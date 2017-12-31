package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class Controller {

    //the pdf file which is opened by Button button2
    private static String filename = "sql.pdf";

    //webpage which is opened by Button button3
    private static String webpageLink = "http://marcindobienko.xyz";

    //variables for buttons in numeric list, starts from the left of application
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    //BorderPane
    @FXML
    private BorderPane borderPane;

    //shadow effect for all buttons
    public void initialize() {

        button1.setEffect(new DropShadow());
        button2.setEffect(new DropShadow());
        button3.setEffect(new DropShadow());
        button4.setEffect(new DropShadow());
        button5.setEffect(new DropShadow());
        button6.setEffect(new DropShadow());

    }

    //method dedicated to Button button2, open all files and also focus on specific type of files
    @FXML
    public void openFile() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        List<File> file = chooser.showOpenMultipleDialog(borderPane.getScene().getWindow());
        if (file != null) {
            try {
                for (int i = 0; i < file.size(); i++) {
//                    System.out.println(file.get(i)); //option
                    Desktop.getDesktop().open(file.get(i));
                }
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            System.out.println("Cancelled");
        }
    }

    //dedicated to Button button3, open pdf certificate
    @FXML
    public void openCertificate() {
        try {
            File myFile = new File(filename);
            Desktop.getDesktop().open(myFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //created for Button button3 to open webpage
    @FXML
    public void openWebsite() throws IOException {

        try {
            Desktop.getDesktop().browse(new URL(webpageLink).toURI());
        } catch (URISyntaxException e) {
            e.getMessage();
        }
    }

    //Open Dialog when Button button4 is pressed
    @FXML
    public void showDialog() throws InterruptedException, IOException {
        javafx.scene.control.Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("New order");
        dialog.setHeaderText("Write all necessary information");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("pizzaController.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Pizza controller = fxmlLoader.getController();
            controller.writingData();
        }
    }

    //load data from the file
    @FXML
    public void loadOrders() {

        String orderFile = Pizza.getFileForOrders();

        try (BufferedReader dirFile = new BufferedReader(new FileReader(orderFile))) {
            String input;
            while ((input = dirFile.readLine()) != null) {
                String[] data = input.split("\t");
                String code = data[0];
                String price = data[1];
                String shortDescription = data[2];

                System.out.println("\n" + "Load order: " + "\n" + "Code: " + code + "\n" + "Price: " + price + "\n"
                        + "Description: " + shortDescription + "\n" + "* * * * *");

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //exit
    @FXML
    public void handleExit() {

        Platform.exit();
    }

}
