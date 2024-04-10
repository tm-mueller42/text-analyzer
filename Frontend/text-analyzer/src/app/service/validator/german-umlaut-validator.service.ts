import { Injectable } from '@angular/core';
import { CharacterValidator } from './character-validator';

@Injectable({
  providedIn: 'root'
})
export class GermanUmlautValidatorService implements CharacterValidator{

  name : string;

  constructor() {
    this.name = "germanUmlaut";
   }

  validate(character: string) {
    return true;
  }
   
  }
