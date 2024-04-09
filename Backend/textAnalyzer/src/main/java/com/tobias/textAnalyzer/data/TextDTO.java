package com.tobias.textAnalyzer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TextDTO {
    public String text;
    public CharacterType characterType;
}
