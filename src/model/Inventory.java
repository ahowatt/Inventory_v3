package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** Inventory class
 * @author Abigail Howatt
 */
public class Inventory{

    /** Observable list of all parts. */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /** Observable list of all products. */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /** Observable list of associated parts. */
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** Adds a new part to the master list.
     * @param newPart
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /** Adds a new product to the master list.
     * @param newProduct
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /** Returns a list of all parts.
     * @return allParts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** Returns a list of all products.
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /** Returns a list of associated parts.
     * @return associatedParts
     */
    public static ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }

    /** Creates an error message.
     * @param error
     */
    public static void errorMessage(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText(error);
        alert.showAndWait();
    }

    /** Creates an ID for objects of product and parts classes.
     * @return int
     */
    public static int createID(){
        int maxID = 0;
        for(Part p: Inventory.getAllParts()){
            if(maxID < p.getId()){
                maxID = p.getId();
            }
        }
        int id = maxID +1;
        return id;
    }

}