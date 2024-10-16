package srychkov; // Update to match your package structure
import org.example.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Test Klasse
 */
class WortTrainerTest {
    private WortTrainer trainer;
    private WortListe wortListe; // WortListe remains as a member variable


    private WortTrainer wortTrainer;

    @BeforeEach
    public void setUp() {
        // Initialize WortTrainer with a sample WortListe
        WortListe wortListe = new WortListe(new String[]{"Dog", "Cat"}, new String[]{
                "https://static.vecteezy.com/system/resources/thumbnails/005/857/332/small_2x/funny-portrait-of-cute-corgi-dog-outdoors-free-photo.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/800px-Cat03.jpg"
        });
        wortTrainer = new WortTrainer(wortListe);
    }

    @Test
    public void testWortHinzufuegen() {
        // Arrange
        String newWord = "Rabbit";
        String newUrl = "https://example.com/rabbit.jpg";

        // Act
        wortTrainer.wortHinzufuegen(newWord, newUrl);

        // Assert
        assertEquals( 3, wortTrainer.getWl().getLength());
        assertEquals(newWord, wortTrainer.getWl().getWort(2));
    }

    @Test
    public void testCheckWortRichtig() {
        // Arrange
        String correctWord = "Dog";

        // Act
        boolean result = wortTrainer.checkWort(correctWord);

        // Assert
        assertTrue(result);
        assertEquals(1, wortTrainer.getRichtig());
        assertEquals( 1, wortTrainer.getEingaben());
    }

    @Test
    public void testCheckWortFalsch() {
        // Arrange
        String wrongWord = "Fish";

        // Act
        boolean result = wortTrainer.checkWort(wrongWord);

        // Assert
        assertFalse(result);
        assertEquals(0, wortTrainer.getRichtig());
        assertEquals(1, wortTrainer.getEingaben());
    }

    @Test
    public void testRandomWort() {
        // Act
        String randomWord = wortTrainer.randomWort();

        // Assert
        assertNotNull( randomWord);
        assertTrue(randomWord.equals("Dog") || randomWord.equals("Cat"));
    }

}
