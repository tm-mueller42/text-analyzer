import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class ConsonantValidatorService implements CharacterValidator{

  characterType : string;
  characterTypeDefinition: string;

  constructor() {
    this.characterType = "consonant";
    this.characterTypeDefinition = "BCDFGHJKLMNPQRSTVWXYZ";
   }

  validate(character: string) {
    return this.characterTypeDefinition.includes(character);
  }
}
