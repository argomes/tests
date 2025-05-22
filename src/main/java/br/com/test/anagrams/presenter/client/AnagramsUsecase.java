package br.com.test.anagrams.presenter.client;

import java.util.List;

public interface AnagramsUsecase {

    List<String> generateAnagram(String text);
}
