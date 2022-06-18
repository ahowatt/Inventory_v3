package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Product class
 *
 * @author Abigail Howatt
 */
public class Product {
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;

    /** Creates an object of class product.
     * @param id
     * @param name
     * @param price
     * @param inv
     * @param min
     * @param max
     */
    public Product(int id, String name, double price, int inv, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    /** Sets the product ID. */
    public void setId(int id){
        this.id = id;
    }

    /** Sets the product name. */
    public void setName(String name){
        this.name = name;
    }

    /** Sets the product price. */
    public void setPrice(double price){
        this.price = price;
    }

    /** Sets the product stock. */
    public void setStock(int inv){
        this.inv = inv;
    }

    /** Sets the product minimum. */
    public void setMin(int min){
        this.min = min;
    }

    /** Sets the product maximum. */
    public void setMax(int max){
        this.max = max;
    }

    /** Gets the product ID.
     * @return id
     */
    public int getId(){
        return id;
    }

    /** Gets the product name.
     *
     * @return name
     */
    public String getName(){
        return name;
    }

    /** Gets product price.
     *
     * @return price
     */
    public double getPrice(){
        return price;
    }

    /** Gets product inventory.
     *
     * @return inv
     */
    public int getInv(){
        return inv;
    }

    /** Gets product minimum.
     *
     * @return min
     */
    public int getMin(){
        return min;
    }

    /** Gets product maximum.
     *
     * @return max
     */
    public int getMax(){
        return max;
    }

    /** Adds associated parts.
     *
     * @param part
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /** Gets associated parts.
     *
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
