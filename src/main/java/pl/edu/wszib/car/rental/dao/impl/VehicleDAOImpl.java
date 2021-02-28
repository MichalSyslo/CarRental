package pl.edu.wszib.car.rental.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rental.dao.IVehicleDAO;
import pl.edu.wszib.car.rental.model.Vehicle;
import pl.edu.wszib.car.rental.session.SessionObject;


import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@Repository
public class VehicleDAOImpl implements IVehicleDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Resource
    SessionObject sessionObject;

    @Override
    public Vehicle getVehicleByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM tvehicle WHERE id = :id");
        query.setParameter("id", id);
        Vehicle vehicle = query.getSingleResult();
        session.close();

        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx=session.beginTransaction();
            session.update(vehicle);
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
    public List<Vehicle> getAllVehicles() {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.edu.wszib.car.rental.model.Vehicle");
        List<Vehicle> vehicles = query.getResultList();
        session.close();

        return vehicles;
    }

    @Override
    public List<Vehicle> getAvailableVehicles(Timestamp start, Timestamp end) {
        Session session = this.sessionFactory.openSession();
        NativeQuery<Vehicle> query = session.createSQLQuery("CALL getAvailableVehicles(:startDate, :endDate)");
        query.addEntity(Vehicle.class);
        query.setParameter("startDate", start);
        query.setParameter("endDate", end);
        List<Vehicle> vehicles = query.getResultList();
        session.close();

        return vehicles;
    }

    @Override
    public void persistVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(vehicle);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(vehicle);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
