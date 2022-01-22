package model;

public class outsourcePart extends Part{
    private String companyName;

    public outsourcePart(int id, String name, double price, int inv, int min, int max,String companyName) {
        super(name, price, inv, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
