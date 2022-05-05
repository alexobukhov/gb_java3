package lesson6;

public class CheckArray {

    public static void main(String[] args) {
        CheckArray checkArray = new CheckArray();
    }

    public CheckArray() {
    }

    public int[] createTestArrayTrue() {
        int[] inArrayTrue = { 1, 2, 5, 5, 10, 3};
        return inArrayTrue;
    }

    public int[] createTestArrayFalse() {
        int[] inArrayFalse = { 10, 1, 5, 5, 10, 3};
        return inArrayFalse;
    }

    public int[] createArray(int size, int bound) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
            System.out.printf("%3s", array[i]);
        }
        System.out.println("");

        return array;
    }

    /**
     * Задание 3 задача 7
     * @param inArray
     * @return boolean result
     */
    public boolean taskSeven(int[] inArray) {
        boolean result = false;
        int item = inArray.length-2;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < inArray.length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                leftSum = leftSum + inArray[j];
            }
            for (int k = i + 1; k < inArray.length; k++) {
                rightSum = rightSum + inArray[k];
            }
            if (leftSum == rightSum) {
                result = true;
                break;
            } else if (leftSum > rightSum) {
                result = false;
                break;
            }
            leftSum = 0;
            rightSum = 0;
        }

        return result;
    }

    /**
     * Задание 3 задача 8
     * @param inArray
     * @param offsetNumber
     * @return int[] inArray
     */
    public int[] taskEight(int[] inArray, int offsetNumber) {
        int remainder = 0;
        if (Math.abs(offsetNumber) > inArray.length) {
            remainder = offsetNumber % inArray.length;
        }
        if (remainder < 0) {
            for (int i = 0; i < Math.abs(remainder); i++) {
                inArray = shiftArrayLeft(inArray);
            }
        } else if (remainder > 0) {
            for (int i = 0; i < Math.abs(remainder); i++) {
                inArray = shiftArrayRight(inArray);
            }
        } else if (offsetNumber < 0) {
            for (int i = 0; i < Math.abs(remainder); i++) {
                inArray = shiftArrayLeft(inArray);
            }
        } else if (offsetNumber > 0) {
            for (int i = 0; i < Math.abs(remainder); i++) {
                inArray = shiftArrayRight(inArray);
            }
        }
        for (int item : inArray) {
            System.out.printf("%3s", item);
        }
        System.out.println("");
        return inArray;
    }

    /**
     * Смещение массива влево
     * @param inArray
     * @return int[] inArray
     */
    private int[] shiftArrayLeft(int[] inArray) {
        int valueToRemember = inArray[0];
        for (int i = 1; i < inArray.length; i++) {
            inArray[i - 1] = inArray[i];
        }
        inArray[inArray.length - 1] = valueToRemember;

        return inArray;
    }

    /**
     * Смещениие массива вправо
     * @param inArray
     * @return
     */
    private int[] shiftArrayRight(int[] inArray) {
        int valueToRemember = inArray[inArray.length - 1];
        for (int i = inArray.length - 2; i >= 0; i--) {
            inArray[i + 1] = inArray[i];
        }
        inArray[0] = valueToRemember;

        return inArray;
    }
}
