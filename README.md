# CS-2852_Lab7

Overview

Morse Code is a method for communicating using two symbols: dots and dashes. Each character has a code, consisting of zero or more dots and zero or more dashes. The following table describes the mapping for many popular characters:
Symbol 	Code 	Symbol 	Code 	Symbol 	Code 	Symbol 	Code
A 	.- 	L 	.-.. 	W 	.-- 	7 	--...
B 	-... 	M 	-- 	X 	-..- 	8 	---..
C 	-.-. 	N 	-. 	Y 	-.-- 	9 	----.
D 	-.. 	O 	--- 	Z 	--.. 	. 	.-.-.-
E 	. 	P 	.--. 	0 	----- 	, 	--..--
F 	..-. 	Q 	--.- 	1 	.---- 	/ 	-..-.
G 	--. 	R 	.-. 	2 	..--- 	? 	..--..
H 	.... 	S 	... 	3 	...-- 	SPACE 	.-...
I 	.. 	T 	- 	4 	....- 	NEW LINE 	.-.-
J 	.--- 	U 	..- 	5 	..... 		
K 	-.- 	V 	...- 	6 	-.... 	

In this assignment, you will create a Morse Code decoder. You will write and test two classes: MorseDecoder and MorseTree<E>.

In order to decode each series of dots and dashes, you must build a binary tree. A partially constructed tree is shown below. The tree contains the letters A to E in the alphabet.
Morse Code Tree
Figure 1: Morse Code Tree

Notice that the resulting binary tree provides a decoding path. For example, the letter D can be decoded from its Morse Code: -... To accomplish this, we start at the root of the tree and traverse to the left or right depending on whether the code begins with a dot or a dash respectively. In this case, -.. begins with a dash followed by two dots, so we would traverse from the root of the tree once to the right and then twice to the left. Doing so causes us to arrive at a node in the tree with the value D.

In fact, once the tree is fully populated, all symbols in it can be decoded by visiting the left child when a dot is encountered and visiting the right child when a dash is encountered. When all of the dots/dashes in the code have been processed, the resulting node in the tree contains the symbol represented by the code.
Procedure

Your program must read in a file consisting of characters that have been encoded in Morse Code. In the encoded file, each character is translated into its morse code. Each code is separated by a space. For example, HI THERE would appear in an encoded file as: .... .. .-... - .... . .-. .. Your MorseDecoder class must read and encoded file and write the decoded result to an output file. Your program must make use of a MorseTree<E> class to store the Morse Code in a binary tree.

The MorseTree<E> class must use generics to denote the type of data stored in the tree.

The MorseTree<E> class must have the following public methods:

    MorseTree() — constructor that ensures that the content in the root node is set to null.
    add(E symbol, String code) — where symbol is the letter/digit/punctuation mark and code is the Morse Code associated with the symbol and inserts it into the tree. Keep in mind that this may involve adding multiple nodes to the tree. For example, if A is added to the tree first, the following nodes are added to the tree: An empty node (that is, a node whose content is set to null) as the root node as well as an empty node that is the root node’s left child. That node must have a right child that is a node whose content is set to A.
    decode(String code) — where code is the code for a symbol that is returned by the method. If a code is not found, the method should return null. This method should throw an IllegalArgumentException if the String passed to the method contains anything other than . or -. This method must make a call to a private recursive method that does most of the work. Note: This method will function correctly only if the tree has been correctly populated in advance.

Your program should be implemented in the MorseDecoder class. The design for this class is your responsibility; however, you are required to have one method called loadDecoder(Path path) that accepts a Path object containing the Morse Code file as an argument. The method calls the add() from the MorseTree<E> class multiple times in order to populate the tree. Each line of the file contains one codeword mapping. The first character is the symbol (e.g., D) immediately followed by the corresponding morse codeword (e.g., -..). Note: The newline character is a special case in that it requires two characters to represent the symbol (\n). When reading the the file, if the line begins with a backslash character, the symbol takes two characters and the codeword begins at the third character on the line. You will need to handle this as a special case in your code.

Notes:

    Your program should not decode symbols that are not found in the tree. A warning message should be displayed to the console for any code encountered that is not in the morse code tree.
    Line breaks in the following file are included for clarity but should be treated as whitespace. Newlines should be added to the output file only when the .-.- codeword is encountered.

Sample Results

Running your program on this file:

    .- .-... ... .--. .- -.-. . .-... ... .... --- ..- .-.. -.. .-... -... . .-... .--. .-.. .- -.-. . -.. .-... -... . - .-- . . -. .-... . .- -.-. .... .-... . -. -.-. --- -.. . -.. .-... -.-. .... .- .-. .- -.-. - . .-. .-.-.- .-.-
    .-.-
    .- .-... * .-... ... .... --- ..- .-.. -.. .-... -... . .-... .--. .-.. .- -.-. . -.. .-... -... . - .-- . . -. .-... . .- -.-. .... .-... .-- --- .-. -.. .-.-.- .-.-
    .-.-
    .-.. .. -. . .-... -... .-. . .- -.- ... .-... .. -. .-... - .... . .-... .. -. .--. ..- - .-... ..-. .. .-.. . .-... ... .... --- ..- .-.. -.. .-... -... . .-... .-. . .--. .-.. .. -.-. .- - . -.. .-... .. -. .-... - .... . .-... . -. -.-. --- -.. . -.. .-... ..-. .. .-.. . .-.-.-  .-.-

should display something like this to the console:

    Enter an input filename:
    encoded.txt
    Enter an output filename
    decoded.txt
    Warning: skipping: *

and produce the following output file:

    A SPACE SHOULD BE PLACED BETWEEN EACH ENCODED CHARACTER.
     
    A  SHOULD BE PLACED BETWEEN EACH WORD.
     
    LINE BREAKS IN THE INPUT FILE SHOULD BE REPLICATED IN THE ENCODED FILE.

Lab Deliverables

    See your professor’s instructions for details on submission guidelines and due dates.

        Dr. Taylor’s students: See below
        All other students should refer to Blackboard

    If you have any questions, consult your instructor.

Acknowledgements

This assignment was originally developed by Dr. Jay Urbain.
