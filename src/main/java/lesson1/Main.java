package lesson1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Object[] changeArray = {13, "change"};
        changeArrayItems(changeArray, 0, 1);
        convertListToArrayList(changeArray);
    }

    private static Object[] changeArrayItems(Object[] changeArray, int firstPosition, int secondPosition) {
        Object firstItem = changeArray[firstPosition];
        changeArray[firstPosition] = changeArray[secondPosition];
        changeArray[secondPosition] = firstItem;
        return changeArray;
    }

    private static List<Object> convertListToArrayList(Object[] inArray) {
        return Arrays.asList(inArray);
    }
}
