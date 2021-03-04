package pl.edu.wszib.car.rental.model;


import javax.persistence.*;

@Entity (name="tvehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private int seats;
    private double mileage;
    private int bootCapacity;
    private String gearbox;
/*    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;*/

    public Vehicle(String name, double price, int seats, double mileage, int bootCapacity, String gearbox) {
        this.name = name;
        this.price = price;
        this.seats = seats;
        this.mileage = mileage;
        this.bootCapacity = bootCapacity;
        this.gearbox = gearbox;
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getBootCapacity() {
        return bootCapacity;
    }

    public void setBootCapacity(int bootCapacity) {
        this.bootCapacity = bootCapacity;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

}
