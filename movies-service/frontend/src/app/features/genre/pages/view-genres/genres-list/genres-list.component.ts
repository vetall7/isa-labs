import {Component, inject, OnInit} from '@angular/core';
import {GenreService} from '@genre/services';
import {SingleGenreComponent} from '@genre/components/single-genre/single-genre.component';
import {AsyncPipe} from '@angular/common';
import {Observable} from 'rxjs';
import {Genres} from '@genre/models/genres.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-genres-list',
  imports: [SingleGenreComponent, AsyncPipe, RouterLink],
  templateUrl: './genres-list.component.html',
  styleUrl: './genres-list.component.css',
  standalone: true
})
export class GenresListComponent implements OnInit {
  private readonly genreService = inject(GenreService);

  protected genres$ : Observable<Genres | null> = this.genreService.genres$;

  public ngOnInit() : void {
    this.genreService.getGenres().subscribe();
  }
}
