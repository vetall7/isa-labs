import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MovieService} from '@movie/services/movie-service.service';
import {DetailedMovie} from '@movie/models/DetailedMovie.module';
import { v4 as uuidv4 } from 'uuid';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-create-movie',
  imports: [ReactiveFormsModule],
  standalone: true,
  templateUrl: './create-movie.component.html',
  styleUrl: './create-movie.component.css'
})
export class CreateMovieComponent {
  private readonly fb = inject(FormBuilder);

  private readonly movieService = inject(MovieService);

  private readonly router = inject(Router);

  private readonly route = inject(ActivatedRoute);

  protected readonly movieForm: FormGroup = this.fb.group({
    title: ['', [Validators.required]],
    description: ['', [Validators.required]],
    year: [null, [Validators.required]],
    imageUrl: ['', [Validators.required]]
  })

  protected onSubmit(): void {
    const genre = this.route.snapshot.paramMap.get('genreId');
    if (this.movieForm.valid && genre) {
      const movie: DetailedMovie = this.movieForm.value;
      movie.genreName = genre;
      const id = uuidv4();
      this.movieService.createMovie(movie, id).subscribe(
        () => this.router.navigate(['/genres', genre])
      );
    }
  }
}
