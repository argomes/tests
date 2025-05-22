package br.com.test.anagrams;

import br.com.test.anagrams.domain.usecase.AnagramsUseCaseImpl;
import br.com.test.anagrams.presenter.AnacramsController;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnacramsController anacramsController = AnacramsController.builder()
                .withAnagramsUseCase(new AnagramsUseCaseImpl())
                .build();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma palavra para gerar os anagramas: ");
        String input = scanner.nextLine().trim();

        List<String> anagrams = anacramsController.generateAnagram(input);

        anagrams.forEach(System.out::println);

    }
}