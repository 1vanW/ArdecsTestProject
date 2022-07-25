package cashe;

import users.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CasheMemory<Integer ,V extends Serializable> {

    private int maximumSize;
    private Map<Integer,User> myCashe ;

    public CasheMemory(int size) {
        this.maximumSize = size;
        this.myCashe = new HashMap<>();
    }
    // получение юзера по ключу
    public User get(Integer key){
        if (myCashe.containsKey(key)){
            User user = myCashe.get(key);
            user.setLastAccessTime(user.getLastAccessTime());
            return readFromFile(user.getName());
        }
        return null;
    }

    public Integer getLRU(){ // юзер который дольше всех не был
            Integer key = myCashe.keySet().iterator().next();
             LocalDateTime lastDateTime = myCashe.get(key).getLastAccessTime();
        for (Map.Entry<Integer,User> user : myCashe.entrySet()){
            if (user.getValue().getLastAccessTime().isBefore(lastDateTime)){
                key = user.getKey();
                lastDateTime = user.getValue().getLastAccessTime();
            }
        }

        return key;
    }
    //добавление в кэш
    public void add(Integer key, User user){
        /*если размер первысили то удаляем по стратегии LRU (алгоритм для хранения ограниченного
         объема данных: из хранилища вытесняется информация, которая не использовалась дольше всего.)*/
        if (myCashe.size() == maximumSize){
            Integer tempRemove = getLRU();

            deleteFile(myCashe.get(tempRemove).getName());
            myCashe.remove(tempRemove);
        }

        myCashe.put(key,new User(writeToFile(user)));
    }

    //запись в временный файл
    public String writeToFile(User user){
        File tempFile = null;
        try {
           tempFile = File.createTempFile("Data","");
            tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectOutputStream obj = null;
        try
        {
            obj= new ObjectOutputStream(new FileOutputStream(tempFile));
            obj.writeObject(user);
            obj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFile.getAbsolutePath();
    }


    // удаление
    private void deleteFile(String name){
        File file = new File(name);
        file.delete();
    }

    // чтение объекта из файла
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
                ", myCashe=" + myCashe +
                '}';
    }




}
