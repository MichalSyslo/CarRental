package pl.edu.wszib.car.rental.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rental.dao.IBookingDAO;
import pl.edu.wszib.car.rental.model.Reservation;
import pl.edu.wszib.car.rental.model.view.FullReservationInfoModel;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class BookingDAOImpl implements IBookingDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistReservation(Reservation reservation) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(reservation);
            tx.commit();

        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(reservation);
            tx.commit();
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Reservation getReservationById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Reservation> query = session.createQuery("FROM treservation WHERE id = :id");
        query.setParameter("id", id);
        Reservation reservation = query.getSingleResult();
        session.close();

        return reservation;
    }

    @Override
    public List<FullReservationInfoModel> getFullReservationsInfoByLogin(String login, Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE login = :login AND endDate > :currentTime");
        query.setParameter("login", login);
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullReservationInfos = query.getResultList();
        session.close();

        return fullReservationInfos;
    }

    @Override
    public List<FullReservationInfoModel> getFullReservationsInfoByName(String name, String surname, Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE userName = :name AND surname = :surname AND endDate > :currentTime");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullReservationInfos = query.getResultList();
        session.close();

        return fullReservationInfos;
    }

    @Override
    public List<FullReservationInfoModel> getAllFullReservationsInfo(Timestamp currentTime) {
        Session session = this.sessionFactory.openSession();
        Query<FullReservationInfoModel> query = session.createQuery("FROM FullReservationInfoModel WHERE endDate > :currentTime");
        query.setParameter("currentTime", currentTime);
        List<FullReservationInfoModel> fullReservationInfos = query.getResultList();
        session.close();

        return fullReservationInfos;
    }
}
