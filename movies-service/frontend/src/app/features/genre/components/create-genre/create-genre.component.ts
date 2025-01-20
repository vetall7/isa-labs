import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {GenreService} from '@genre/services';
import {DetailedGenre} from '@genre/models';
import {catchError, EMPTY} from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-create-genre',
  imports: [ReactiveFormsModule, MatDialogContent, MatFormField, MatLabel, MatDialogActions, MatButton, MatInput, MatError, MatDialogTitle],
  standalone: true,
  templateUrl: './create-genre.component.html',
  styleUrl: './create-genre.component.scss'
})
export class CreateGenreComponent {
  private readonly fb = inject(FormBuilder);

  private readonly genreService = inject(GenreService);

  private readonly dialogRef = inject(MatDialogRef<CreateGenreComponent>);

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
            this.dialogRef.close();
            }
        );
      }
    }
  }
}
