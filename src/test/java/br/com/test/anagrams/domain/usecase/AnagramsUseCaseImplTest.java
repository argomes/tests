package br.com.test.anagrams.domain.usecase;

import br.com.test.anagrams.presenter.client.AnagramsUsecase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnagramsUseCaseImplTest {

    private final AnagramsUsecase usecase = new AnagramsUseCaseImpl();

    @Test
    void shouldGenerateAllAnagramsForTwoCharacters() {
        String input = "ab";
        List<String> result = usecase.generateAnagram(input);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("ab"));
        assertTrue(result.contains("ba"));
    }

    @Test
    void shouldGenerateAllAnagramsForThreeCharacters() {
        String input = "abc";
        List<String> result = usecase.generateAnagram(input);

        assertNotNull(result);
        assertEquals(6, result.size());
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    void shouldThrowException_WhenInputIsNull() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usecase.generateAnagram(null)
        );

        assertEquals("The text cannot be empty or null.", exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenInputIsEmpty() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usecase.generateAnagram("")
        );

        assertEquals("The text cannot be empty or null.", exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenInputIsOneCharacter() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usecase.generateAnagram("a")
        );

        assertEquals("The text must contain more than one character.", exception.getMessage());
    }

    @Test
    void shouldHandleTextWithRepeatingCharacters() {
        String input = "aab";
        List<String> result = usecase.generateAnagram(input);

        assertNotNull(result);
        assertEquals(6, result.size()); // Note: Duplicates are allowed in result
        assertTrue(result.contains("aab"));
        assertTrue(result.contains("aba"));
        assertTrue(result.contains("baa"));
    }
}
