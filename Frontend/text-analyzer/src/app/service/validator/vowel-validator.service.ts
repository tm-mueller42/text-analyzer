import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class VowelValidatorService implements CharacterValidator{

  name : string;

  constructor() {
    this.name = "vowel";
   }

  validate(character: string) {
    return true;
  }
   
  }