import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GenreService} from '@genre/services';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DetailedGenre} from '@genre/models';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-edit-genre',
  imports: [ReactiveFormsModule, MatDialogContent, MatFormField, MatError, MatDialogActions, MatButton, MatInput, MatLabel, MatDialogTitle],
  standalone: true,
  templateUrl: './edit-genre.component.html',
  styleUrl: './edit-genre.component.scss'
})
export class EditGenreComponent implements OnInit {
  private route = inject(ActivatedRoute);

  private readonly router = inject(Router);

  private genreService = inject(GenreService);

  protected fb = inject(FormBuilder);

  private readonly data = inject<{ genreId: String}>(MAT_DIALOG_DATA);

  private readonly dialogRef = inject(MatDialogRef<EditGenreComponent>);


  protected genreForm: FormGroup = this.fb.group({
    name: [{ value: '', disabled: true }, [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]],
  });

  public ngOnInit() : void{
    const genreId = this.data.genreId;
    if (genreId) {
      this.genreService.getGenreDetails(genreId).subscribe(
        (genre) => this.genreForm.patchValue({
          name: genre.name,
          description: genre.description
        })
      );
    }
  }

  protected onSubmit() : void{
    if (this.genreForm.valid) {
      const {name, description} = this.genreForm.getRawValue();
      if (name && description){

        const updatedGenre: DetailedGenre = {
          name: name,
          description: description
        }
        this.genreService.updateGenre(updatedGenre).subscribe(
          () => this.dialogRef.close()
        );
      }
    }
  }
}
