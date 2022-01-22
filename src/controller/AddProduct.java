package controller;

import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {
    public TableColumn partID;
    public TableColumn partName;
    public TableColumn partInv;
    public TableColumn partPrice;
    public TableColumn assocPartID;
    public TableColumn assocPartName;
    public TableColumn assocPartInv;
    public TableColumn assocPartPrice;
    public TextField partSearch;
    public Button prodAddPart;
    public Button removeAssocPart;
    public Button saveProd;
    public Button cancelProd;
    public TextField prodID;
    public TextField prodName;
    public TextField prodInv;
    public TextField prodPrice;
    public TextField prodMin;
    public TextField prodMax;
    public TableView partsTable;
    public TableView assocPartsTable;
    public TextField modProdName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void onPartSearch(ActionEvent actionEvent) {
    }

    public void onProdAddPart(ActionEvent actionEvent) {
    }

    public void onRemoveAssocPart(ActionEvent actionEvent) {
    }

    public void onSaveProd(ActionEvent actionEvent) {
    }

    public void onCancelProd(ActionEvent actionEvent) throws IOException {
            Parent onCancelAddProd = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));//set up the top hierarchy of the new "page"
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
            //and then cast it to a stage
            Scene scene = new Scene(onCancelAddProd,850,480);
            stage.setTitle("Main Menu");
            stage.setScene(scene); //pass the created scene to the stage
            stage.show();
    }

    public void onProdID(ActionEvent actionEvent) {
    }

    public void onAddProdName(ActionEvent actionEvent) {
    }

    public void onAddProdInv(ActionEvent actionEvent) {
    }

    public void onAddProdPrice(ActionEvent actionEvent) {
    }

    public void onAddProdMin(ActionEvent actionEvent) {
    }

    public void onAddProdMax(ActionEvent actionEvent) {
    }

}
