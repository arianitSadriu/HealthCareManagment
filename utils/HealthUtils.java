package HealthCareManagment.utils;

public class HealthUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNegative(int value) {
        return value < 0;
    }

    public static boolean isNotPositive(int value) {
        return value <= 0;
    }

    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 150;
    }
}