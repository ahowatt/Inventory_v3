package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import model.inhousePart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    public Button addProductB;
    public Button modifyProductB;
    public Button deleteProductB;
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, Integer> productID;
    @FXML
    public TableColumn<Product, String> productName;
    @FXML
    public TableColumn<Product, Integer> prodInv;
    @FXML
    public TableColumn<Product, Double> prodPrice;
    public Button searchProdB;
    public Button addPartB;
    public Button modifyPartB;
    public Button deletePartB;
    @FXML
    public TableView<Part> partsTable;
    @FXML
    public TableColumn<Part, Integer> partID;
    @FXML
    public TableColumn<Part, String> partName;
    @FXML
    public TableColumn<Part, Integer> partInv;
    @FXML
    public TableColumn<Part, Double> partPrice;
    public Button searchPartsB;

  //private ObservableList<Part> parts = FXCollections.observableArrayList();
  //private ObservableList<Product> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
        prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


    public void onAddProductB(ActionEvent actionEvent) throws IOException {
        Parent onAddProduct = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onAddProduct,850,600);
        stage.setTitle("Add Product");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onModifyProductB(ActionEvent actionEvent) throws IOException {
        Parent onModifyProduct = FXMLLoader.load(getClass().getResource("/view/modifyProduct.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onModifyProduct,850,600);
        stage.setTitle("Modify Product");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();

    }

    public void onDeleteProductB(ActionEvent actionEvent) {
    }

    public void onAddPartB(ActionEvent actionEvent) throws IOException {
        Parent onAddPart = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onAddPart,850,400);
        stage.setTitle("Add Part");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onModifyPartB(ActionEvent actionEvent) throws IOException {
        Parent onModifyPart = FXMLLoader.load(getClass().getResource("/view/modifyPart.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(onModifyPart,850,400);
        stage.setTitle("Modify Part");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onDeletePartB(ActionEvent actionEvent) {
    }

    public void onSearchProdB(ActionEvent actionEvent) {
    }

    public void onSearchPartsB(ActionEvent actionEvent) {
    }

    public void onExitB(ActionEvent actionEvent) {
        System.exit(0);
    }
}
