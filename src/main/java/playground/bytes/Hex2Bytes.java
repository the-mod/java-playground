package playground.bytes;

import java.math.BigInteger;

public class Hex2Bytes {

    /**
     * returns the value of the char
     * @param c
     * @return
     */
    private static int getBinValue(char c) {
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        } else if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10; // TODO
        } else if (c >= '0' && c <= '9') {
            return c - '0';
        } else {
            throw new IllegalArgumentException("Invalid Character: " + c);
        }
    }

    // Further hints:
    // to use unsigned Value use (byte & 0xff)
    // Java is using two complements see: https://en.wikipedia.org/wiki/Two%27s_complement
    //
    // E.g. 1111 1111 => -1 cause its -128 + 127
    public static void main(String[] args) {
        // 10AF => 0001 0000 1010 1111
        // 0001 => 1 decimal
        // 0000 => 0 decimal
        // 00010000 => 16 decimal

        // 1010 => 8 decimal
        // 1111 => 15 decimal
        // 10101111 => 175 decimal

        // In java all bytes are signed, means 7 bit left for values
        // Left most bit is the sign (0 is positive, 1 is negative)
        // => Values are between -128,127
        // First 24 bits are discarded
        // 10AF => 0001 0000 1010 1111
        //                   1010 1111 => -81 decimal as byte value

        String hex = "10AF"; // 0001000010101111
        byte[] bytes = new byte[hex.length() / 2];

        for (int i = 0; i < hex.length() - 2; i++) {
            // shift first digit cause its the first part of 8 bit
            int result = getBinValue(hex.charAt(i * 2)) << 4 | getBinValue(hex.charAt(i * 2 + 1));
            bytes[i] = (byte) result;
        }

        String s1 = String.format("%8s", Integer.toBinaryString(bytes[0] & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(bytes[1] & 0xFF)).replace(' ', '0');

        System.out.println("10AF with BigInt: " + new BigInteger("10AF", 16).toString(2));
        System.out.println("10AF in binary: " + s1+s2);

        int convertedValue = Integer.decode("0x"+hex);
        int digit = Character.digit('A', 16);
        int numericValue = Character.getNumericValue('A');

        byte bytes2[] = {(byte)16, (byte)-81};

        System.out.println("(byte)137 = " + (byte)137);      //prints: (byte)137 = -119
        System.out.println("(byte)128 = " + (byte)128);      //prints: (byte)128 = -128
        System.out.println("(byte)-129 = " + (byte)-129);    //prints: (byte)-129 = 127

        //dec: 260 => bin: 0001 0000 0100 => 8 bit: 0000 0100 => dec 4
        // Dangerous: Loosing precision: int is always 32 bit
        System.out.println("260 as binary: " + Integer.toBinaryString(260));
        System.out.println("260 as signed byte: " + (byte) 260);
        System.out.println("4 as signed byte: " + (byte) 4);
    }
}
