import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TextAnalyzerService } from 'app/service/text-analyzer.service';
import { ResultComponent } from 'app/result/result.component';
import { Result } from 'app/result';
import { ApiService } from 'app/service/api.service';
import { AnalysisRequest } from 'app/analysis-request';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ResultComponent
  ],
  template: `
    <section>
      <form [formGroup]="analysisForm" (submit)="submitApplication()">
        <input type="text" placeholder="Please enter your text" formControlName="textInput">
        <button class="primary">Submit</button>
        <label>select character type
          <select formControlName="methodInput">
            <option value="VOWEL">Vowels</option>
            <option value="CONSONANT">Consonants</option>
            <option value="PUNCTUATION">Punctuation</option>
            <option value="GERMAN_UMLAUT">German Umlaute</option>
          </select>
        </label>
        <label>offline mode
          <input type="checkbox" formControlName="offlineMode">
        </label>
      </form>
    </section>
    <section>
      <app-result
        *ngFor="let currentResult of results" [result] = "currentResult">
      </app-result>
    </section>
  `,
  styleUrl: './form.component.css'
})
export class FormComponent {

  default = "VOWEL";

  analysisForm = new FormGroup({
    textInput: new FormControl(''),
    methodInput: new FormControl(''),
    offlineMode: new FormControl(false)
  });

  analyzer: TextAnalyzerService = inject(TextAnalyzerService);
  apiService: ApiService = inject(ApiService);

  analysisResult: Map<string, number> = new Map<string, number>;

  results: Result[] = [];

  constructor() {
    this.analysisForm.controls['methodInput'].setValue(this.default, {onlySelf: true});
  }

  submitApplication() {


    this.analyzer.characterType = "CONSONANT";
    const analysisRequest: AnalysisRequest = {text: this.analysisForm.value.textInput ?? "", characterType: this.analysisForm.value.methodInput ?? "vowel"};

    if (!this.analysisForm.value.offlineMode) {
      console.log(analysisRequest);
      this.apiService.getAnalysisResults(analysisRequest).then((fetchedResults: Result[]) => {
        console.log(fetchedResults);
        this.results = fetchedResults ?? [];
      });
    }

    else if (this.analysisForm.value.offlineMode) {
      this.analysisResult = this.analyzer.analyze(this.analysisForm.value.textInput ?? "", this.analysisForm.value.methodInput ?? "VOWEL");
      for (const key of this.analysisResult.keys()) {
        this.results.push({character:key, count: this.analysisResult.get(key) ?? 0})
        console.log("The character '", key, "' appears ", this.analysisResult.get(key), " times in this text.");
      }
    }
  }

}
