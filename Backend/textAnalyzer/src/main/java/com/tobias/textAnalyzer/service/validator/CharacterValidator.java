package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterType;

public interface CharacterValidator {
    CharacterType getCaracterType();
    boolean validate(String currentChar);
}
