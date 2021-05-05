package playground.bytes;

public class SignExtension {

    // Example how a conversion from byte to int could go wrong
    // taken from: https://stackoverflow.com/questions/28097873/java-4-bytes-to-32-bit-integer
    public static void main(String[] args) {
        // signed byte to int
        int i = 255;    // 1111 1111

                        // v  sign bit
        byte b = (byte) i;    // 1111 1111 = -1
        System.out.println("int " + i + " is byte " + b);
        // sign extension, which leads to wrong value here
        int wrong = (int) b;// 11111111111111111111111111111111 = -1
                            // \______________________/
                            //      sign extension

        System.out.println("Wrong Value: " + wrong);

        int right = (int) (0xFF & b);  // 00000000000000000000000011111111 = 255
        System.out.println("Right Value: " + right);
    }
}
