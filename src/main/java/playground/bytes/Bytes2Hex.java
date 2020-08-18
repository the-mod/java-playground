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
        String hex = string2Hex("a".getBytes());
        System.out.println(hex);

        String hexLong = long2Hex(125L);
        System.out.println(hexLong);

        String hexInt = int2Hex(125);
        System.out.println(hexInt);
    }
}
