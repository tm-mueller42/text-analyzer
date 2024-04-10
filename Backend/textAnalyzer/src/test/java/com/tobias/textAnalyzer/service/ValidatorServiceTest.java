package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.data.CharacterType;
import com.tobias.textAnalyzer.service.validator.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.tobias.textAnalyzer.data.CharacterType.*;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorServiceTest {

    private final Set<CharacterValidator> validators = Set.of(
            new VowelValidator(),
            new ConsonantValidator(),
            new GermanUmlautValidator(),
            new PunctuationValidator()
    );
    private final ValidatorService validatorService = new ValidatorService(validators);

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(new VowelValidator(), VOWEL),
                Arguments.of(new ConsonantValidator(), CONSONANT),
                Arguments.of(new GermanUmlautValidator(), GERMAN_UMLAUT),
                Arguments.of(new PunctuationValidator(), PUNCTUATION)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void provideValidator(CharacterValidator expected, CharacterType type) {
        CharacterValidator result = validatorService.provideValidator(type).orElseThrow();
        Assertions.assertInstanceOf(expected.getClass(), result);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void provideFalseValidator() {
        ConsonantValidator expected = new ConsonantValidator();
        CharacterValidator result = validatorService.provideValidator(VOWEL).orElseThrow();
        Assertions.assertFalse(result instanceof ConsonantValidator);
        assertThat(result).usingRecursiveComparison().isNotEqualTo(expected);
    }
}