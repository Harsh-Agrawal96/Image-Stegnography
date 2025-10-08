import java.awt.image.BufferedImage;


public class Decoder {

    /**
     * The main public method to decode a message from an image.
     * It returns the hidden string.
     */
    public static String decode(BufferedImage image) {
        int len = extractInteger(image, 0);
        if (len <= 0 || len * 8 + 32 > image.getWidth() * image.getHeight()) {
            throw new IllegalArgumentException("No valid message found or message length is invalid.");
        }

        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[i] = extractByte(image, i * 8 + 32);
        }
        return new String(b);
    }

    private static int extractInteger(BufferedImage img, int start) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start % maxY;
        int count = 0;
        int length = 0;

        for (int i = startX; i < maxX && count < 32; i++) {
            for (int j = startY; j < maxY && count < 32; j++) {
                int rgb = img.getRGB(i, j);
                int bit = BitManager.getBitValue(rgb, 0);
                length = BitManager.setBitValue(length, count, bit);
                count++;
            }
            startY = 0;
        }
        return length;
    }

    private static byte extractByte(BufferedImage img, int start) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start % maxY;
        int count = 0;
        byte b = 0;

        for (int i = startX; i < maxX && count < 8; i++) {
            for (int j = startY; j < maxY && count < 8; j++) {
                int rgb = img.getRGB(i, j);
                int bit = BitManager.getBitValue(rgb, 0);
                b = (byte) BitManager.setBitValue(b, count, bit);
                count++;
            }
            startY = 0;
        }
        return b;
    }
}