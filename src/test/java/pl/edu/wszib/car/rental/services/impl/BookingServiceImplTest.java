package pl.edu.wszib.car.rental.services.impl;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.car.rental.configuration.TestConfiguration;
import pl.edu.wszib.car.rental.dao.IBookingDAO;
import pl.edu.wszib.car.rental.dao.IHistoryDAO;
import pl.edu.wszib.car.rental.dao.IUserDAO;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.view.BookingPeriodModel;
import pl.edu.wszib.car.rental.services.IBookingService;
import pl.edu.wszib.car.rental.session.SessionObject;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class BookingServiceImplTest {

    @Autowired
    IBookingService bookingService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookingDAO bookingDAO;

    @MockBean
    IHistoryDAO historyDAO;

    @MockBean
    IVehicleDAO vehicleDAO;

    @Resource
    SessionObject sessionObject;

    @Test
    public void correctSetBookingPeriodTest(){
        BookingPeriodModel bookingPeriodModel = new BookingPeriodModel("2021-03-03","2021-03-05","17:00");
        String startRentDate=bookingPeriodModel.getStartDate()+" "+ bookingPeriodModel.getRentHour()+":00";
        String endRentDate= bookingPeriodModel.getEndDate()+" "+ bookingPeriodModel.getRentHour()+":00";

        Timestamp tsStart = Timestamp.valueOf(startRentDate);
        Timestamp tsEnd = Timestamp.valueOf(endRentDate);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startRentDate, fmt);
        LocalDateTime end = LocalDateTime.parse(endRentDate, fmt);

        long days = ChronoUnit.DAYS.between(start, end);

        long expected = 2;

        Assert.assertEquals(expected, days);
    }

    @Test
    public void tooShortBookingPeriodTest(){
        BookingPeriodModel bookingPeriodModel = new BookingPeriodModel("2021-03-03","2021-03-03","17:00");

        this.bookingService.setBookingPeriod(bookingPeriodModel);

        Assert.assertNull(this.sessionObject.getStartDate());
        Assert.assertNull(this.sessionObject.getEndDate());
    }

    @Test
    public void incorrectChronologyBookingTest(){
        BookingPeriodModel bookingPeriodModel = new BookingPeriodModel("2021-03-07","2021-03-03","17:00");

        this.bookingService.setBookingPeriod(bookingPeriodModel);

        Assert.assertNull(this.sessionObject.getStartDate());
        Assert.assertNull(this.sessionObject.getEndDate());
    }
}


