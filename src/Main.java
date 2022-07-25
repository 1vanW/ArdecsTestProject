import cashe.CasheMemory;
import users.User;

import java.time.LocalDateTime;
//тестим че тут вообще получается
public class Main {
    public static void main(String[] args) {
        CasheMemory<Integer,User> myCashe = new CasheMemory<>(3);

        User user = new User("Ivan");

        User user2 = new User("maksim");
        User user3 = new User("Oleg");
        User user4 = new User("Anna");


        myCashe.add(1,user);
        myCashe.add(2,user2);
        myCashe.add(3,user3);
        myCashe.add(4,user4);
        System.out.println(user.toString());

        System.out.println(myCashe.get(3));

        System.out.println(myCashe.toString());









    }
}
