package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class GameTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testGetInput() {
        // Simuler une entrée utilisateur "42"
        ByteArrayInputStream inputStream = new ByteArrayInputStream("4\n".getBytes());
        System.setIn(inputStream);

        // Appeler la méthode getInput() de la classe Game
        String result = Game.getInput();

        // Vérifier si la méthode a renvoyé la valeur attendue
        assertEquals("42", result.trim());
    }

    @Test
    public void testPrintInputMessage() {
        // Appeler la méthode printInputMessage avec l'argument "42"
        Game.printInputMessage("4");

        // Vérifier si le message imprimé est celui attendu
        assertEquals("Enter your choice for section number 4: ", outputStream.toString());
    }

    @Test
    public void testPrint() {
        // Appeler la méthode print avec le message "Hello, world!"
        Game.print("Hello, world!");

        // Vérifier si le message imprimé est celui attendu
        assertEquals("Hello, world!\n", outputStream.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }
}
