import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {GenreService} from '@genre/services';
import {DetailedGenre} from '@genre/models';
import {Router} from '@angular/router';
import {catchError, EMPTY} from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-genre',
  imports: [ReactiveFormsModule],
  standalone: true,
  templateUrl: './create-genre.component.html',
  styleUrl: './create-genre.component.css'
})
export class CreateGenreComponent {
  private readonly fb = inject(FormBuilder);

  private readonly router = inject(Router);

  private readonly genreService = inject(GenreService);

  protected genreForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]]
  })

  protected onSubmit() : void {
    if (this.genreForm.valid){
      const {name, description} = this.genreForm.value;
      if (name && description){
        const newGenre: DetailedGenre = {
          name: name,
          description: description
        }
        this.genreService.createGenre(newGenre).
        pipe(
          catchError((error) => {
              if (error instanceof HttpErrorResponse){
                if (error.status == 409){
                  console.log('Conflict: Genre already exists');
                }
              }
              return EMPTY;
          })
        )
          .subscribe( () => {
                this.router.navigate(['/genres']);
            }
        );
      }
    }
  }
}
