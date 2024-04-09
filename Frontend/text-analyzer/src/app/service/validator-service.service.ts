import { Injectable, inject } from '@angular/core';
import { VowelValidatorService } from './validator/vowel-validator.service';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  validator: VowelValidatorService = inject(VowelValidatorService)
  constructor() { }

  getValidator() {
    return this.validator;
  }
}
