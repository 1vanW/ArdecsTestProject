/*
Classname: Main
Developer: Иван Хохряков
 */
import cashe.CacheMemory;

import users.User;

/**
 * Class Main для теста класса CacheMemory
 *
 * @version   1.0 26.07.2022
 * @author    Иван Хохряков
 */
 public class Main {

    public static void main(String[] args) {

        CacheMemory<Integer,User> myCache = new CacheMemory<>(3);

        User user1 = new User("Ivan");

        User user2 = new User("maksim");

        User user3 = new User("Oleg");

        User user4 = new User("Anna");

        myCache.add(1,user1);

        myCache.add(2,user2);

        myCache.add(3,user3);

        myCache.add(4,user4);

        myCache.add(5,user4);

        System.out.println(user1.toString());

        System.out.println(myCache.toString());

    }

 }
