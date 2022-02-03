package model;

import model.customer.Customer;

public class Driver {
    public static void main(String[] args) {
        int num = 4;
        int result = 0;


        int square = (int)Math.pow(2, 8);

        while ((hasDigit(square) > 1)) {

            int num2 = square;
            int sum = 0;
            while (num2 > 0){
                sum = sum + num2 %10;
                num2 = num2/10;
            }

            square = sum;

        }
        System.out.println(square);

    }

    private static int hasDigit(int square) {
        int count = 0;
        while(square!= 0){
             square/=10;
             count++;
         }
         return count;

    }
}
