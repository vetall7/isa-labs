import {Component, inject, OnInit} from '@angular/core';
import {GenreService} from '@genre/services';
import {Genres} from '@genre/models/genres.model';

@Component({
  selector: 'app-genres-list',
  imports: [],
  providers: [GenreService],
  templateUrl: './genres-list.component.html',
  styleUrl: './genres-list.component.css',
  standalone: true
})
export class GenresListComponent implements OnInit {
  private readonly genreService = inject(GenreService);

  protected genres: Genres | undefined;

  public ngOnInit() : void {
    this.genreService.getGenres().subscribe(
      (genres: Genres) => {
        this.genres = genres;
      }
    )
  }
}
