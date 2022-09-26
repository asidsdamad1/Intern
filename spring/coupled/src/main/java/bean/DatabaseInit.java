package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInit {
    private List<User> listOfUsers = new ArrayList<>();

    public DatabaseInit() {
        System.out.println("Constructor called");
    }

    @Bean
    public void getUsername() {
        System.out.println(listOfUsers.get(0));
    }

    @PostConstruct
    public void init() {
        User user = new User("User", "Pass1");
        User user1 = new User("User1", "Pass2");
        User user2 = new User("User3", "Pass3");

        listOfUsers.add(user);
        listOfUsers.add(user1);
        listOfUsers.add(user2);
        System.out.println("-----------List of users added in init() method ------------");
        for (User user3 : listOfUsers) {
            System.out.println(user3.toString());
        }
        // save to database
    }

    @PreDestroy
    public void destroy() {
        // Delete from database
        listOfUsers.clear();
        System.out.println("-----------After of users removed from List in destroy() method ------------");
        for (User user3 : listOfUsers) {
            System.out.println(user3.toString());
        }
        System.out.println("List is clean up ..");
    }
}
