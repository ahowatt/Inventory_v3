package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/** Controls the Add Product screen.
 * @author Abigail Howatt
 * Adds a new object of the product class.
 */
public class AddProduct implements Initializable {
    /**Table column displaying the part ID number.*/
    public TableColumn partID;
    /** Table column displaying part name. */
    public TableColumn partName;
    /** Table column displaying part inventory levels. */
    public TableColumn partInv;
    /** Table column displaying part price. */
    public TableColumn partPrice;
    /** Table column displaying part ID in the associated table.*/
    public TableColumn assocPartID;
    /** Column displaying part names in the associated table. */
    public TableColumn assocPartName;
    /** Column displaying inventory in the associated parts table. */
    public TableColumn assocPartInv;
    /** Column displaing price in the associated parts table. */
    public TableColumn assocPartPrice;
    /** Search field for the parts table. */
    public TextField partSearch;
    /** Button to add parts to the associated parts table. */
    public Button prodAddPart;
    /** Button to remove parts from the associated table. */
    public Button removeAssocPart;
    /** Button to save a new product. */
    public Button saveProd;
    /** Cancel button returns to main menu. */
    public Button cancelProd;
    /** Text field that displays the product ID. */
    public TextField prodID;
    /** Table displaying all parts. */
    public TableView partsTable;
    /** Table displaying associated parts. */
    public TableView assocPartsTable;
    /** Text field that gets the product name. */
    public TextField prodName;
    /** Text field that gets the product inventory. */
    public TextField prodInv;
    /** Text field that gets the product price. */
    public TextField prodPrice;
    /** Text field that gets the product maximum. */
    public TextField prodMax;
    /** Text field that gets the product minimum. */
    public TextField prodMin;
    /** Observable list to hold associated parts. */
    public ObservableList<Part> addedParts = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets values for the parts table
        this.partsTable.setItems(Inventory.getAllParts());
        this.partID.setCellValueFactory(new PropertyValueFactory("id"));
        this.partName.setCellValueFactory(new PropertyValueFactory("name"));
        this.partInv.setCellValueFactory(new PropertyValueFactory("stock"));
        this.partPrice.setCellValueFactory(new PropertyValueFactory("price"));

        //sets values for the table of associated parts
        this.assocPartID.setCellValueFactory(new PropertyValueFactory("id"));
        this.assocPartName.setCellValueFactory(new PropertyValueFactory("name"));
        this.assocPartInv.setCellValueFactory(new PropertyValueFactory("stock"));
        this.assocPartPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }

    /** Searches the list of all parts on button click.
     * @param actionEvent
     */
    public void onPartSearch(ActionEvent actionEvent) {
        String a = partSearch.getText();
        ObservableList<Part> parts = searchByPartName(a);

        if (parts.size() == 0) {
            try {
                int id = Integer.parseInt(a);
                Part p = searchByIDPart(id);
                if (p != null) {
                    parts.add(p);
                }

            } catch (NumberFormatException ignored) {
                Inventory.errorMessage("The part was not found.");
                return;
            }
        }
        partsTable.setItems(parts);
        partSearch.setText("");
    }

    /** Searches for parts by ID.
     * @param id
     * @return null
     */
    private Part searchByIDPart(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /** Creates a list with parts matching the search string.
     * @param partialName
     * @return namedParts, observable list.
     */
    private ObservableList<Part> searchByPartName(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part p : allParts) {
            if (p.getName().contains(partialName)) {
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /** Adds parts to the associated parts table.
     * @param actionEvent
     * Takes a selected part from the parts table.
     * RUNTIME ERROR: I had this as a static method and the parts were being associated to every product.
     * RUNTIME ERROR: It was a non-static method being called from a static class, but now I can't remember how.
     * RUNTIME ERROR: I created an instance of the class so that I could call a non-static method. That solved the associated issue.
     *
     */
    public void onProdAddPart(ActionEvent actionEvent) {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            Inventory.errorMessage("Please select a part.");
        }
        addedParts.add(part);
        assocPartsTable.setItems(addedParts);

    }

    /** Removes parts from the associated parts table.
     * @param actionEvent
     * Confirms deletion before removing part from associated parts.
     */
    public void onRemoveAssocPart(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("You are removing a part.");
        alert.setContentText("Do you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        Part part = (Part) assocPartsTable.getSelectionModel().getSelectedItem();
        if (result.get() == ButtonType.OK) {
            addedParts.remove(part);
        } else if (result.get() == ButtonType.OK && part == null) {

        }

    }

    /** Adds a new product.
     * @param actionEvent
     * Requires parameters for the Product constructor.
     * Validates values before creating a new object.
     */
    public void onSaveProd(ActionEvent actionEvent) throws IOException {

        try {
            Double.parseDouble(this.prodPrice.getText());
        } catch (NumberFormatException e) {
            Inventory.errorMessage("The price must be a double XX.XX");
            return;
        }

        try {
            Integer.parseInt(this.prodInv.getText());
        } catch (NumberFormatException e) {
            Inventory.errorMessage("The inventory must be an integer.");
            return;
        }

        try {
            Integer.parseInt(this.prodMax.getText());
        } catch (NumberFormatException e) {
            Inventory.errorMessage("The maximum must be an integer.");
            return;
        }

        try {
            Integer.parseInt(this.prodMin.getText());
        } catch (NumberFormatException e) {
            Inventory.errorMessage("The minimum must be an integer.");
            return;

        }
        String name = this.prodName.getText();
        if (this.prodName.getLength() == 0) {
            Inventory.errorMessage("The product needs a name.");
            return;
        }

        double price = Double.parseDouble(this.prodPrice.getText());
        int inv = Integer.parseInt(this.prodInv.getText());
        int min = Integer.parseInt(this.prodMin.getText());
        int max = Integer.parseInt(this.prodMax.getText());

        if (inv < min || inv > max) {
            Inventory.errorMessage("Inventory must be between maximum and minimum.");
        }

        if (min > max) {
            Inventory.errorMessage("Maximum must be greater than minimum.");
        }

        if(addedParts.isEmpty()){
            Inventory.errorMessage("The product must have at least one part.");
            return;
        }

        else {
            Product product = new Product(Inventory.createID(), name, price, inv, min, max);
            for (Part p : addedParts) {
                product.addAssociatedPart(p);
            }

            Inventory.addProduct(product);
        }

            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene  = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setTitle("Main Menu");
            stage.setScene(new Scene (scene));
            stage.show();
        }

    /** Returns to the main menu on button click.
     * @param actionEvent
     */
    public void onCancelProd(ActionEvent actionEvent) throws IOException {
        Parent onCancelAddProd = (Parent)FXMLLoader.load(this.getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(onCancelAddProd, 850.0D, 480.0D);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

      /** Takes part information from the selected part.
     * @param mouseEvent
     */
    public void choosePart(MouseEvent mouseEvent) {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part == null) {
            System.out.println("Nothing is selected");
            //needs a dialog box
        } else {
            int id = part.getId();
            String name = part.getName();
            int inv = part.getStock();
            double price = part.getPrice();
            int min = part.getMin();
            int max = part.getMax();
        }
    }
    
}
