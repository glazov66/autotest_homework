package ru.geekbrains.qa;

public class AreaCount {

    // метод для вычисление полупериметра
    public static double hp(double a, double b, double c) {
        return (a + b + c) / 2.0;
    }

    // метод для вычисления площади треугольника
    public static double area(double a, double b, double c) {
        double res = hp(a, b, c);
        return Math.sqrt(res * (res - a) * (res - b) * (res - c));
    }


}
