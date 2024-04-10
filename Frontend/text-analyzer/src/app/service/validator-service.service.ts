import { Injectable, inject, InjectionToken, Injector } from '@angular/core';
import { VowelValidatorService } from './validator/vowel-validator.service';
import { ConsonantValidatorService } from './validator/consonant-validator.service';
import { PunctuationValidatorService } from './validator/punctuation-validator.service';
import { GermanUmlautValidatorService } from './validator/german-umlaut-validator.service';
import { CharacterValidator } from './validator/character-validator';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  validators: CharacterValidator[] = 
  [
    new VowelValidatorService,
    new ConsonantValidatorService,
    new PunctuationValidatorService,
    new GermanUmlautValidatorService
  ];

  constructor() { }

  provideValidator(characterType: string) {
    return this.validators.find(val => val.characterType === characterType) ??  new VowelValidatorService;
  }
}
                                                                                      