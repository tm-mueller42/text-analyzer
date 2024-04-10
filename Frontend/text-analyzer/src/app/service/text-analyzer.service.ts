import { Injectable, inject } from '@angular/core';
import { CharacterValidator } from './validator/character-validator';
import { ValidatorService } from './validator-service.service';
import { Result } from 'app/result';
import { AnalysisRequest } from 'app/analysis-request';

@Injectable({
  providedIn: 'root',
})
export class TextAnalyzerService {

  validatorService: ValidatorService = inject(ValidatorService);

  constructor() { }

  analyze(analysisRequest: AnalysisRequest) {
    const validator: CharacterValidator = this.validatorService.provideValidator(analysisRequest.characterType);
    const characters: string[] = analysisRequest.text.split("");
    let analysisResult2: Result[] = [];
    for (let character of characters) {
      character = character.toUpperCase();
      if (character.length === 1 && validator.validate(character)) {
        analysisResult2.find(result => result.character === character) ? 
        analysisResult2.find(result => result.character === character)!.count++ :
        analysisResult2.push({character: character, count: 1});
      }
    }
    return analysisResult2;
  }
}
