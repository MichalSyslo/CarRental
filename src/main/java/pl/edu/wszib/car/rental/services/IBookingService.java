package pl.edu.wszib.car.rental.services;

import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.util.List;

public interface IBookingService {
    void setBookingPeriod(BookingPeriodModel bookingPeriod);
    void bookCarByID(int id);
    void cancelReservationById(int id);
    List<FullReservationInfoModel> getFullReservationsInfoByLogin(String login);
    List<FullReservationInfoModel> getFullReservationsInfoByName(String name, String surname);
    List<FullReservationInfoModel> getAllFullReservationsInfo();

}
