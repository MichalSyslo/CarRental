package pl.edu.wszib.car.rental.dao;

import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.sql.Timestamp;
import java.util.List;

public interface IHistoryDAO {
    List<FullReservationInfoModel> getFullHistoryInfoByLogin(String login, Timestamp currentTime);
    List<FullReservationInfoModel> getFullHistoryInfoByName(String name, String surname, Timestamp currentTime);
    List<FullReservationInfoModel> getAllFullHistoryInfo(Timestamp currentTime);
}
