import {Component, inject, Input} from '@angular/core';
import {Genre} from '@genre/models';
import {GenreService} from '@genre/services';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-single-genre',
  imports: [
    RouterLink
  ],
  providers: [],
  standalone: true,
  templateUrl: './single-genre.component.html',
  styleUrl: './single-genre.component.css'
})
export class SingleGenreComponent {
  @Input() genre: Genre | undefined;

  private readonly genreService = inject(GenreService);

  protected onDelete(): void {
    if (this.genre) {
      this.genreService.deleteGenre(this.genre).subscribe();
    }
  }
}
