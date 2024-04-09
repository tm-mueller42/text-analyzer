package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.data.CharacterType;
import com.tobias.textAnalyzer.service.validator.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.tobias.textAnalyzer.data.CharacterType.*;

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
                Arguments.of(VowelValidator.class, VOWEL),
                Arguments.of(ConsonantValidator.class, CONSONANT),
                Arguments.of(GermanUmlautValidator.class, GERMAN_UMLAUT),
                Arguments.of(PunctuationValidator.class, PUNCTUATION)
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void provideValidator(Class<Object> expected, CharacterType type) {

        CharacterValidator result = validatorService.provideValidator(type).orElseThrow();
        Assertions.assertInstanceOf(expected, result);
        Assertions.assertEquals(type, result.getCaracterType());
    }
}