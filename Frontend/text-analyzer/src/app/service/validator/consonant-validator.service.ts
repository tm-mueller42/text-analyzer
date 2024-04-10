import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class ConsonantValidatorService implements CharacterValidator{

  name : string;

  constructor() {
    this.name = "consonant";
   }

  validate(character: string) {
    return true;
  }
   
  }
