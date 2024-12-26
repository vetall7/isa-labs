import { Component } from '@angular/core';
import { RouterOutlet} from '@angular/router';
import {HeaderComponent} from './core/components/header/header.component';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, MatButton],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true
})
export class AppComponent {
}
