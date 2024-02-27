package playground.bytes;

import java.math.BigInteger;

public class BigInt {
    public static void main(String[] args) {
      byte[] byteArray = new byte[]{0, 0, 1, -115, -87, 33, 42, 70};
      BigInteger number = new BigInteger(byteArray);
      System.out.println(number);
    }
}
