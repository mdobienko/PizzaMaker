package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pizza {

    //name for the order file
    private static String fileForOrders = "Orders.dat";
    //optional Dialog for future test
    @FXML
    private DialogPane dialogPane;
    //Short description in dialog
    @FXML
    private TextArea shortDescriptionField;
    //code of employee
    @FXML
    private PasswordField codeField;
    //price
    @FXML
    private TextField priceField;

    //get data from the file with orders for pizza
    public static String getFileForOrders() {
        return fileForOrders;
    }

    //input all necessary data to pizza order
    public void writingData() throws IOException, InterruptedException {
        String code = codeField.getText().trim();
        String price = priceField.getText().trim();
        String shortDescription = shortDescriptionField.getText().trim();

        System.out.println("* * * * *" + "\n" + "Order: " + "\n" + "Code: " + code + "\n"
                + "Price: " + price + " zł" + "\n" + "Description: " + shortDescription);

        List<String> pizzaList2 = new ArrayList<>();
        pizzaList2.add(code);
        pizzaList2.add(price);
        pizzaList2.add(shortDescription);

        FileWriter file = new FileWriter(fileForOrders, true);
        BufferedWriter bw = new BufferedWriter(file);

        try {

            if (price.matches("[0-9]+")) {
                bw.write(String.format("%s\t%s\t%s",
                        code,
                        price,
                        shortDescription));
                bw.newLine();
            } else {
                System.out.println("Incorrect price, order cancelled");
                try {
                    warning();
                } catch (InterruptedException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    //show warning when the price is incorrect, connected to mainPizzaWarning.fxml
    public void warning() throws IOException, InterruptedException {
        javafx.scene.control.Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Warning!");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("mainPizzaWarning.fxml"));
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

            dialog.showAndWait();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
