import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class VowelValidatorService implements CharacterValidator{

  characterType : string;
  characterTypeDefinition: string;

  constructor() {
    this.characterType = "vowel";
    this.characterTypeDefinition = "AEIOU";
   }

  validate(character: string) {
    return this.characterTypeDefinition.includes(character);
  }
}
