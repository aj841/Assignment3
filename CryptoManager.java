/*
 * Class: CMSC203 22502 
 * Instructor: Dr. Kuijt
 * Description: Encrypts and decrypts using caesar and bellaso
 * Due: 10/20/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: Alex Kim
*/

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {

	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
	    
		if (plainText == null) {
	            return false;
	        }
		
		boolean isInBounds = true;
		int numOfChars = plainText.length();
		for (int i = 0; i < numOfChars; i++) {
			
			if (plainText.charAt(i) > UPPER_RANGE || plainText.charAt(i) < LOWER_RANGE) {
				isInBounds = false;
			}
		}
		return isInBounds;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		
		//Error checking
		if (!isStringInBounds(plainText)) {
			return "The selected string is not in bounds, Try again.";
		}
		
		int numOfChars = plainText.length();
		char charEncrypted;
		StringBuilder cEncrypted = new StringBuilder(); 
		key = key % RANGE; //Ensure int key is in range

		//Convert each character to encrypted and append to new string
		for (int i = 0; i < numOfChars; i++) {
			//add value of key to each char and use modulus to to make updated char in range.
			//subtracted lower range to make lower bound 0 to make modulus easier
			charEncrypted = (char) ((plainText.charAt(i) - LOWER_RANGE + key) % RANGE + LOWER_RANGE);
			cEncrypted.append(charEncrypted);
		}
		
		return cEncrypted.toString();
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		
		//Error checking
		if (!isStringInBounds(plainText) || !isStringInBounds(bellasoStr)) {
			return "The selected string is not in bounds, Try again.";
		}
		
		int numOfPlainChars = plainText.length();
		int numOfKeyChars = bellasoStr.length();
		char charEncrypted,
			keyChar;
		
		//Repeat bellasoStr to extend to same length to plainText
		String repeatKey = bellasoStr.repeat(numOfPlainChars/numOfKeyChars + 1).substring(0, numOfPlainChars);
		StringBuilder bEncrypted = new StringBuilder();
		
		for (int i = 0; i < numOfPlainChars; i++) {
			keyChar = repeatKey.charAt(i);
			//add value of bellasoStr key to each char in plainText
			//subtract lower range (make modulus easier since it is zero), modulus by range, add lower range back
			charEncrypted = (char) (((plainText.charAt(i) + keyChar) - LOWER_RANGE) % RANGE + LOWER_RANGE);
	        bEncrypted.append(charEncrypted);
		}
		return bEncrypted.toString();
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		
		//Error checking
		if (!isStringInBounds(encryptedText)) {
			return "The selected string is not in bounds, Try again.";
		}
		
		int numOfChars = encryptedText.length();
		char charDecrypted;
		StringBuilder cDecrypted = new StringBuilder(); 
		key = key % RANGE; //Ensure int key is in range
		
		//Convert each character to decrypted and append to new string
		for (int i = 0; i < numOfChars; i++) {
			//subtract value of key to each char and use modulus to to make updated char in range
			//subtracted lower range to make lower bound 0 to make modulus easier. added range to make result positive
			charDecrypted = (char) ((encryptedText.charAt(i) - LOWER_RANGE - key + RANGE) % RANGE + LOWER_RANGE);
			cDecrypted.append(charDecrypted);
		}
		
		return cDecrypted.toString();
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
				
		//Error checking
		if (!isStringInBounds(encryptedText) || !isStringInBounds(bellasoStr)) {
			return "The selected string is not in bounds, Try again.";
		}
		
		int numOfEncChars = encryptedText.length();
		int numOfKeyChars = bellasoStr.length();
		char charDecrypted,
			keyChar;
		
		//Repeat bellasoStr to extend to same length to plainText
		String repeatKey = bellasoStr.repeat(numOfEncChars/numOfKeyChars + 1).substring(0, numOfEncChars);
		StringBuilder bDecrypted = new StringBuilder();
		
		for (int i = 0; i < numOfEncChars; i++) {
			keyChar = repeatKey.charAt(i);
			//subtract value of bellasoStr key to each char in plainText
			//add range (make modulus easier since it is zero), modulus by range, add range back
			charDecrypted = (char) (((encryptedText.charAt(i) - keyChar) + RANGE) % RANGE + RANGE);
			//handle when it is 96 ASCII
			if (charDecrypted == '`') {
				charDecrypted = (char)(charDecrypted - RANGE);
			}
	        bDecrypted.append(charDecrypted);
		}
		return bDecrypted.toString();
	}
}
