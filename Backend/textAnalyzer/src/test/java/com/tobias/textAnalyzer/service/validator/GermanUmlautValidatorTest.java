package com.tobias.textAnalyzer.service.validator;

import com.tobias.textAnalyzer.data.CharacterTypeDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tobias.textAnalyzer.data.CharacterTypeDefinition.DEFINITION_GERMAN_UMLAUT;

class GermanUmlautValidatorTest {

    private final GermanUmlautValidator validator = new GermanUmlautValidator();
    private static final Field[] fields = CharacterTypeDefinition.class.getDeclaredFields();
    private static final String[] NOT_GERMAN_UMLAUTE = Stream.of(fields)
            .filter(field -> !field.getName().equals("DEFINITION_GERMAN_UMLAUT"))
            .map(field -> {
                try {
                    return (String) field.get(CharacterTypeDefinition.class);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.joining()).split("");

    private static final String[] GERMAN_UMLAUTE = DEFINITION_GERMAN_UMLAUT.split("");


    static String[] german_umlaute() {
        return GERMAN_UMLAUTE;
    }
    static String[] not_german_umlaute() {
        return NOT_GERMAN_UMLAUTE;
    }

    @ParameterizedTest
    @MethodSource("german_umlaute")
    void validateGermanUmlaute(String input) {
        boolean resultUpperCase = validator.validate(input);
        boolean resultLowerCase = validator.validate(input.toLowerCase());
        Assertions.assertTrue(resultUpperCase);
        Assertions.assertTrue(resultLowerCase);
    }

    @ParameterizedTest
    @MethodSource("not_german_umlaute")
    void validateNotGermanUmlaute(String input) {
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

