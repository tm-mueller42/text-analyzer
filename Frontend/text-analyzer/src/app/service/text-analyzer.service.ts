import { Injectable, inject } from '@angular/core';
import { CharacterValidator } from './validator/character-validator';
import { ValidatorService } from './validator-service.service';

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzerService {

  validatorService: ValidatorService = inject(ValidatorService);
  validator: CharacterValidator = inject(this.validatorService.getValidator);

  analyze(text: string, characterType: string) {

  }
  constructor() { }
}
