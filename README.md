# ☕ Java LSB Steganography Tool

A simple, command-line steganography tool written in Java. This application allows you to hide a secret text message within a PNG image using the **Least Significant Bit (LSB)** technique.

---


## 🧐 What It Does

This tool provides a basic implementation of steganography, which is the practice of concealing a message within another, non-secret file or message. In this case, we hide text inside an image file. The resulting image (the stego-image) looks identical to the original image to the naked eye.

### ✨ Features
* **Encode:** Hide any text message within a PNG image.
* **Decode:** Extract the hidden text message from a stego-image.
* **Interactive CLI:** A simple and easy-to-use menu-driven interface.

---


## 🛠️ How It Works

The program uses the **LSB (Least Significant Bit)** method. It works by altering the last bit of each pixel's color data to store bits of the secret message.

1.  **Message Length:** The tool first converts the length of the secret message into a 32-bit binary number and embeds these 32 bits into the first 32 pixels of the image. This tells the decoder how many characters to read.
2.  **Message Data:** It then converts each character of the secret message into its 8-bit binary representation and embeds these bits sequentially into the subsequent pixels of the image.

Because only the least significant bit of each pixel is changed, the visual difference in the resulting stego-image is virtually undetectable.

---


## 📂 Project Structure

The code is organized into four main files for clarity and modularity:

* `SteganographyApp.java`: The main entry point of the application. It contains the user menu and handles all user interaction.
* `Encoder.java`: Contains all the logic for embedding the message length and the message itself into the image.
* `Decoder.java`: Contains all the logic for extracting the message from a stego-image.
* `BitManager.java`: A utility class with helper functions for low-level bit manipulation, used by both the Encoder and Decoder.

---


## ✅ Requirements

* **Java Development Kit (JDK)** version 8 or higher.

---


## 🚀 How to Use

Follow these steps to compile and run the application.

### Step 1: Prepare Your Files
Place all four `.java` files (`SteganographyApp.java`, `Encoder.java`, `Decoder.java`, `BitManager.java`) in a single directory. You will also need to place your **cover image** in this same directory for the program to find it.

**Important:** The image must be in a lossless format like **PNG**. Using a lossy format like JPEG will corrupt the hidden data.

### Step 2: Compile the Code
Open a terminal or command prompt in the project directory and compile all the Java files using the following command:
```sh
javac *.java
```

### Step 3: Run the Application
Run the main application file with this command:
```sh
java SteganographyApp
```

### Step 4: Follow the On-Screen Prompts
The application will launch an interactive menu. When you enter the filenames, the program will look for the cover image in the current directory, and the new stego-image will be generated and saved in that same directory.

---
