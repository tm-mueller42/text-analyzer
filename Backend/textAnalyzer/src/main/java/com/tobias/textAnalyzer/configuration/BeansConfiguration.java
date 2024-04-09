package com.tobias.textAnalyzer.configuration;

import com.tobias.textAnalyzer.service.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class BeansConfiguration {

    @Bean
    public Set<CharacterValidator> validators() {
        return Set.of(
                new ConsonantValidator(),
                new VowelValidator(),
                new GermanUmlautValidator(),
                new PunctuationValidator());
    }
}
