import {Component, inject, Input} from '@angular/core';
import {Genre} from '@genre/models';
import {GenreService} from '@genre/services';
import {RouterLink} from '@angular/router';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardImage,
  MatCardModule
} from '@angular/material/card';
import {MatButton, MatButtonModule} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {EditGenreComponent} from '@genre/components/edit-genre/edit-genre.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-single-genre',
  imports: [
    RouterLink,
    MatCardModule, MatButtonModule, MatIcon
  ],
  providers: [],
  standalone: true,
  templateUrl: './single-genre.component.html',
  styleUrl: './single-genre.component.scss'
})
export class SingleGenreComponent {
  @Input() genre: Genre | undefined;

  private readonly dialog = inject(MatDialog);

  private readonly genreService = inject(GenreService);

  protected onDelete(): void {
    if (this.genre) {
      this.genreService.deleteGenre(this.genre).subscribe();
    }
  }

  protected onEditGenre(genreId: String): void {
    this.dialog.open(EditGenreComponent, {
      data: { genreId: genreId }
    });
  }
}
