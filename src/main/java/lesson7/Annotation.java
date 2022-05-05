package lesson7;

import lesson7.annotation.AfterSuite;
import lesson7.annotation.BeforeSuite;
import lesson7.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Annotation {

    public static void start(Class<?> annotationClass, AnnotationTest annotationTest) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = annotationClass.getDeclaredMethods();
        int beforeSuiteMethodCount = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(BeforeSuite.class))
                .toArray().length;
        if (beforeSuiteMethodCount > 1) {
            throw new RuntimeException();
        } else {
            Method beforeSuiteMethod = Arrays.stream(methods)
                    .filter(m -> m.isAnnotationPresent(BeforeSuite.class))
                    .findFirst()
                    .orElse(null);
            beforeSuiteMethod.setAccessible(true);
            beforeSuiteMethod.invoke(annotationTest);
        };
        int afterSuiteMethodCount = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(AfterSuite.class))
                .toArray().length;
        if (afterSuiteMethodCount > 1) {
            throw new RuntimeException();
        } else {
            Method afterSuiteMethod = Arrays.stream(methods)
                    .filter(m -> m.isAnnotationPresent(AfterSuite.class))
                    .findFirst()
                    .orElse(null);
            afterSuiteMethod.setAccessible(true);
            afterSuiteMethod.invoke(annotationTest);
        }
        List<Method> testMethods = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
        Collections.sort(testMethods, Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()));
        for (Method method : testMethods) {
            method.setAccessible(true);
            method.invoke(annotationTest);
        }
    }
}
