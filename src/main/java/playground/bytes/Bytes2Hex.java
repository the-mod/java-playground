package playground.bytes;

public class Bytes2Hex {

    public static String string2Hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes) {
            int decimal = b & 0xff;

            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {
                hex = "0" + hex;
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

    public static String long2Hex(long input) {
        return Long.toHexString(input);
    }

    public static String int2Hex(int input) {
        return Integer.toHexString(input);
    }

    public static void main(String[] args) {
        // a is decimal 97 which is 01100001 in binary
        // 0110 => 6
        // 0001 => 1
        String hex = string2Hex("a".getBytes());
        System.out.println("a in hex is: " + hex);

        // s is decimal 115 which is 1110011
        // 0111 => 7
        // 0011 => 3
        String hex2 = string2Hex("s".getBytes());
        System.out.println("s in hex is: " + hex2);

        // t is decimal 116, s is decimal 115
        String hex3 = string2Hex("ts".getBytes());
        System.out.println("ts in hex is: " + hex3);

        String hexLong = long2Hex(125L);
        System.out.println("125L in hex is: " + hexLong);

        String hexInt = int2Hex(125);
        System.out.println("125 in hex is: " + hexInt);
    }
}
