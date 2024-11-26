import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GenreService} from '@genre/services';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DetailedGenre} from '@genre/models';

@Component({
  selector: 'app-edit-genre',
  imports: [ReactiveFormsModule],
  standalone: true,
  templateUrl: './edit-genre.component.html',
  styleUrl: './edit-genre.component.css'
})
export class EditGenreComponent implements OnInit {
  private route = inject(ActivatedRoute);

  private readonly router = inject(Router);

  private genreService = inject(GenreService);

  protected fb = inject(FormBuilder);

  protected genreForm: FormGroup = this.fb.group({
    name: [{ value: '', disabled: true }, [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]],
  });

  public ngOnInit() : void{
    const genreId = this.route.snapshot.paramMap.get('genreId');
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
      console.log(name, description);
      if (name && description){

        const updatedGenre: DetailedGenre = {
          name: name,
          description: description
        }
        this.genreService.updateGenre(updatedGenre).subscribe(
          () => this.router.navigate(['/genres'])
        );
      }
    }
  }
}
