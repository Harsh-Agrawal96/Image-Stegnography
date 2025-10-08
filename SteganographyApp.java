// File: SteganographyApp.java (Updated for dynamic input)

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class SteganographyApp {


    /**
     * Handles the encoding process based on user input.
     */
    private static void handleEncode(Scanner scanner) {
        try {
            System.out.print("Enter the secret message to hide: ");
            String secretMessage = scanner.nextLine();

            System.out.print("Enter the input image file name (e.g., cover.png): ");
            String coverImageName = scanner.nextLine();
            File coverImageFile = new File(coverImageName);

            System.out.print("Enter the output image file name (e.g., stego_image.png): ");
            String stegoImageName = scanner.nextLine();
            File stegoImageFile = new File(stegoImageName);

            System.out.println("Loading cover image...");
            BufferedImage coverImage = ImageIO.read(coverImageFile);
            if (coverImage == null) {
                System.err.println("Error: Cover image not found or could not be read.");
                return;
            }

            System.out.println("Embedding the message...");
            BufferedImage stegoImage = Encoder.embed(coverImage, secretMessage);

            ImageIO.write(stegoImage, "PNG", stegoImageFile);
            System.out.println("✅ Success! Message embedded and saved to '" + stegoImageName + "'.");

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the decoding process based on user input.
     */
    private static void handleDecode(Scanner scanner) {
        try {
            System.out.print("Enter the image file to decode from (e.g., stego_image.png): ");
            String imageToDecodeName = scanner.nextLine();
            File imageToDecodeFile = new File(imageToDecodeName);

            System.out.println("Loading image for decoding...");
            BufferedImage imageToDecode = ImageIO.read(imageToDecodeFile);
            if (imageToDecode == null) {
                System.err.println("Error: Image to decode not found or could not be read.");
                return;
            }

            System.out.println("Decoding the message...");
            String decodedMessage = Decoder.decode(imageToDecode);

            System.out.println("Success! Decoded Message: \"" + decodedMessage + "\"");

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Steganography Menu ---");
            System.out.println("1. Encode a message into an image");
            System.out.println("2. Decode a message from an image");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleEncode(scanner);
                    break;
                case "2":
                    handleDecode(scanner);
                    break;
                case "0":
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
}