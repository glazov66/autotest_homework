package ru.geekbrains.qa;

//import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
//важный импорт, указать, что в импорте должен быть именно он

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TriangleTests {

    public static final double A = 3;
    public static final double B = 4;
    public static final double C = 5;

    static final Logger log = LoggerFactory.getLogger(TriangleTests.class);

    // метод выполняется ПЕРЕД ВСЕМИ тестами
    @BeforeAll
    static void setUp() {
        log.info("\tBEFORE ALL TESTS =>");
    }

    // метод выполняется ПОСЛЕ ВСЕХ тестов
    @AfterAll
    static void tearDown() {
        log.info("\tAFTER ALL TESTS =>");
    }

    // метод выполняется перед каждым тестом
    @BeforeEach
    void setUpTest() {
        log.info("\t\tBEFORE CURRENT TEST");
    }

    // метод выполняется после каждого теста
    @AfterEach
    void tearDownTest() {
        log.info("\t\tAFTER CURRENT TEST");
    }


    // тест, который проверяет работу функции вычисления полупериметра
    @DisplayName("Тест вычисления полупериметра")
    @Test
    public void testHp() {
        log.info("\t\t\tSTART THIS TEST");

        double act_result = AreaCount.hp(A, B, C);
        double exp_result = 6; // перед тестом подставляем правильное значение для исходных А, В и С

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND THIS TEST");
    }

    @DisplayName("Тест вычисления площади треугольника")
    @Test
    public void testArea() {
        log.info("\t\t\tSTART THIS TEST");

        double act_result = AreaCount.area(A, B, C);
        double exp_result = 6;

        Assumptions.assumeTrue(act_result == exp_result, () -> "Тест не выполняем, т.к. результат вычисления не равен ожиданию");
        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND THIS TEST");
    }

    @DisplayName("Тест вычисления площади с разными длинами сторон")
    @ParameterizedTest(name = "Тест #{index}: Стороны треугольника = {arguments}.")
    @CsvSource({
            "3, 4, 5",
            "2, 3, 4",
            "4, 6, 1.5"
    })


    void paramAreaTest(double a, double b, double c) {
        log.info("\t\t\tSTART THIS TEST");

        Assumptions.assumeTrue((a+b)>c && (a+c)>b && (b+c)>a, () -> "Тест пропускаем, т.к. исходные данные некорректные.");

        double act_result = AreaCount.area(a, b, c);
        double res = (a + b + c) / 2.0;
        double exp_result = Math.sqrt(res * (res - a) * (res - b) * (res - c));

        System.out.println("act_result: " + act_result);
        System.out.println("exp_result: " + exp_result);

        Assertions.assertEquals(exp_result, act_result);

        log.info("\t\t\tEND THIS TEST");
    }
}
