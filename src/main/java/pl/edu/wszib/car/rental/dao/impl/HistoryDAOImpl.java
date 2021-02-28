package pl.edu.wszib.car.rental.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rental.dao.IHistoryDAO;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class HistoryDAOImpl implements IHistoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<FullReservationInfoModel> getFullHistoryInfoByLogin(String login, Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE login = :login AND endDate < :currentTime");
        query.setParameter("login", login);
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullHistoryInfos = query.getResultList();
        session.close();

        return fullHistoryInfos;
    }

    @Override
    public List<FullReservationInfoModel> getFullHistoryInfoByName(String name, String surname, Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE userName = :name AND surname = :surname AND endDate < :currentTime");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullHistoryInfos = query.getResultList();
        session.close();

        return fullHistoryInfos;
    }

    @Override
    public List<FullReservationInfoModel> getAllFullHistoryInfo(Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE endDate < :currentTime");
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullHistoryInfos = query.getResultList();
        session.close();

        return fullHistoryInfos;
    }
}
