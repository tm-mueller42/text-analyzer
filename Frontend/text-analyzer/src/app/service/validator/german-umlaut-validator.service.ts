import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class GermanUmlautValidatorService implements CharacterValidator{

  characterType : string;
  characterTypeDefinition: string;

  constructor() {
    this.characterType = "GERMAN_UMLAUT";
    this.characterTypeDefinition = "ÄÖÜ";
   }

  validate(character: string) {
    return this.characterTypeDefinition.includes(character);
  }
}
