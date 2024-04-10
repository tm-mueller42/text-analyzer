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

    //const data = fetch(request).then(res => res.json).then(res => {return res as unknown as Result[]})
    //return await data ?? {};
    const data = await (fetch(request));
    return await data.json() ?? [];
  }
   
  /*
  async getAnalysisResults(text: string, characterType: string): Promise<Result[]> {
      const data = await fetch(this.url);
      return await data.json() ?? [];
    }
  */
  
  constructor() { }
}
