/*
 *CS 2852 - 011
 *Fall 2017
 *Lab 7 - Morse Code Detector
 *Name: Donal Moloney
 *Created: 10/16/2017
 */
package Moloneyda;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the driver for the program its in charge of reading files, decoding morse code
 * in the files and then saving the decoded files
 */
public class MorseDecoder {
    /**
     * This is an tree object
     */
    private MorseTree tree;

    /**
     * This is the class constructor its purpose is initialize the tree and load it with values so
     * it can decode messages
     *
     * @param masterFile the file values and the values equivalent in morse code
     */
    public MorseDecoder(File masterFile) {
        tree = new MorseTree();
        loadDecoder(masterFile);
    }

    /**
     * This is the main method it what calls all the other methods
     *
     * @param args - This means that the main function expects an array of Strings
     */
    public static void main(String[] args) {
        try {
            File masterFile = new File("src/masterFile.txt");
            MorseDecoder decoder = new MorseDecoder(masterFile);
            Scanner input = new Scanner(System.in);
            System.out.println("Enter an input file; exclude .txt extension");
            File inputFile = checkValid(input.nextLine());
            System.out.println("Enter output filename; exclude .txt extension");
            File outputFile = createFileToWrite(input.nextLine());
            ArrayList<String> outPutFileContents = decoder.readFile(inputFile);
            decoder.saveDecodedMessage(outputFile, outPutFileContents);
        } catch (IOException e) {
            System.err.print("Either the file you entered does not exist, or their is an error " +
                                     "writing to the file\n");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "The code encountered is not " +
                    "in the morse code tree");
        }
    }

    /**
     * This methods creates a file from passed in user input and checks if this file already exists
     *
     * @param fileInput - a string which will represent the name of the file
     * @return inputFile = the valid file
     * @throws IOException - if the file does not exist
     */
    public static File checkValid(String fileInput) throws IOException {
        String filePath = "src/" + fileInput + ".txt";
        File inputFile = new File(filePath);
        if (inputFile.exists()) {
            return inputFile;
        } else {
            throw new IOException();
        }
    }

    /**
     * This method creates the file to be written to from user input
     *
     * @param fileInput - a string representing the name of the file
     * @return the file to be written to
     */
    public static File createFileToWrite(String fileInput) {
        String filePath = "src/" + fileInput + ".txt";
        File outPut = new File(filePath);
        return outPut;
    }

    /**
     * This file reads the contents of the file and returns it in an array list
     *
     * @param inputFile the file to be read from
     * @return the contents of the file in an array list
     */
    public ArrayList<String> readFile(File inputFile) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader bufferedReader;
        FileReader fileReader;
        String currentLine;
        String[] characters;
        String decodedCharacter = "";
        try {
            fileReader = new FileReader(inputFile);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                characters = currentLine.split(" ");
                for (int i = 0; i < characters.length; i++) {
                    try {
                        decodedCharacter = tree.decode(characters[i]);
                        if (decodedCharacter != null) {
                            lines.add(decodedCharacter);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.print("Warning: skipping" + decodedCharacter);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Warning: the file you are trying to write to could not be found");
        } catch (IOException e) {
            System.err.print("An error occurred reading from the buffered reader");
        }
        return lines;
    }

    /**
     * This message writes the decoded message to a file
     *
     * @param outputFile         the file to be written to
     * @param outPutFileContents the decoded message
     */
    public void saveDecodedMessage(File outputFile, ArrayList<String> outPutFileContents) {
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (int i = 0; i < outPutFileContents.size(); i++) {
                writer.write(outPutFileContents.get(i));
            }
            writer.close();
        } catch (IOException e) {
            System.err.print("An error occurred writing to the file");
        }
    }

    /**
     * This method populates the tree with contents that will allow it to decode messages
     *
     * @param masterFile the file with characters and their morse code equivalent
     */
    private void loadDecoder(File masterFile) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader bufferedReader;
            FileReader fileReader;
            fileReader = new FileReader(masterFile);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] parts = currentLine.split("\t");
                Object symbol = parts[0];
                String node = parts[1];
                tree.add(symbol, node);
            }
        } catch (FileNotFoundException e) {
            System.err.print("The fileReader could not find the file to read from");
        } catch (IOException e) {
            System.err.print(
                    "An error regarding the input and output of the buffered reader " + "occurred");
        }
    }

    /**
     * This method returns the morese tree object when it is called its purpose is to allow the
     * tree to be tested
     *
     * @return populated morse tree object
     */
    public MorseTree getMorseTree() {
        return this.tree;
    }
}
