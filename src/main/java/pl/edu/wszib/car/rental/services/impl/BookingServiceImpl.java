package pl.edu.wszib.car.rental.services.impl;

import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.services.IBookingService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class BookingServiceImpl implements IBookingService {

    @Resource
    SessionObject sessionObject;

    @Override
    public void setBookingPeriod(BookingPeriodModel bookingPeriodModel) {
        Date date;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh");

        String startRentDate = bookingPeriodModel.getStartDate()+" "+ bookingPeriodModel.getRentHour()+":00";
        try {
            date = format.parse(startRentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp ts = Timestamp.valueOf(startRentDate);
        System.out.println("TS: "+ts);


        String endRentDate = bookingPeriodModel.getEndDate()+" "+ bookingPeriodModel.getRentHour()+":00";
        try {
            date = format.parse(endRentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp ts2 = Timestamp.valueOf(endRentDate);


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startRentDate, fmt);
        LocalDateTime end = LocalDateTime.parse(endRentDate, fmt);

        long days = ChronoUnit.DAYS.between(start, end);
        System.out.println("Time difference in seconds: " + days);

        if(days <1){
            this.sessionObject.setInfo("Cannot rent a car for less than one day");
            return;
        }

        this.sessionObject.setStartDate(ts);
        this.sessionObject.setEndDate(ts2);
    }
}
