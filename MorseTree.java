/*
 *CS 2852 - 011
 *Fall 2017
 *Lab 7 - Morse Code Detector
 *Name: Donal Moloney
 *Created: 10/16/2017
 */
package Moloneyda;

/**
 * This is class creates a MorseTree allowing you to decode messages
 */
public class MorseTree<E> {

    /**
     * This is the declared node root object
     */
    private Node<E> root;

    /**
     * This is the morse tree constructor it sets the rout
     */
    public MorseTree() {
        root = new Node(null, null, null);
    }

    /**
     * This is a private inner class that is placed in charge of creating nodes
     */
    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * This method is in charge of adding symbols into the MorseTree depending on how its morse
     * code combination
     *
     * @param symbol - The character you wish to add to the tree
     * @param node   - Its more code combination
     */
    public void add(E symbol, String node) {
        Node current = root;
        for (int i = 0; i < node.length(); i++) {
            if (node.substring(i, i + 1).equals(".")) {
                if (current.left == null) {
                    current.left = new Node(null, null, null);
                }
                current = current.left;
            } else if (node.substring(i, i + 1).equals("-")) {
                if (current.right == null) {
                    current.right = new Node(null, null, null);
                }
                current = current.right;
            }
        }
        current.data = symbol;
    }

    /**
     * Decodes the passed in string
     *
     * @param code -  the character to be decoded
     * @return character represented by a morse code
     * @throws IllegalArgumentException - if the character is not a morse code character
     */
    public String decode(String code) throws IllegalArgumentException {
        Node current = root;
        Boolean exists = true;
        for (int i = 0; (i < code.length() && exists == true); i++) {
            String character = code.substring(i, i + 1);
            if (character.equals(".")) {
                if (current.left == null) {
                    exists = false;
                } else {
                    current = current.left;
                }
            } else if (character.equals("-")) {
                if (current.right == null) {
                    exists = false;
                } else {
                    current = current.right;
                }
            } else if (character.equals("|")) {
                return "\t";
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (exists == true) {
            return (String) current.data;
        } else {
            return null;
        }
    }

}
