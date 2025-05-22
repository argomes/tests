package br.com.test.anagrams.presenter;

import br.com.test.anagrams.presenter.client.AnagramsUsecase;

import java.util.List;

public class AnacramsController {

    private final AnagramsUsecase anagramsUsecase;

    private AnacramsController(final Builder builder) {
        this.anagramsUsecase = builder.anagramsUsecase;
    }

    public List<String> generateAnagram(final String text) {
        return anagramsUsecase.generateAnagram(text);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AnagramsUsecase anagramsUsecase;

        public Builder withAnagramsUseCase(AnagramsUsecase anagramsUsecase) {
            this.anagramsUsecase = anagramsUsecase;
            return this;
        }

        public AnacramsController build() {
            if (anagramsUsecase == null) {
                throw new IllegalStateException("AnagramsUsecase must be provided.");
            }
            return new AnacramsController(this);
        }
    }
}
