package convert;

import com.google.common.primitives.Longs;

import java.nio.ByteBuffer;

public class LongByte {

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);

    public static byte[] longToBytes(long x){
        byteBuffer.putLong(0,x);
        return byteBuffer.array();
    }

    public static long bytesToLong(byte[] bytes){
        byteBuffer.put(bytes,0, bytes.length);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }


    public static int bytesToInt(byte[] bytes){
        return bytes[3]&0xFF |
                bytes[2]&0xFF<< 8 |
                bytes[1]&0xFF<<16 |
                bytes[0]&0xFF<<24;
    }

    public static byte[] intToBytes(int a){
        return new byte[]{
                (byte)((a>>24)&0xFF),
                (byte)((a>>16)&0xFF),
                (byte)((a>>8)&0xFF),
                (byte)(a&0xFF)
        };
    }

    public static void main(String[] args) {
        long longNum = 129l;
        System.out.println(longToBytes(longNum));
        System.out.println(bytesToLong(Longs.toByteArray(longNum)));

        System.out.println(intToBytes(129));
        System.out.println(bytesToInt(intToBytes(129)));
    }
}
