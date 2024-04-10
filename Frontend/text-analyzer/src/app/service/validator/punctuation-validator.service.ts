import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class PunctuationValidatorService implements CharacterValidator{

  characterType : string;
  characterTypeDefinition: string;

  constructor() {
    this.characterType = "PUNCTUATION";
    this.characterTypeDefinition = ".,;:!?-()[]{}";
   }

  validate(character: string) {
    return this.characterTypeDefinition.includes(character);
  }
}
