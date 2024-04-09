package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterType;
import org.springframework.stereotype.Service;

import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_GERMAN_UMLAUT;
import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_PUNCTUATION;

@Service
public class PunctuationValidator implements CharacterValidator {

    public CharacterType characterType = CharacterType.PUNCTUATION;

    @Override
    public CharacterType getCaracterType() {
        return characterType;
    }

    @Override
    public boolean validate(String currentChar) {
        return currentChar.length() == 1 && DEFINITION_PUNCTUATION.contains(currentChar.toUpperCase());
    }
}
