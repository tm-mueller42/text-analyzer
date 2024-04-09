import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  template: `
    <section>
      <form [formGroup]="analysisForm" (submit)="submitApplication()">
        <input type="text" placeholder="Please enter your text" formControlName="textInput">
        <button class="primary">Submit</button>
        <label>select character type
          <select formControlName="methodInput">
            <option value="vowel">Vowels</option>
            <option value="consonant">Consonants</option>
            <option value="punctuation">Punctuation</option>
            <option value="German Umlaut">German Umlaute</option>
          </select>
        </label>
        <label>offline mode
          <input type="checkbox" formControlName="offlineMode">
        </label>
      </form>
    </section>
  `,
  styleUrl: './form.component.css'
})
export class FormComponent {

  default = "vowel";

  analysisForm = new FormGroup({
    textInput: new FormControl(''),
    methodInput: new FormControl(''),
    offlineMode: new FormControl(false)
  });

  constructor() {
    this.analysisForm.controls['methodInput'].setValue(this.default, {onlySelf: true});
  }

  submitApplication() {

    console.log(`Homes application received: 
    Text: ${this.analysisForm.value.textInput ?? ''}, 
    offlineMode: ${this.analysisForm.value.offlineMode ?? false},
    Method: ${this.analysisForm.value.methodInput ?? ''}`);
  }

}