package pl.edu.wszib.car.rental.dao;

import pl.edu.wszib.car.rental.model.Reservation;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface IBookingDAO {
    void persistReservation(Reservation reservation);
    void cancelReservation(Reservation reservation);
    Reservation getReservationById(int id);
    List<FullReservationInfoModel> getFullReservationsInfoByLogin(String login, Timestamp currentTime);
    List<FullReservationInfoModel> getFullReservationsInfoByName(String name, String surname, Timestamp currentTime);
    List<FullReservationInfoModel> getAllFullReservationsInfo(Timestamp currentTime);
}
