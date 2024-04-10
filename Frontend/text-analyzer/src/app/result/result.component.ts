import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Result } from 'app/result';

@Component({
  selector: 'app-result',
  standalone: true,
  imports: [
    CommonModule
  ],
  template: `
    <p>
      The character '{{result.character}}'' appears {{result.count}} times in this text.
    </p>
  `,
  styleUrl: './result.component.css'
})
export class ResultComponent {
  @Input() result!: Result ;
}
