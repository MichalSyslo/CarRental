package pl.edu.wszib.car.rental.model.view;


public class BookingPeriodModel {
    private String startDate=null;
    private String endDate=null;
    private String rentHour=null;

    public BookingPeriodModel(String startDate, String endDate, String rentHour) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentHour = rentHour;
    }

    public BookingPeriodModel() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRentHour() {
        return rentHour;
    }

    public void setRentHour(String rentHour) {
        this.rentHour = rentHour;
    }
}
