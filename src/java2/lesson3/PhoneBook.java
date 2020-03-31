package java2.lesson3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PhoneBook {
    static HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();
    static String phoneOwner;

    public static void main(String[] args) {
        add("Ленин", "+7 (555) 123-45-01");
        add("Сталин", "+7 (555) 123-45-02");
        add("Малинков", "+7 (555) 123-45-03");
        add("Хрущев", "+7 (555) 123-45-04");
        add("Брежнев", "+7 (555) 123-45-05");
        add("Андропов", "+7 (555) 123-45-06");
        add("Черненко", "+7 (555) 123-45-07");
        add("Горбачев","+7 (555) 123-45-08");
        add("Ельцин","+7 (555) 123-45-09");
        add("Путин","+7 (555) 123-45-10");
        add("Медведев","+7 (555) 123-45-11");
        add("Путин","+7 (555) 123-45-12");

        System.out.println(phoneBook);
        Iterator<String> iter;

        phoneOwner = "Горбачев";
        iter=get(phoneOwner);
        System.out.println("Все телефоны абонента " + phoneOwner);
        while (iter.hasNext()){
            System.out.println(iter.next()+ " ");
        }

        phoneOwner = "Путин";
        iter=get(phoneOwner);
        System.out.println("Все телефоны абонента " + phoneOwner);
        while (iter.hasNext()){
            System.out.println(iter.next()+ " ");
        }
    }

    private static void add(String name, String phoneNumber){
        ArrayList<String> arrayList = new ArrayList<>();
        if (phoneBook.containsKey(name)){
            arrayList=phoneBook.get(name);
        }
        arrayList.add(phoneNumber);
        phoneBook.put(name, arrayList);
    }

    private static Iterator<String> get(String name){
        return phoneBook.get(name).iterator();
    }

}
