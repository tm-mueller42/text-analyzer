import { Injectable, inject } from '@angular/core';
import { CharacterValidator } from './validator/character-validator';
import { ValidatorService } from './validator-service.service';
import { VowelValidatorService } from './validator/vowel-validator.service';

@Injectable({
  providedIn: 'root',
})
export class TextAnalyzerService {

  characterType: string = "consonant";
  validatorService: ValidatorService = inject(ValidatorService);

  constructor() { }

  analyze(text: string, characterType: string) {
    this.characterType = characterType;
    const validator: CharacterValidator = this.validatorService.provideValidator(characterType);
    const characters: string[] = text.split("");
    let analysisResult = new Map<string, number>();
    for (let character of characters) {
      character = character.toUpperCase();
      if (character.length === 1 && validator.validate(character)) {
        const currentValue: number = analysisResult.get(character)?? 0;
        analysisResult.set(character, currentValue+1) 
      }
    }
    
    return analysisResult;
  }
}
