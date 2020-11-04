package hiber.dao;

//import com.sun.org.glassfish.gmbal.NameValue;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   //@Qualifier("getSessionFactory")
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> findByCar(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car WHERE (model = :model and series = :series)");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }
}
