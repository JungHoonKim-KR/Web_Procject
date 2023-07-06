package solving;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class 긴자리계산 {
    public static void main(String[] args) {
        BigInteger a,b;

        Scanner sc=new Scanner(System.in);
        a=sc.nextBigInteger();
        b=sc.nextBigInteger();

        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        
        
    }
    
}
