package com.tobias.textAnalyzer.service;

import com.tobias.textAnalyzer.data.RequestDTO;
import com.tobias.textAnalyzer.data.ResultDTO;
import com.tobias.textAnalyzer.service.validator.CharacterValidator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;

@Service
public class TextAnalyzer {
    public ResultDTO[] analyzeText(String text, CharacterValidator validator) {
        String[] textArray = text.toUpperCase().split("");
        return Arrays.stream(textArray).
                filter(validator::validate)
                .collect(Collectors.groupingBy(Function.identity(), collectingAndThen(counting(), Long::intValue)))
                .entrySet().stream()
                .map(entry -> new ResultDTO(entry.getKey(), entry.getValue()))
                .toList().toArray(new ResultDTO[0]);
    }
}
