package model;

public class inhousePart extends Part{
    private int machineID;

    public inhousePart(int id, String name, double price, int inv, int min, int max,int machineID) {
        super(id, name, price, inv, min, max);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}

