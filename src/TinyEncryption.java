import java.math.BigInteger;

public class TinyEncryption {
    public static final int delta = 0x9E3779B9; // "magic constant"

    // test with different keys in SetupUtils
    public static void main (String [] args) {
        SetupUtils s = new SetupUtils();

        int [] key = s.getKey();
        int [] p = s.getPlaintext();

        encrypt(key, p);
    }

    public static void encrypt(int [] key, int [] p) {
        int k0 = key[0];
        int k1 = key[1];
        int k2 = key[2];
        int k3 = key[3];

        int l = p[0];
        int r = p[1];

        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum += delta;
            l += ((r << 4) + k0) ^ (r + sum) ^ ((r >> 5) + k1);
            r += ((l << 4) + k2) ^ (l + sum) ^ ((l >> 5) + k3);
        }
        p[0] = l;
        p[1] = r;
        System.out.println("Ciphertext: 0x" + Integer.toHexString(p[0]).toUpperCase() + Integer.toHexString(p[1]).toUpperCase());
        decrypt(key, p);
    }

    public static void decrypt(int [] key, int [] c) {
        int k0 = key[0];
        int k1 = key[1];
        int k2 = key[2];
        int k3 = key[3];

        int l = c[0];
        int r = c[1];
        int tempL = l;
        int tempR = r;

        int sum = delta << 5;

        for (int i = 0; i < 32; i++) {
            r -= ((l << 4) + k2) ^ (l + sum) ^ ((l >> 5) + k3);
            l -= ((r << 4) + k0) ^ (r + sum) ^ ((r >> 5) + k1);
            sum -= delta;
        }

        c[0] = l;
        c[1] = r;
        System.out.print("Plaintext: " + Integer.toHexString(c[0]).toUpperCase() + Integer.toHexString(c[1]).toUpperCase());
        System.out.println(" | (Original ciphertext): 0x" + Integer.toHexString(tempL).toUpperCase() +  Integer.toHexString(tempR).toUpperCase());
    }
}
