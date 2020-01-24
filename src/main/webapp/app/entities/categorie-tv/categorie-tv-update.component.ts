import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieTV, CategorieTV } from 'app/shared/model/categorie-tv.model';
import { CategorieTVService } from './categorie-tv.service';

@Component({
  selector: 'jhi-categorie-tv-update',
  templateUrl: './categorie-tv-update.component.html'
})
export class CategorieTVUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libele: [null, [Validators.required]],
    description: []
  });

  constructor(protected categorieTVService: CategorieTVService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieTV }) => {
      this.updateForm(categorieTV);
    });
  }

  updateForm(categorieTV: ICategorieTV): void {
    this.editForm.patchValue({
      id: categorieTV.id,
      libele: categorieTV.libele,
      description: categorieTV.description
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieTV = this.createFromForm();
    if (categorieTV.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieTVService.update(categorieTV));
    } else {
      this.subscribeToSaveResponse(this.categorieTVService.create(categorieTV));
    }
  }

  private createFromForm(): ICategorieTV {
    return {
      ...new CategorieTV(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieTV>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
