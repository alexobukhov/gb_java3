import lesson6.CheckArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CheckArrayTest {

    private static CheckArray checkArray;

    @BeforeEach
    void startUp() {
        checkArray = new CheckArray();
    }

    @DisplayName("Проверка баланса массива (true)")
    @Test
    void testTaskSevenTrue() {
        Assertions.assertTrue(() -> {
            int[] inArray = checkArray.createTestArrayTrue();;
            return checkArray.taskSeven(inArray);
        });
    }

    @DisplayName("Проверка баланса массива (false)")
    @Test
    void testTaskSevenFalse() {
        Assertions.assertFalse(() -> {
            int[] inArray = checkArray.createTestArrayFalse();
            return checkArray.taskSeven(inArray);
        });
    }

    @DisplayName("Проверка смещения массива")
    @ParameterizedTest
    @MethodSource("dataForShiftArrayTest")
    void testTaskEight(int[] inArray, int offset, int[] outArray) {
        Assertions.assertEquals(outArray, checkArray.taskEight(inArray, offset));
    }

    public static Stream<Arguments> dataForShiftArrayTest() {
        List<Arguments> argumentsList = new ArrayList<>();
        int[] inArray = new int[10];
        for (int i = 0; i < inArray.length; i++) {
            inArray[i] = (int) (Math.random() * 50);
            System.out.printf("%3s", inArray[i]);
        }
        System.out.println(System.lineSeparator());
        int offset = 5;
        int[] outArray = inArray;
        for (int i = 0; i < Math.abs(offset); i++) {
            int valueToRemember = outArray[outArray.length - 1];
            for (int j = outArray.length - 2; j >= 0; j--) {
                outArray[j + 1] = outArray[j];
            }
            outArray[0] = valueToRemember;
        }
        argumentsList.add(Arguments.arguments(inArray, offset, outArray));
        return argumentsList.stream();
    }
}
