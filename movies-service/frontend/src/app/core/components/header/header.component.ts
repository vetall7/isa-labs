import {Component, inject, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {MatToolbar} from '@angular/material/toolbar';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {toSignal} from '@angular/core/rxjs-interop';
import {Store} from '@ngrx/store';
import {selectIsAuthenticated} from '../../../states/auth/auth.selector';
import {logout} from '../../../states/auth/auth.action';
import {AuthService} from '@shared/services/auth.service';
import {ThemeTogglingService} from '@shared/services/theme-toggling.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-header',
  imports: [
    RouterLink,
    MatToolbar,
    MatMenuTrigger,
    MatButton,
    MatIconModule,
    MatMenuItem,
    MatSlideToggleModule,
    MatInputModule,
    MatMenu,
    MatIconButton,
    FormsModule
  ],
  standalone: true,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  private authStore = inject(Store);

  protected readonly authService = inject(AuthService);

  private readonly themeTogglingService = inject(ThemeTogglingService);

  protected isDarkTheme = this.themeTogglingService.getIsDarkTheme();

  public ngOnInit() : void {
    this.setTheme(this.themeTogglingService.getIsDarkTheme());
  }

  protected toggleTheme() : void {
    this.themeTogglingService.toggleTheme();

    this.setTheme(this.themeTogglingService.getIsDarkTheme());
  }

  private setTheme(isDark: boolean) : void {
    const body = document.body;
    if (isDark) {
      body.classList.add('dark-mode');
    } else {
      body.classList.remove('dark-mode');
    }
  }

  protected onLogout() : void {
    this.authStore.dispatch(logout());
  }
}
