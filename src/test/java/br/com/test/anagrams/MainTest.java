package br.com.test.anagrams;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void shouldGenerateAndPrintAnagramsForValidInput() throws Exception {
        // Simula entrada do usuário
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Captura saída do console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Executa o main
        Main.main(new String[]{});

        // Restaura a saída original
        System.setOut(originalOut);

        String output = outContent.toString();

        // Verifica se contém anagramas esperados
        assertTrue(output.contains("abc"));
        assertTrue(output.contains("acb"));
        assertTrue(output.contains("bac"));
        assertTrue(output.contains("bca"));
        assertTrue(output.contains("cab"));
        assertTrue(output.contains("cba"));
    }

    @Test
    void shouldHandleInvalidInputTooShort() {
        InputStream in = new ByteArrayInputStream("a\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Exception exception = assertThrows(RuntimeException.class, () -> Main.main(new String[]{}));
        assertEquals("The text must contain more than one character.", exception.getMessage());

        System.setOut(originalOut);
    }

    @Test
    void shouldHandleEmptyInput() {
        InputStream in = new ByteArrayInputStream("\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Exception exception = assertThrows(RuntimeException.class, () -> Main.main(new String[]{}));
        assertEquals("The text cannot be empty or null.", exception.getMessage());
    }
}
