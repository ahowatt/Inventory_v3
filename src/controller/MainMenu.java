package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** Controls the main menu.
 * @author Abigail Howatt
 */
public class MainMenu implements Initializable {

    /** Button that goes to the Add Product menu. */
    public Button addProductB;
    /** Button that goes to the Modify Product menu. */
    public Button modifyProductB;

    @FXML
    /** Button that deletes selected product. */
    public Button deleteProductB;
    @FXML
    /** Table of product objects. */
    public TableView<Product> productTable;
    @FXML
    /** Table column displaying product ID. */
    public TableColumn<Product, Integer> productID;
    @FXML
    /** Table column displaying product name. */
    public TableColumn<Product, String> productName;
    @FXML
    /** Table column displaying product inventory. */
    public TableColumn<Product, Integer> prodInv;
    @FXML
    /** Table column displaying product price. */
    public TableColumn<Product, Double> prodPrice;
    /** Button to search products. */
    public Button searchProdB;
    /** Button that goes to the add part menu. */
    public Button addPartB;
    /** Button that goes to the modify part menu. */
    public Button modifyPartB;
    /** Button that deletes selected part. */
    public Button deletePartB;
    @FXML
    /** Table displaying all the parts. */
    public TableView<Part> partsTable;
    @FXML
    /** Table column dislaying part ID. */
    public TableColumn<Part, Integer> partID;
    @FXML
    /** Table column displaying part name. */
    public TableColumn<Part, String> partName;
    @FXML
    /** Table column displaying part inventory. */
    public TableColumn<Part, Integer> partInv;
    @FXML
    /** Table column displaying part price. */
    public TableColumn<Part, Double> partPrice;
    /** Button that searches the part list. */
    public Button searchPartsB;
    /** Button that exits the program. */
    public Button exitB;
    /** Text field that gets part search string. */
    public TextField searchPartTxt;
    /** Text field that gets product search string. */
    public TextField searchProdText;


    public MainMenu() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partsTable.setItems(Inventory.getAllParts());
        this.productTable.setItems(Inventory.getAllProducts());
        this.partID.setCellValueFactory(new PropertyValueFactory("id"));
        this.partName.setCellValueFactory(new PropertyValueFactory("name"));
        this.partInv.setCellValueFactory(new PropertyValueFactory("stock"));
        this.partPrice.setCellValueFactory(new PropertyValueFactory("price"));
        this.productID.setCellValueFactory(new PropertyValueFactory("id"));
        this.productName.setCellValueFactory(new PropertyValueFactory("name"));
        this.prodInv.setCellValueFactory(new PropertyValueFactory("inv"));
        this.prodPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }

    /** Button that goes to the add product screen.
     * @param actionEvent
     */
    public void onAddProductB(ActionEvent actionEvent) throws IOException {
        Parent onAddProduct = (Parent)FXMLLoader.load(this.getClass().getResource("/view/addProduct.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onAddProduct, 850.0D, 600.0D);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Button that goes to the modify product screen.
     * Passes information from the selected product to the modify screen.
     */
    public void onModifyProductB(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyProduct.fxml"));
        loader.load();

        ModifyProduct modProdController = loader.getController();
        Product product = productTable.getSelectionModel().getSelectedItem();
        if(product != null){

            modProdController.receiveProduct(product);
        } else{
            Inventory.errorMessage("Please select a product. ");
            return;
        }

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Button that deletes a product.
     * @param actionEvent confirms before deleting product.
     */
    public void onDeleteProductB(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setContentText("Do you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK && productTable.getSelectionModel().getSelectedItem() != null) {
            Product product = productTable.getSelectionModel().getSelectedItem();
                if (product.getAllAssociatedParts().size() != 0) {
                    Inventory.errorMessage("Please remove all parts from the product first.");
                } else {
                    Inventory.getAllProducts().remove(product);
                }
                productTable.getSelectionModel().clearSelection();

            }
         else if(result.get() == ButtonType.CANCEL){
             productTable.getSelectionModel().clearSelection();
             return;
        } else{
             Inventory.errorMessage("Please select a product.");
        }
    }

    /** Button that goes to the add part screen. */
    public void onAddPartB(ActionEvent actionEvent) throws IOException {
        Parent onAddPart = (Parent)FXMLLoader.load(this.getClass().getResource("/view/addPart.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onAddPart, 850.0D, 400.0D);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Button that goes to the modify part menu.
     * @param actionEvent
     * Passes information for the selected part to the modify screen.
     */
    public void onModifyPartB(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyPart.fxml"));
        loader.load();

        Part selectedPart = (Part) partsTable.getSelectionModel().getSelectedItem();
        if(partsTable.getSelectionModel().getSelectedItem() != null) {
            modifyPart MPController = loader.getController();
            MPController.receivePart(selectedPart);
        }
        else{
            Inventory.errorMessage("Please select a part.");
            return;
        }

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /** Button that deletes the selected part.
     * @param actionEvent
     */
    public void onDeletePartB(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setContentText("Do you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (result.get() == ButtonType.OK && part!= null){
            Inventory.getAllParts().remove(part);
        } else if (result.get() == ButtonType.OK && part == null){
            Inventory.errorMessage("Please select a part.");
        } else {
            partsTable.getSelectionModel().clearSelection();
            return;
        }
    }

    /** Button that searches the product list.
     * @param actionEvent
     */
    public void onSearchProdB(ActionEvent actionEvent) {
        String q = searchProdText.getText();
        ObservableList<Product> products = searchByProdName(q);

        if(products.size() == 0) {
            try {
                int id = Integer.parseInt(q);
                Product p = searchByIDProd(id);
                if (p != null) {
                    products.add(p);
                }

            }catch(NumberFormatException ignored){
                Inventory.errorMessage("The product was not found.");
                return;
            }
        }
        productTable.setItems(products);
        searchProdText.setText("");
    }

    /** Searches product name by search string.
     * @param partialName
     * @return namedProducts observable list.
     */
    private ObservableList<Product> searchByProdName(String partialName){
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for( Product p : allProducts){
            String s = p.getName().toLowerCase();
            if(s.contains(partialName)){
                namedProducts.add(p);
            }
        }
        return namedProducts;
    }

    /** Searches all parts for the search string.
     * @param partialName
     * @return namedParts, observable list.
     */
    private ObservableList<Part> searchByPartName(String partialName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts){
            String s = p.getName().toLowerCase();
            if(s.contains(partialName)){
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /** Button that searches the parts.
     * @param actionEvent
     */
    public void onSearchPartsB(ActionEvent actionEvent) {
        String a = searchPartTxt.getText();
        ObservableList<Part> parts = searchByPartName(a);

        if(parts.size() == 0) {
            try {
                int id = Integer.parseInt(a);
                Part p = searchByIDPart(id);
                if (p != null) {
                    parts.add(p);
                }

            }catch(NumberFormatException ignored){
                Inventory.errorMessage("The part was not found.");
                return;
            }
        }
        partsTable.setItems(parts);
        searchPartTxt.setText("");
    }

    /** Searches parts by ID.
     * @param id
     * @return parts that match ID.
     */
    private Part searchByIDPart(int id){
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /** Searches products by ID.
     * @param id
     * @return products that match ID.
     */
    private Product searchByIDProd(int id){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product p : allProducts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /** Button that exits the program after confirmation.
     * @param actionEvent
     */
    public void onExitB(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm quit");
        alert.setContentText("Do you want to leave?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            System.exit(0);
        } else{
            return;
        }
    }

}
