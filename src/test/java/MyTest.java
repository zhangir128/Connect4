import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MyTest {

	@Test
	void testConstructor () {
		GameButton button = new GameButton();
		assertEquals(false, button.isClicked());
		assertEquals(false, button.checkMove());
	}

	@Test
	void testValidMove () {
		GameButton button = new GameButton();
		button.validMove();
		assertEquals(true, button.checkMove());
	}

	@Test
	void testSetClicked () {
		GameButton button = new GameButton();
		button.setClicked();
		assertEquals(true, button.isClicked());
	}

}