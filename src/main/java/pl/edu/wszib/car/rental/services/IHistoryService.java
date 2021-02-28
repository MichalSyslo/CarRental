package pl.edu.wszib.car.rental.services;

import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.util.List;

public interface IHistoryService {
    List<FullReservationInfoModel> getFullHistoryInfoByLogin(String login);
    List<FullReservationInfoModel> getFullHistoryInfoByName(String name, String surname);
    List<FullReservationInfoModel> getAllFullHistoryInfo();
}
