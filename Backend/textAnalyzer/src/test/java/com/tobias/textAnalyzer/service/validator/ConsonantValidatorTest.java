package com.tobias.textAnalyzer.service.validator;

import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.*;

import com.tobias.textAnalyzer.data.CharacterTypeDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConsonantValidatorTest {

    private final ConsonantValidator validator = new ConsonantValidator();
    private static final Field[] fields = CharacterTypeDefinition.class.getDeclaredFields();
    private static final String[] NOT_CONSONANTS = Stream.of(fields)
            .filter(field -> !field.getName().equals("DEFINITION_CONSONANT"))
            .map(field -> {
                try {
                    return (String) field.get(CharacterTypeDefinition.class);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.joining()).split("");

    private static final String[] CONSONANTS = DEFINITION_CONSONANT.split("");


    static String[] consonants() {
        return CONSONANTS;
    }
    static String[] not_consonants() {
        return NOT_CONSONANTS;
    }

    @ParameterizedTest
    @MethodSource("consonants")
    void validateConsonants(String input) {
        boolean resultUpperCase = validator.validate(input);
        boolean resultLowerCase = validator.validate(input.toLowerCase());
        Assertions.assertTrue(resultUpperCase);
        Assertions.assertTrue(resultLowerCase);
    }

    @ParameterizedTest
    @MethodSource("not_consonants")
    void validateNotConsonants(String input) {
        boolean resultUpperCase = validator.validate(input);
        boolean resultLowerCase = validator.validate(input.toLowerCase());
        Assertions.assertFalse(resultUpperCase);
        Assertions.assertFalse(resultLowerCase);
    }

    @Test
    void validateEmpty() {
        boolean result = validator.validate("");
        Assertions.assertFalse(result);
    }

    @Test
    void validateStringWithMoreThanOneCharacter() {
        boolean result = validator.validate("bb");
        Assertions.assertFalse(result);
    }
}

