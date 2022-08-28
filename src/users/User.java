/*
Classname: Main
Developer: Иван Хохряков
 */
package users;

import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.Objects;

/**
 * Class User представляет собой элемент который будет хранится в кэше
 *
 * @version   1.0 26.07.2022
 * @author    Иван Хохряков
 */
 public class User implements Serializable {

    private String name;
    /**Переменная хранит время посещения*/
    private LocalDateTime lastAccessTime;

    public User(String name) {
        this.name = name;
        lastAccessTime = getCurrentDateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if ((o == null)
                || (getClass() != o.getClass())){
            return false;
        }

        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(lastAccessTime, user.lastAccessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastAccessTime);
    }

    private LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

     @Override
     public String toString() {
         return "User{" +
                 "name='" + name + '\'' +
                 ", lastAccessTime=" + lastAccessTime +
                 '}';
     }
 }


