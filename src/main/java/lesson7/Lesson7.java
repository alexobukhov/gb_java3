package lesson7;

import java.lang.reflect.InvocationTargetException;

public class Lesson7 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Annotation annotation = new Annotation();
        AnnotationTest annotationTest = new AnnotationTest();
        annotation.start(AnnotationTest.class, annotationTest);
    }
}
