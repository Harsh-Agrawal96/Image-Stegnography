import java.awt.image.BufferedImage;


public class Encoder {

    /**
     * The main public method to embed a message.
     * It returns a new image with the hidden data.
     */
    public static BufferedImage embed(BufferedImage image, String message) {
        int messageLength = message.length();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageSize = imageWidth * imageHeight;

        if (messageLength * 8 + 32 > imageSize) {
            throw new IllegalArgumentException("Message is too long for the chosen image.");
        }

        embedInteger(image, messageLength, 0);

        byte[] messageBytes = message.getBytes();
        for (int i = 0; i < messageBytes.length; i++) {
            embedByte(image, messageBytes[i], i * 8 + 32);
        }
        return image;
    }

    private static void embedInteger(BufferedImage img, int n, int start) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start % maxY;
        int count = 0;

        for (int i = startX; i < maxX && count < 32; i++) {
            for (int j = startY; j < maxY && count < 32; j++) {
                int rgb = img.getRGB(i, j);
                int bit = BitManager.getBitValue(n, count);
                rgb = BitManager.setBitValue(rgb, 0, bit);
                img.setRGB(i, j, rgb);
                count++;
            }
            startY = 0;
        }
    }

    private static void embedByte(BufferedImage img, byte b, int start) {
        int maxX = img.getWidth();
        int maxY = img.getHeight();
        int startX = start / maxY;
        int startY = start % maxY;
        int count = 0;

        for (int i = startX; i < maxX && count < 8; i++) {
            for (int j = startY; j < maxY && count < 8; j++) {
                int rgb = img.getRGB(i, j);
                int bit = BitManager.getBitValue(b, count);
                rgb = BitManager.setBitValue(rgb, 0, bit);
                img.setRGB(i, j, rgb);
                count++;
            }
            startY = 0;
        }
    }
}