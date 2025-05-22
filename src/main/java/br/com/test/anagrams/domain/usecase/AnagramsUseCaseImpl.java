package br.com.test.anagrams.usecase;

import br.com.test.anagrams.presenter.client.AnagramsUsecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnagramsUseCaseImpl implements AnagramsUsecase {


    @Override
    public List<String> generateAnagram(final String text) {
        validationText(text);
        List<String> result = new ArrayList<>();
        generateAnagramsRecursive("", text, result);
        return result;
    }

    private void generateAnagramsRecursive(final String prefix, final String remaining, List<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            char current = remaining.charAt(i);
            String nextPrefix = prefix + current;
            String nextRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generateAnagramsRecursive(nextPrefix, nextRemaining, result);
        }
    }

    private static void validationText(final String text) {
        if (Objects.isNull(text) || text.isBlank()) {
            throw new RuntimeException("The text cannot be empty or null.");
        }

        if (text.length() <= 1) {
            throw new RuntimeException("The text must contain more than one character.");
        }
    }
}
