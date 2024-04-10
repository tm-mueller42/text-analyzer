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
      <form [formGroup]="analysisForm" (submit)="submitDataForAnalysis()">
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
        *ngFor="let currentResult of analysisResults" [result] = "currentResult">
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
  analysisResults: Result[] = [];

  submitDataForAnalysis() {
    const analysisRequest: AnalysisRequest = {
      text: this.analysisForm.value.textInput ?? "", characterType: this.analysisForm.value.methodInput ?? "VOWEL"
    };

    if (!this.analysisForm.value.offlineMode) {
      this.apiService.getAnalysisResults(analysisRequest).then((fetchedResults: Result[]) => {
        this.analysisResults = fetchedResults ?? [];
      });
    }
    else if (this.analysisForm.value.offlineMode) {
      this.analysisResults = this.analyzer.analyze(analysisRequest);
    }
  }

  constructor() {
    this.analysisForm.controls['methodInput'].setValue(this.default, {onlySelf: true});
  }
}
