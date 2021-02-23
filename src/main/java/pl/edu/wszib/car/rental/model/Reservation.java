package pl.edu.wszib.car.rental.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="treservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private int carId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp endDate;
    private double totalPrice;

    public Reservation(int carId, User user, Timestamp startDate, Timestamp endDate, double totalPrice) {
        this.carId = carId;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
