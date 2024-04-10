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
  ]

  constructor() { }


  provideValidator(characterType: string) {
    function getClassToken(className: string): InjectionToken<any> {
      return new InjectionToken<any>(className);
    }
    const validator: CharacterValidator = this.validators.find(val => val.characterType === characterType) ??  new VowelValidatorService;
    const name = validator.characterType;
    console.log(validator.constructor.name);
    const token = getClassToken(name);
    return validator;
  }
}
                                                                                      