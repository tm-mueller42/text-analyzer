package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.data.ResultDTO;
import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;

class TextAnalyzerTest {

    private final TextAnalyzer analyzer = new TextAnalyzer();

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(new ResultDTO[] {new ResultDTO("A", 1)}, "A"),
                Arguments.of(new ResultDTO[] {new ResultDTO("A", 1)}, "a"),
                Arguments.of(new ResultDTO[] {new ResultDTO("A", 2)}, "Aa"),
                Arguments.of(new ResultDTO[] {
                        new ResultDTO("A", 2),
                        new ResultDTO("B", 1),
                }, "Aab")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void analyzeText(ResultDTO[] expected, String text) {
        CharacterValidator mockValidator = Mockito.mock(CharacterValidator.class);
        Mockito.when(mockValidator.validate(anyString())).thenReturn(true);
        ResultDTO[] result = analyzer.analyzeText(text, mockValidator);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
        verify(mockValidator, atLeastOnce()).validate(anyString());
    }

    @Test
    void analyseEmptyOrInvalidText() {
        CharacterValidator mockValidator = Mockito.mock(CharacterValidator.class);
        Mockito.when(mockValidator.validate(anyString())).thenReturn(false);
        ResultDTO[] expected = new ResultDTO[]{};
        ResultDTO[] result = analyzer.analyzeText("", mockValidator);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
        verify(mockValidator, atLeastOnce()).validate(anyString());
    }
}