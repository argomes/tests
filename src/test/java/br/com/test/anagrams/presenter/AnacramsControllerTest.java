package br.com.test.anagrams.presenter;

import br.com.test.anagrams.presenter.client.AnagramsUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnacramsControllerTest {

    private AnagramsUsecase anagramsUsecase;
    private AnacramsController controller;

    @BeforeEach
    void setUp() {
        anagramsUsecase = mock(AnagramsUsecase.class);
        controller = AnacramsController.builder()
                .withAnagramsUseCase(anagramsUsecase)
                .build();
    }

    @Test
    void shouldGenerateAnagrams_WhenValidTextProvided() {
        // given
        String input = "abc";
        List<String> expectedAnagrams = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");

        when(anagramsUsecase.generateAnagram(input)).thenReturn(expectedAnagrams);

        // when
        List<String> result = controller.generateAnagram(input);

        // then
        assertNotNull(result);
        assertEquals(expectedAnagrams.size(), result.size());
        assertEquals(expectedAnagrams, result);
        verify(anagramsUsecase, times(1)).generateAnagram(input);
    }

    @Test
    void shouldThrowException_WhenUsecaseNotProvidedToBuilder() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> AnacramsController.builder().build()
        );

        assertEquals("AnagramsUsecase must be provided.", exception.getMessage());
    }
}
