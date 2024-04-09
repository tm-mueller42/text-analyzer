package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterType;
import org.springframework.stereotype.Service;
import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.*;

@Service
public class VowelValidator implements CharacterValidator {

    public CharacterType characterType = CharacterType.VOWEL;

    @Override
    public CharacterType getCaracterType() {
        return characterType;
    }

    @Override
    public boolean validate(String currentChar) {
        return currentChar.length() == 1 && DEFINITION_VOWEL.contains(currentChar.toUpperCase());
    }
}