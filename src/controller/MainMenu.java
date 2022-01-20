package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu<Parts, Products> implements Initializable {
    public Button addProductB;
    public Button modifyProductB;
    public Button deleteProductB;
    public TableView productTable;
    public TableColumn productID;
    public TableColumn productName;
    public TableColumn prodInv;
    public TableColumn prodPrice;
    public Button searchProdB;
    public Button addPartB;
    public Button modifyPartB;
    public Button deletePartB;
    public TableView partsTable;
    public TableColumn partID;
    public TableColumn partName;
    public TableColumn partInv;
    public TableColumn partPrice;
    public Button searchPartsB;

  private ObservableList<Part> parts = FXCollections.observableArrayList();
  private ObservableList<Product> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      partsTable.setItems(parts);
      productTable.setItems(products);

      partID.setCellValueFactory(new PropertyValueFactory<>("id"));
      partName.setCellValueFactory(new PropertyValueFactory<>("name"));
      partInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
      partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

      productID.setCellValueFactory(new PropertyValueFactory<>("id"));
      productName.setCellValueFactory(new PropertyValueFactory<>("name"));
      prodInv.setCellValueFactory(new PropertyValueFactory<>("inv"));
      prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void toAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));//set up the top hierarchy of the new "page"
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow(); //set it up as a button action event,
        //and then cast it to a stage
        Scene scene = new Scene(root,850,400);
        stage.setTitle("Add Parts");
        stage.setScene(scene); //pass the created scene to the stage
        stage.show();
    }

    public void onAddProductB(ActionEvent actionEvent) {
    }

    public void onModifyProductB(ActionEvent actionEvent) {
    }

    public void onDeleteProductB(ActionEvent actionEvent) {
    }

    public void onAddPartB(ActionEvent actionEvent) {
    }

    public void onModifyPartB(ActionEvent actionEvent) {
    }

    public void onDeletePartB(ActionEvent actionEvent) {
    }

    public void onSearchProdB(ActionEvent actionEvent) {
    }

    public void onSearchPartsB(ActionEvent actionEvent) {
    }
}
