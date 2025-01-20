import {Component, inject, OnInit, Signal} from '@angular/core';
import {GenreService} from '@genre/services';
import {SingleGenreComponent} from '@genre/components/single-genre/single-genre.component';
import {RouterLink} from '@angular/router';
import {Genres} from '@genre/models';
import {MatList, MatListItem, MatListSubheaderCssMatStyler} from '@angular/material/list';
import {MatDivider} from '@angular/material/divider';
import {MatFabButton, MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {CreateGenreComponent} from '@genre/components/create-genre/create-genre.component';
import {EditGenreComponent} from '@genre/components/edit-genre/edit-genre.component';
import {MatProgressSpinner} from '@angular/material/progress-spinner';


@Component({
  selector: 'app-genres-list',
  imports: [SingleGenreComponent, RouterLink, MatList, MatListSubheaderCssMatStyler, MatListItem, MatDivider, MatFabButton, MatIcon, MatIconButton, MatProgressSpinner],
  templateUrl: './genres-list.component.html',
  styleUrl: './genres-list.component.scss',
  standalone: true
})
export class GenresListComponent implements OnInit {
  private readonly genreService = inject(GenreService);

  protected genresSig: Signal<Genres | null> = this.genreService.genresSig;

  readonly dialog = inject(MatDialog);

  public ngOnInit() : void {
    this.genreService.getGenres().subscribe();
  }

  protected onCreateGenre(): void {
    this.dialog.open(CreateGenreComponent);
  }
}
