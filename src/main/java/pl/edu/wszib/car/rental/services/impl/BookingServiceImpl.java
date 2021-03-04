package pl.edu.wszib.car.rental.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.dao.IBookingDAO;
import pl.edu.wszib.car.rental.model.Reservation;
import pl.edu.wszib.car.rental.model.User;
import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;
import pl.edu.wszib.car.rental.services.IBookingService;
import pl.edu.wszib.car.rental.services.IVehicleService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    IBookingDAO bookingDAO;

    @Autowired
    IVehicleService vehicleService;

    @Resource
    SessionObject sessionObject;


    @Override
    public void setBookingPeriod(BookingPeriodModel bookingPeriodModel) {
        this.sessionObject.setStartDate(null);
        this.sessionObject.setEndDate(null);

        if(bookingPeriodModel.getRentHour().equals("") || bookingPeriodModel.getStartDate().equals("")|| bookingPeriodModel.getEndDate().equals("")){
            this.sessionObject.setInfo("Please, provide necessary information");
            return;
        }

        String startRentDate = bookingPeriodModel.getStartDate()+" "+ bookingPeriodModel.getRentHour()+":00";
        Timestamp tsStart = Timestamp.valueOf(startRentDate);

        String endRentDate = bookingPeriodModel.getEndDate()+" "+ bookingPeriodModel.getRentHour()+":00";
        Timestamp tsEnd = Timestamp.valueOf(endRentDate);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startRentDate, fmt);
        LocalDateTime end = LocalDateTime.parse(endRentDate, fmt);
        LocalDateTime currentDate = LocalDateTime.now();


        long days = ChronoUnit.DAYS.between(start, end);
        if(days <1){
            this.sessionObject.setInfo("Cannot rent a car for less than one day");
            return;
        }

        long hours = ChronoUnit.HOURS.between(currentDate, start);
        long minutes = ChronoUnit.MINUTES.between(currentDate, start);
        if(hours < 0 || minutes <= 0){
            this.sessionObject.setInfo("Choose proper pick-up date");
            return;
        }

        this.sessionObject.setStartDate(tsStart);
        this.sessionObject.setEndDate(tsEnd);
    }

    @Override
    public void bookCarByID(int id) {
        String startDate = this.sessionObject.getStartDate().toString().substring(0, this.sessionObject.getStartDate().toString().length()-2);
        String endDate = this.sessionObject.getEndDate().toString().substring(0, this.sessionObject.getEndDate().toString().length()-2);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDate, fmt);
        LocalDateTime end = LocalDateTime.parse(endDate, fmt);
        long days = ChronoUnit.DAYS.between(start, end);

        Reservation reservation = new Reservation(
                this.vehicleService.getVehicleByID(id),
                this.sessionObject.getLoggedUser(),
                this.sessionObject.getStartDate(),
                this.sessionObject.getEndDate(),
                (double)days*this.vehicleService.getVehicleByID(id).getPrice());

        this.bookingDAO.persistReservation(reservation);
    }

    @Override
    public void cancelReservationById(int id) {
        Reservation reservation = this.bookingDAO.getReservationById(id);
        String startDate = reservation.getStartDate().toString().substring(0, reservation.getStartDate().toString().length()-2);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDate, fmt);
        LocalDateTime currentDate = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(currentDate, start);

        if(days<=3 && this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN){
            this.sessionObject.setInfo("It's not allowed to cancel a reservation, if it starts in 3 days or less");
            return;
        }

        this.bookingDAO.cancelReservation(reservation);
    }

    @Override
    public List<FullReservationInfoModel> getFullReservationsInfoByLogin(String login) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.bookingDAO.getFullReservationsInfoByLogin(login, currentDate);
    }

    @Override
    public List<FullReservationInfoModel> getFullReservationsInfoByName(String name, String surname) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.bookingDAO.getFullReservationsInfoByName(name, surname, currentDate);
    }

    @Override
    public List<FullReservationInfoModel> getAllFullReservationsInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.bookingDAO.getAllFullReservationsInfo(currentDate);
    }
}
