package lesson7;

import lesson7.annotation.AfterSuite;
import lesson7.annotation.BeforeSuite;
import lesson7.annotation.Test;

public class AnnotationTest {

    @BeforeSuite
    private void beforeSuite() {
        System.out.println("BeforeSuite method");;
    }

    @Test
    private void testOne() {
        System.out.println("TestOne method");
    }

    @Test(priority = 2)
    private void testTwo() {
        System.out.println("TestTwo method");
    }

    @Test(priority = 3)
    private void testThree() {
        System.out.println("TestThree method");
    }

    @AfterSuite
    private void afterSuite() {
        System.out.println("AfterSuite method");;
    }
}
