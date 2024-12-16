import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MovieService} from '@movie/services/movie-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {DetailedMovie} from '@movie/models/DetailedMovie.module';

@Component({
  selector: 'app-edit-movie',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  standalone: true,
  templateUrl: './edit-movie.component.html',
  styleUrl: './edit-movie.component.scss'
})
export class EditMovieComponent implements OnInit {
  private readonly fb = inject(FormBuilder);

  private readonly movieService = inject(MovieService);

  private readonly router = inject(Router);

  private readonly route = inject(ActivatedRoute);

  private movieId: String | null = null;

  protected readonly movieForm: FormGroup = this.fb.group({
    title: [{value:'', disabled: true}, [Validators.required]],
    description: ['', [Validators.required]],
    year: [{value:null, disabled: true}, [Validators.required]],
    imageUrl: ['', [Validators.required]],
    genreName: [{value:'', disabled: true}, [Validators.required]]
  })

  public ngOnInit(): void {
    this.movieId = this.route.snapshot.paramMap.get('movieId');
    if (this.movieId) {
      this.movieService.getDetailedMovie(this.movieId).subscribe(movie => this.movieForm.setValue(movie));
    }
  }

  protected onSubmit(): void {
    const genre = this.route.snapshot.paramMap.get('genreId');
    if (this.movieForm.valid && genre) {
      const movie: DetailedMovie = this.movieForm.value;
      movie.genreName = genre;
      if (this.movieId) {
        this.movieService.updateMovie(movie, this.movieId).subscribe(
          () => this.router.navigate(['/genres', genre])
        );
      }
    }
  }
}
