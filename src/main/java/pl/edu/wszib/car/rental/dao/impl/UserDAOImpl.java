package pl.edu.wszib.car.rental.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rental.dao.IUserDAO;
import pl.edu.wszib.car.rental.model.User;


@Repository
public class UserDAOImpl implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM tuser WHERE login = :login");
        query.setParameter("login", login);
        User user = null;
        try{
            user = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Cannot find user");
        }
        session.close();

        return user;
    }

    @Override
    public boolean persistUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return true;
        } catch ( Exception e){
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return false;
    }
}
