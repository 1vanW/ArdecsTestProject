package users;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }

    private LocalDateTime lastAccessTime;

    public User(String name) {
        this.name = name;
        lastAccessTime = getCurrentDateTime();
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
}


