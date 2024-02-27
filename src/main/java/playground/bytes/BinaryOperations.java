package playground.bytes;

public class BinaryOperations {
    public static void main(String[] args) {
      int a = 0xa3; // 163 in decimal
      int b = 0xff; // 255 in decimal

      int andResult = a & b; // Bitwise AND
      int shiftLeftResult = a << 1; // Left shift
      int shiftRightResult = a >> 1; // Right shift
      int unsignedShiftRightResult = a >>> 1; // Unsigned right shift

      System.out.println("AND result: " + andResult);
      System.out.println("Left shift result: " + shiftLeftResult);
      System.out.println("Right shift result: " + shiftRightResult);
      System.out.println("Unsigned right shift result: " + unsignedShiftRightResult);
  }
}
