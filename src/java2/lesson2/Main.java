package java2.lesson2;

public class Main {
    public static void main(String[] args) {
        String[][] rightStringsArray = {
                {"01", "01", "02", "03"},
                {"10", "11", "12", "13"},
                {"20", "21", "22", "23"},
                {"30", "31", "32", "33"},
        };
        try {
            sumArray(rightStringsArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] wrongStringsArray = {
                {"00", "01", "02", "03"},
                {"10", "11", "12", "13"},
                {"20", "21", "22", "23", "24"},
                {"30", "31", "32", "33"},
        };
        try {
            sumArray(wrongStringsArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] anotherWrongStringsArray = {
                {"00", "01", "02", "03"},
                {"10", "11", "12", "13"},
                {"20", "badNumber", "22", "23"},
                {"30", "31", "32", "33"},
        };
        try {
            sumArray(anotherWrongStringsArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private static void sumArray(String[][] strings) throws MyArraySizeException, MyArrayDataException {

        int stringsLength = strings.length;
        int stringLength;

        if (stringsLength != 4)
            throw new MyArraySizeException("Количество строк в массиве равно " + stringsLength + ", а должно быть 4");

        for (int i = 0; i < stringsLength; i++) {
            stringLength = strings[i].length;
            if (stringLength != 4)
                throw new MyArraySizeException("Количество ячеек в строке " + i + " равно " + stringLength + ", а должно быть 4");
        }

        int sum = 0;
        int tmpInt;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    tmpInt = Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(strings[i][j] + " не является целым числом");
                }
                sum += tmpInt;
            }
        }
        System.out.println(sum);
    }
}
