package java2.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Words {
    public static void main(String[] args) {

        String[] words = {"Красный", "Оранжевый", "Красный", "Желтый", "Красный", "Оранжевый", "Зеленый", "Голубой", "Синий", "Фиолетовый", "Зеленый", "Синий"};

        ArrayList<String> stringArrayList = new ArrayList<>();
        Collections.addAll(stringArrayList, words);
        HashSet<String> stringHashSet = new HashSet<>(stringArrayList);
        System.out.println("Уникальные значения:" + stringHashSet);
        Iterator<String> stringArrayListIter;
        Iterator<String> stringHashSetIter = stringHashSet.iterator();

        String tmp1, tmp2;
        int counter;
        while (stringHashSetIter.hasNext()){
            tmp1 = stringHashSetIter.next();
            counter=0;
            stringArrayListIter = stringArrayList.iterator();
            while (stringArrayListIter.hasNext()){
                tmp2 = stringArrayListIter.next();
                if (tmp2.equals(tmp1)){
                    counter++;
                }
            }
            System.out.printf("Слово %s встречается в изначальном массиве %d раз\n", tmp1, counter);
        }
    }
}
