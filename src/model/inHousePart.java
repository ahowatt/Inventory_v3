package model;

/** Creates an object of class in-house part.
 * Takes parameters of class part and adds machine ID.
 */
public class inHousePart extends Part {

    int machineID;

    public inHousePart(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** Gets the machine ID.
     * @return int machineID
     */
    public int getMachineID() {
        return machineID;
    }

}
