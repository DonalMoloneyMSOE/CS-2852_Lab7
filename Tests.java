/*
 *CS 2852 - 011
 *Fall 2017
 *Lab 7 - Morse Code Detector
 *Name: Donal Moloney
 *Created: 10/16/2017
 */
package Moloneyda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Moloneyda.MorseDecoder.checkValid;

/**
 * This class runs tests on a the testable components of the program
 */
public class Tests {
    File testFileToWrite;
    MorseDecoder decoder;
    File masterFile;
    MorseTree tree;
    File corruptedFile;
    String falseFile;
    String correctFilePath;
    File correctFile;
    File validFile;
    String nullValueDots = "................";
    String nullValueSlash = "------------";

    /**
     * This class manipulates objects inside of it before every test is run
     */
    @BeforeEach
    void testSetUp() {
        corruptedFile = new File("");
        testFileToWrite = new File("src/testFileToWrite.txt");
        validFile = new File("src/small.txt");
        correctFilePath = "small";
        correctFile = new File(correctFilePath);
        falseFile = "src/falseFile.txt";
        masterFile = new File("src/masterFile.txt");
        decoder = new MorseDecoder(masterFile);
        tree = decoder.getMorseTree();
    }

    /**
     * This class manipulates objects inside of it before every test is run
     */
    @AfterEach
    void testTearDown() {
        corruptedFile = null;
        testFileToWrite = null;
        validFile = null;
        correctFilePath = null;
        correctFile = null;
        falseFile = null;
        masterFile = null;
        decoder = null;
        tree = null;
    }

    /**
     * This test checks to see if the file is valid and if invalid files throw an IOexception
     *
     * @throws IOException - for files that are not valid
     */
    @Test
    void testCheckValidFile() throws IOException {
        Assertions.assertThrows(IOException.class, () -> checkValid(falseFile));
        File file = decoder.checkValid(correctFilePath);
        String testFilePath = file.getAbsolutePath();
        String trueFilePath = validFile.getAbsolutePath();
        Assertions.assertTrue(testFilePath.equals(trueFilePath));
    }

    /**
     * This method tests createFileToWrite() by creating a file asserting that it does not exist
     * as it should not since it is "created" once its passed into file reader and that its file
     * path is what it should be
     */
    @Test
    void testCreateFileToWrite() {
        String testFilePath = "testPath";
        String expectedPath = "C:\\Users\\moloneyda\\CS2852Lab7\\src\\" + testFilePath + ".txt";
        File test = decoder.createFileToWrite(testFilePath).getAbsoluteFile();
        Assertions.assertFalse(test.exists());
        Assertions.assertTrue(expectedPath.equals(test.getPath()));
    }

    /**
     * This test ensures that elements are able to be added to the morse tree and then be used to
     * decode characters
     */
    @Test
    void testAdd() {
        String tilda = "ñ";
        String tildaPath = "--.--";
        tree.add(tilda, tildaPath);
        String test = tree.decode(tildaPath);
        Assertions.assertTrue(test.equals(tilda));

        String accentedLetter = "À";
        String accentedLetterPath = ".--.-";
        tree.add(accentedLetter, accentedLetterPath);
        String accentedLetterTestPath = tree.decode(accentedLetterPath);
        Assertions.assertTrue(accentedLetterTestPath.equals(accentedLetter));
    }

    /**
     * This test ensures that the morse tree is able to decode encoded characters correctly
     */
    @Test
    void testDecode() {
        String twoChar = "..- | .....";
        String decodedTwoChar = "";
        String[] twoCharString = twoChar.split(" ");
        for (String character : twoCharString) {
            decodedTwoChar += tree.decode(character);
        }
        Assertions.assertTrue(decodedTwoChar.equals("U\t5"));
        String invalidCharacter = "*";
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> tree.decode(invalidCharacter));
        Assertions.assertTrue(tree.decode(nullValueDots) == null);
        Assertions.assertTrue(tree.decode(nullValueSlash) == null);
    }

    /**
     * This method ensures that encoded files can be read by the morseDecoder
     *
     * @throws IOException
     */
    @Test
    void testReadFile() throws IOException {
        String checkValid = "";
        File dad = new File("src/dad.txt");
        ArrayList<String> message = decoder.readFile(dad);
        for (int i = 0; i < message.size(); i++) {
            checkValid += message.get(i);
        }
        Assertions.assertTrue(checkValid.equals("D\tA\tD"));
    }

    /**
     * This tests that the morse decoder can save text to a file
     *
     * @throws IOException
     */
    @Test
    void testSaveDecodedMessage() throws IOException {
        ArrayList<String> decodedCollection = new ArrayList<>();
        decodedCollection.add("B");
        decodedCollection.add("\t");
        decodedCollection.add("O");
        decodedCollection.add("\t");
        decodedCollection.add("B");
        decoder.saveDecodedMessage(testFileToWrite, decodedCollection);
        Assertions.assertTrue(testFileToWrite.exists());
        String messageInFile = "";
        Scanner in = new Scanner(new FileReader(testFileToWrite));
        while (in.hasNext()) {
            messageInFile += in.nextLine();
        }
        Assertions.assertTrue(messageInFile.equals("B\tO\tB"));
    }
}
