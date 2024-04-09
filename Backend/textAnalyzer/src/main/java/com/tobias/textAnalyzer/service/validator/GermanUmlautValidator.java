package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterType;
import org.springframework.stereotype.Service;

import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_CONSONANT;
import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_GERMAN_UMLAUT;

@Service
public class GermanUmlautValidator implements CharacterValidator {

    public CharacterType characterType = CharacterType.GERMAN_UMLAUT;

    @Override
    public CharacterType getCaracterType() {
        return characterType;
    }

    @Override
    public boolean validate(String currentChar) {
        return currentChar.length() == 1 && DEFINITION_GERMAN_UMLAUT.contains(currentChar.toUpperCase());
    }
}
