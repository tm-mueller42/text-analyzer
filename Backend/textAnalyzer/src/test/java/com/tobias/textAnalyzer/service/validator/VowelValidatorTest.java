package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterTypeDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_VOWEL;

class VowelValidatorTest {

    private final VowelValidator validator = new VowelValidator();
    private static final Field[] fields = CharacterTypeDefinition.class.getDeclaredFields();
    private static final String[] NOT_VOWELS = Stream.of(fields)
            .filter(field -> !field.getName().equals("DEFINITION_VOWEL"))
            .map(field -> {
                try {
                    return (String) field.get(CharacterTypeDefinition.class);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.joining()).split("");

    private static final String[] VOWELS = DEFINITION_VOWEL.split("");


    static String[] vowels() {
        return VOWELS;
    }
    static String[] not_vowels() {
        return NOT_VOWELS;
    }

    @ParameterizedTest
    @MethodSource("vowels")
    void validateVowels(String input) {
        boolean resultUpperCase = validator.validate(input);
        boolean resultLowerCase = validator.validate(input.toLowerCase());
        Assertions.assertTrue(resultUpperCase);
        Assertions.assertTrue(resultLowerCase);
    }

    @ParameterizedTest
    @MethodSource("not_vowels")
    void validateNotVowels(String input) {
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
        boolean result = validator.validate("aa");
        Assertions.assertFalse(result);
    }
}

