package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car =  new Car("BMW", 56);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car);
      car.setUser(user1);
      userService.add(user1);

      car =  new Car("Audy", 5);
      user1 = new User("User2", "Lastname2", "user2@mail.ru", car);
      car.setUser(user1);
      userService.add(user1);

      car =  new Car("Kia", 57);
      user1 = new User("User3", "Lastname3", "user3@mail.ru", car);
      car.setUser(user1);
      userService.add(user1);

      car =  new Car("Honda", 87);
      user1 = new User("User4", "Lastname4", "user4@mail.ru", car);
      car.setUser(user1);
      userService.add(user1);

      //userService.add(user1);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 56)));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Audy", 5)));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Kia", 57)));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Honda", 87)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println("==========================================================================================================================================================================================");


      userService.findByCar("BMW", 56).forEach(x -> {
         User user2 = x.getUser();
         System.out.println("Id = " + user2.getId());
         System.out.println("First Name = " + user2.getFirstName());
         System.out.println("Last Name = " + user2.getLastName());
         System.out.println("Email = " + user2.getEmail());
         System.out.println("Id auto = " + x.getId());
         System.out.println("Model auto = " + x.getModel());
         System.out.println("Series auto = " + x.getSeries());
         System.out.println();
      });

      context.close();
   }
}
