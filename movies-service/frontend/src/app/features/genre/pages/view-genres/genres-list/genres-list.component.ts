import {Component, inject, OnInit, Signal} from '@angular/core';
import {GenreService} from '@genre/services';
import {SingleGenreComponent} from '@genre/components/single-genre/single-genre.component';
import {RouterLink} from '@angular/router';
import {Genres} from '@genre/models';


@Component({
  selector: 'app-genres-list',
  imports: [SingleGenreComponent,  RouterLink],
  templateUrl: './genres-list.component.html',
  styleUrl: './genres-list.component.scss',
  standalone: true
})
export class GenresListComponent implements OnInit {
  private readonly genreService = inject(GenreService);

  protected genresSig: Signal<Genres | null> = this.genreService.genresSig;

  public ngOnInit() : void {
    this.genreService.getGenres().subscribe();
  }
}
