package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.api.exception.BadRequestException;
import com.tobias.textAnalyzer.data.CharacterType;
import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ValidatorService {

    private final Set<CharacterValidator> validators;

    public Optional<CharacterValidator> provideValidator(CharacterType characterType) {
        return validators.stream()
                .filter(currentValidator -> currentValidator.getCaracterType().equals(characterType))
                .findFirst();
    }
}
