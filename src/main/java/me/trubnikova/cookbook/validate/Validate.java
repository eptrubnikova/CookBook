package me.trubnikova.cookbook.validate;

public class Validate {

    public static String validateValue(String value) throws InvalidInputException{
        if (value == null || value.isEmpty() || value.isBlank()){
            throw new InvalidInputException("Проверьте корректность ввода данных");
        }else {
            return value;
        }
    }

    public static double validateValue(double value) throws InvalidInputException{
        if (value == 0){
            throw new InvalidInputException("Проверьте корректность ввода данных");
        }else {
            return value;
        }
    }

    public static int validateValue(int value) throws InvalidInputException{
        if (value == 0){
            throw new InvalidInputException("Проверьте корректность ввода данных");
        }else {
            return value;
        }
    }

}
