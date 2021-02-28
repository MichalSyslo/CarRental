package pl.edu.wszib.car.rental.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rental.dao.IHistoryDAO;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;
import pl.edu.wszib.car.rental.services.IHistoryService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    IHistoryDAO historyDAO;

    @Override
    public List<FullReservationInfoModel> getFullHistoryInfoByLogin(String login) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.historyDAO.getFullHistoryInfoByLogin(login, currentDate);
    }

    @Override
    public List<FullReservationInfoModel> getFullHistoryInfoByName(String name, String surname) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.historyDAO.getFullHistoryInfoByName(name, surname, currentDate);
    }

    @Override
    public List<FullReservationInfoModel> getAllFullHistoryInfo() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now().format(fmt));

        return this.historyDAO.getAllFullHistoryInfo(currentDate);
    }
}
