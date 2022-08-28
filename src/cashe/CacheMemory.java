/*
Classname: CacheMemory
Developer: Иван Хохряков
 */
package cashe;

import users.User;

import java.io.*;

import java.time.LocalDateTime;

import java.util.*;
/**
 * Class CacheMemory представляет собой
 *
 * @version   1.0 26.07.22
 * @author    Иван Хохряков
 */
 public class CacheMemory<Integer ,V extends Serializable> {
     /**Переменная хранит в себе максимальный размер Кэша*/
    private final int maximumSize;
    /**Структура данных в которой будут хранится наши юзеры */
    private Map<Integer,User> myCache = new HashMap<>() ;

    public CacheMemory(int size) {
        this.maximumSize = size;

    }
    /**Получение юзера по ключу*/
    public User get(Integer key){
        if (myCache.containsKey(key)){
            User user = myCache.get(key);
            user.setLastAccessTime(user.getLastAccessTime());
            return readFromFile(user.getName());
        }
        return null;
    }

    /**Алгоритм LRU вычисляет юзера который дольше всех не был*/
    public Integer getLRU(){
        Integer key = myCache.keySet().iterator().next();
        LocalDateTime lastDateTime = myCache.get(key).getLastAccessTime();

        for (Map.Entry<Integer,User> user : myCache.entrySet()){

            if (user.getValue().getLastAccessTime().isBefore(lastDateTime)){
                key = user.getKey();
                lastDateTime = user.getValue().getLastAccessTime();
            }
        }

        return key;
    }

    /**Добавление в кэш юзера*/
    public void add(Integer key, User user){
        if (myCache.size() == maximumSize){
            Integer tempRemove = getLRU();
            deleteFile(myCache.get(tempRemove).getName());
            myCache.remove(tempRemove);
        }

        myCache.put(key,new User(writeToFile(user)));
    }

    /**Запись в файл юзера*/
    public String writeToFile(User user){
        File tempFile = null;
        try {
           tempFile = File.createTempFile("Data","");
           tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectOutputStream obj = null;
        try {
            obj= new ObjectOutputStream(new FileOutputStream(tempFile));
            obj.writeObject(user);
            obj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile.getAbsolutePath();
    }

    /**Удаление*/
    private void deleteFile(String name){
        File file = new File(name);
        file.delete();
    }

    /**Чтение юзера из файла*/
    public User readFromFile(String name){
        User user = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(name));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            user = (User)objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public String toString() {
        return "CasheMemory{" +
                "maximumSize=" + maximumSize +
                ", myCashe=" + myCache +
                '}';
    }

    /**Очистка */
    private void clear(){
        for(User user: myCache.values()){
            deleteFile(user.getName());
        }

        myCache.clear();
    }

 }
