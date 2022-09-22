public class SetupUtils {
    private final int [] key = {0xA56BABCD, 0x00000000, 0xFFFFFFFF, 0xABCDEF01}; //128 bit sample key
    private int [] plaintext = {0x01234567, 0x89ABCDEF}; // 64 bit sample plaintext block

    public int[] getKey() {
        return key;
    }

    public int[] getPlaintext() {
        return plaintext;
    }
}
