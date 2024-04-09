package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class TextAnalyzer {

    public HashMap<String, Integer> analyzeText(String text, CharacterValidator validator) {
        String[] textArray = text.split("");
        HashMap<String, Integer> characterFrequencies = new HashMap<>();
        for (String currentCharacter : textArray) {
            if (validator.validate(currentCharacter)) {
                characterFrequencies.merge(currentCharacter.toUpperCase(), 1, Integer::sum);
            }
        }
        return characterFrequencies;
    }
}
