package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import controller.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct implements Initializable {
    public TextField partSearch;
    public Button modProdPart;
    public Button removeAssocPart;
    public Button saveProd;
    public Button cancelModProd;
    public TextField modProdID;
    public TextField modProdName;
    public TextField modProdInv;
    public TextField modProdPrice;
    public TextField modProdMin;
    public TextField modProdMax;

    public TableView<Part> partsTable;
    @FXML
    public TableColumn<Part, Integer> partID;
    @FXML
    public TableColumn<Part, String> partName;
    @FXML
    public TableColumn<Part, Integer> partInv;
    @FXML
    public TableColumn<Part, Double> partPrice;

    public TableColumn<Part, Integer> assocPartID;
    public TableColumn<Part, String> assocPartName;
    public TableColumn<Part, Integer> assocPartInv;
    public TableColumn<Part, Double> assocPartPrice;

    public void onPartSearch(ActionEvent actionEvent) {
    }

    public void onModProdPart(ActionEvent actionEvent) {
    }

    public void onRemoveAssocPart(ActionEvent actionEvent) {
    }

    public void onSaveProd(ActionEvent actionEvent) {
    }

    public void onCancelModProd(ActionEvent actionEvent) throws IOException {
            Parent onCancelModProd = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));//set up the top hierarchy of the new "page"
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
            //and then cast it to a stage
            Scene scene = new Scene(onCancelModProd,850,480);
            stage.setTitle("Main Menu");
            stage.setScene(scene); //pass the created scene to the stage
            stage.show();
    }


    public void onModProdID(ActionEvent actionEvent) {
    }

    public void onModProdName(ActionEvent actionEvent) {
    }

    public void onModProdInv(ActionEvent actionEvent) {
    }

    public void onModProdPrice(ActionEvent actionEvent) {
    }

    public void onModProdMin(ActionEvent actionEvent) {
    }

    public void onModProdMax(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        assocPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
