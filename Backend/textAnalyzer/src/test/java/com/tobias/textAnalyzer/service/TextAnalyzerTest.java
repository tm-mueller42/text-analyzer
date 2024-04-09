package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class TextAnalyzerTest {

    private final TextAnalyzer analyzer = new TextAnalyzer();

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(new HashMap<String, Integer>(Map.of("A", 1)), "A"),
                Arguments.of(new HashMap<String, Integer>(Map.of("A", 1)), "a"),
                Arguments.of(new HashMap<String, Integer>(Map.of("A", 2)), "Aa"),
                Arguments.of(new HashMap<String, Integer>(Map.of("A",2, "B", 1)), "Aab")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void analyzeText(HashMap<String, Integer> expected, String text) {
        CharacterValidator mockValidator = Mockito.mock(CharacterValidator.class);
        Mockito.when(mockValidator.validate(anyString())).thenReturn(true);
        HashMap<String, Integer> result = analyzer.analyzeText(text, mockValidator);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void analyseEmptyOrInvalidText() {
        CharacterValidator mockValidator = Mockito.mock(CharacterValidator.class);
        Mockito.when(mockValidator.validate(anyString())).thenReturn(false);
        HashMap<String, Integer> expected = new HashMap<>(Map.of());
        HashMap<String, Integer> result = analyzer.analyzeText("", mockValidator);
        Assertions.assertEquals(expected, result);
    }
}