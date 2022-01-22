package model;

public abstract class Part {
    private static int nextID = 1;
    private int id;
    private String name;
    private double price;
    private int inv;
    private int min;
    private int max;

    public Part(String name, double price, int inv, int min, int max){
        id = nextID;
        nextID++;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max=max;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setStock(int stock){
        this.inv = inv;
    }

    public void setMin(int min){
        this.min = min;
    }

    public void setMax(int max){
        this.max = max;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getInv(){
        return inv;
    }

    public int getMin(){
        return min;
    }

    public int getMax(){
        return max;
    }
}
