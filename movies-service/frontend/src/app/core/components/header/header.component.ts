import {Component, inject} from '@angular/core';
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
import {AuthService} from '../../../features/auth/services/auth.service';

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
    MatIconButton
  ],
  standalone: true,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  private isDarkTheme = false;
  private authStore = inject(Store);
  protected readonly authService = inject(AuthService);


  protected toggleTheme() : void {
    console.log('toggleTheme');
    this.isDarkTheme = !this.isDarkTheme;
    const body = document.body;

    if (this.isDarkTheme) {
      body.classList.add('dark-mode');
    } else {
      body.classList.remove('dark-mode');
    }
  }

  protected onLogout() : void {
    this.authStore.dispatch(logout());
  }
}
