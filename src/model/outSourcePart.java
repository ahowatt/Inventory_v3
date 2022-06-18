package model;

/** Outsourced parts class.
 * Takes parameters from the parts class.
 */
public class outSourcePart extends Part{

     String companyName;
    public outSourcePart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Returns the company name.
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

}
