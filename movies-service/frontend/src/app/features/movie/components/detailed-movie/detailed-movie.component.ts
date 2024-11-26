import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MovieService} from '@movie/services/movie-service.service';
import {DetailedMovie} from '@movie/models/DetailedMovie.module';

@Component({
  selector: 'app-detailed-movie',
  imports: [],
  standalone: true,
  templateUrl: './detailed-movie.component.html',
  styleUrl: './detailed-movie.component.css'
})
export class DetailedMovieComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);

  private readonly movieService = inject(MovieService);

  protected movie: DetailedMovie | undefined;

    public ngOnInit(): void {
        const movieId = this.route.snapshot.paramMap.get('movieId');
        if (movieId) {
        this.movieService.getDetailedMovie(movieId).subscribe(movie => this.movie = movie);
        }
    }
}
