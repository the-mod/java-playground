package playground.bytes;

public class ByteInt2 {

    public static void main(String[] args) {
        // 0000 0000 0000 0000 0000 0000 1010 1111
        int i = 175;
        //byte b2 = 0b0110_0101;
        String binaryString = Integer.toBinaryString(i);
        // fill up to 32 bit
        String formattedBinaryString = String.format("%32s", binaryString).replaceAll(" ", "0");// 32-bit Integer
        System.out.println("Full 32 bit binary String of " + i + ": " + formattedBinaryString);
        // discard: 1010 1111 => -128 + 32 + 8 + 4 + 2 + 1 = -81

        byte b = (byte) i;
        System.out.println(i + " as byte " + b);

        // try with 60
        int sixty = 60; // 0011 1100
        byte sixtyByte = (byte) sixty;
        System.out.println(sixty + " is as byte " + sixtyByte);

        // try with -60
        int minusSixty = -60; // 1111 1111 1111 1111 1111 1111 1100 0100
        String binaryMinus60 = Integer.toBinaryString(minusSixty);
        System.out.println("Binary String of " + minusSixty + ": " + binaryMinus60);
        byte minusSixtyByte = (byte) minusSixty;
        System.out.println(minusSixty + " is as byte " + minusSixtyByte);


        // 0000 0000 0000 0000 0000 0000 1000 0000
        int x = 128;
        System.out.println("Binary String of " + x + ": " + Integer.toBinaryString(x));
        // inverse: 1111 1111 1111 1111 1111 1111 0111 1111
        // +1:      1111 1111 1111 1111 1111 1111 1000 0000 => -128 as decimal
        byte y = (byte) x;
        System.out.println("int " + x + " is byte " + y);
    }
}
