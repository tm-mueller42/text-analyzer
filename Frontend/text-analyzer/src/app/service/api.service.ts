import { Injectable } from '@angular/core';
import { Result } from 'app/result';
import { AnalysisRequest } from 'app/analysis-request';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  url = 'http://localhost:8080/analyze';

  async getAnalysisResults(analysisRequest: AnalysisRequest): Promise<Result[]> {

    const headers: Headers = new Headers();
    headers.set('Content-Type', 'application/json');
    const request: RequestInfo = new Request(`${this.url}`, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(analysisRequest)
    })

    const data = await fetch(request);
    return await data.json() ?? [];
  }

  constructor() { }
}
