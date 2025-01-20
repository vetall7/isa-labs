import {inject, Injectable, signal, Signal} from '@angular/core';
import {LocalStorageService} from '@shared/utils/local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ThemeTogglingService {
  private readonly localStorage = inject(LocalStorageService);

  public toggleTheme(): void {
    const currentTheme = this.localStorage.getItem('theme');
    if (currentTheme === 'light' || currentTheme === null) {
      this.localStorage.setItem('theme', 'dark');
    } else {
      this.localStorage.setItem('theme', 'light');
    }
  }

  public getIsDarkTheme(): boolean {
    return this.localStorage.getItem('theme') === 'dark';
  }
}
