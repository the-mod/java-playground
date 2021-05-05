package playground.bytes;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteInt {

    public static void main(String[] args) {
        // correct conversion of int (32 bit) to (signed) bytes (8 bit)
        // dec: 1695609641 => hex: 6510F329
        // dec: 1695609641 => bin: 0110 0101 | 0001 0000 | 1111 0011 | 0010 1001
        //            byte values:    101    |    16     |    -13    |    41
        byte[] array = ByteBuffer.allocate(4).putInt(1695609641).array();
        for (int i = 0; i < array.length ; i++) {
            System.out.println(i +1 + ". byte: " + array[i]);
        }

        // converting it back to int
        int back = (array[0]<<24) & 0xff000000 |
                   (array[1]<<16) & 0x00ff0000 |
                   (array[2]<< 8) & 0x0000ff00 |
                   (array[3]<< 0) & 0x000000ff;

        System.out.println("Converting back with bit shifts: " + back);

        // taking the hex to create a new byte array
        // F3 is dec. 243 => out of range for byte type
        byte[] arr = { 0x65, 0x10, (byte) 0xF3, 0x29 };
        ByteBuffer buffer = ByteBuffer.wrap(arr); // big-endian by default
        int result = buffer.getInt();
        System.out.println("Converting back with byteBuffer: " + result);

        int number = new BigInteger(arr).intValue();
        System.out.println("Converting back with bigInt: " + number);
    }
}
