/*
 * Class: CMSC203 22502 
 * Instructor: Dr. Kuijt
 * Description: Tests methods in CryptoManager
 * Due: 10/20/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: Alex Kim
*/
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testStringInBounds() {
		assertTrue(CryptoManager.isStringInBounds("SKIBIDI"));
		assertTrue(CryptoManager.isStringInBounds("\"TEST SPACE\""));
		assertFalse(CryptoManager.isStringInBounds("skibidi"));
		assertFalse(CryptoManager.isStringInBounds("{SKIBIDI"));
		assertFalse(CryptoManager.isStringInBounds("\"{{{\""));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("The selected string is not in bounds, Try again.", CryptoManager.caesarEncryption("skibidi", 7));
		assertEquals("KLJ", CryptoManager.caesarEncryption("GHF", 4));
		assertEquals("GPSUOJUF", CryptoManager.caesarEncryption("FORTNITE", 1));
		assertEquals("0)447", CryptoManager.caesarEncryption("HALLO", 104));
		assertEquals(")!_X_Z_", CryptoManager.caesarEncryption("SKIBIDI", 86));
		assertEquals("ZRPIPKP'[VPSL[", CryptoManager.caesarEncryption("SKIBIDI TOILET", 7));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("GHF", CryptoManager.caesarDecryption("KLJ", 4));
		assertEquals("FORTNITE", CryptoManager.caesarDecryption("GPSUOJUF", 1));
		assertEquals("HALLO", CryptoManager.caesarDecryption("0)447", 104 ));
		assertEquals("SKIBIDI", CryptoManager.caesarDecryption(")!_X_Z_", 86));
		assertEquals("SKIBIDI TOILET", CryptoManager.caesarDecryption("ZRPIPKP'[VPSL[", 7));
	}

	@Test
	public void testEncryptBellaso() {
		assertEquals(" PXYVIX", CryptoManager.bellasoEncryption("SKIBIDI", "MEOW"));
		assertEquals("^Z)G!M!R", CryptoManager.bellasoEncryption("KEYBOARD", "SUPERLONGWORD"));
		assertEquals(" ^XJ ^XJ ^XJ", CryptoManager.bellasoEncryption("POLEPOLEPOLE", "POLE"));

	}

	@Test
	public void testDecryptBellaso() {
		assertEquals("SKIBIDI", CryptoManager.bellasoDecryption(" PXYVIX", "MEOW"));
		assertEquals("KEYBOARD", CryptoManager.bellasoDecryption("^Z)G!M!R", "SUPERLONGWORD"));
		assertEquals("POLEPOLEPOLE", CryptoManager.bellasoDecryption(" ^XJ ^XJ ^XJ", "POLE"));
	}

}
