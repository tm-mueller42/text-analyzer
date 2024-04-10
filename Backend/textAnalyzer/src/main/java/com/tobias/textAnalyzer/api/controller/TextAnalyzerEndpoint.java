package com.tobias.textAnalyzer.api.controller;

import com.tobias.textAnalyzer.api.exception.BadRequestException;
import com.tobias.textAnalyzer.data.RequestDTO;
import com.tobias.textAnalyzer.data.ResultDTO;
import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import com.tobias.textAnalyzer.service.TextAnalyzer;
import com.tobias.textAnalyzer.service.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.HashMap;

@RestController
@RequestMapping("analyze")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerEndpoint {

    private final TextAnalyzer textAnalyzer;
    private final ValidatorService validatorService;

    @PostMapping
    ResultDTO[] analyzeText(@RequestBody RequestDTO request) throws BadRequestException {
        CharacterValidator validator = validatorService.provideValidator(request.getCharacterType())
                .orElseThrow(BadRequestException::new);
        return textAnalyzer.analyzeText(request.getText(), validator);
    }
}
