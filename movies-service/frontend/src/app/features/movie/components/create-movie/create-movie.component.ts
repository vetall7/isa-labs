import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MovieService} from '@movie/services/movie-service.service';
import {DetailedMovie} from '@movie/models/DetailedMovie.module';
import { v4 as uuidv4 } from 'uuid';
import {ActivatedRoute, Router} from '@angular/router';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';

@Component({
  selector: 'app-create-movie',
  imports: [ReactiveFormsModule, MatFormField, MatInput, MatButton, MatLabel, MatDialogContent, MatDialogActions, MatDialogTitle],
  standalone: true,
  templateUrl: './create-movie.component.html',
  styleUrl: './create-movie.component.scss'
})
export class CreateMovieComponent {
  private readonly fb = inject(FormBuilder);

  private readonly movieService = inject(MovieService);

  private readonly data = inject<{ genreId: String}>(MAT_DIALOG_DATA);

  private readonly dialogRef = inject(MatDialogRef<CreateMovieComponent>);

  protected readonly movieForm: FormGroup = this.fb.group({
    title: ['', [Validators.required]],
    description: ['', [Validators.required]],
    year: [null, [Validators.required]],
    imageUrl: ['', [Validators.required]]
  })

  protected onSubmit(): void {
    const genre = this.data.genreId;
    if (this.movieForm.valid && genre) {
      const movie: DetailedMovie = this.movieForm.value;
      movie.genreName = genre;
      const id = uuidv4();
      this.movieService.createMovie(movie, id).subscribe(
        () => this.dialogRef.close()
      );
    }
  }
}
