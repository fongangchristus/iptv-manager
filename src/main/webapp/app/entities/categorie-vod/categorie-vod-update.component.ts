import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieVOD, CategorieVOD } from 'app/shared/model/categorie-vod.model';
import { CategorieVODService } from './categorie-vod.service';

@Component({
  selector: 'jhi-categorie-vod-update',
  templateUrl: './categorie-vod-update.component.html'
})
export class CategorieVODUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libele: [null, [Validators.required]],
    description: []
  });

  constructor(protected categorieVODService: CategorieVODService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieVOD }) => {
      this.updateForm(categorieVOD);
    });
  }

  updateForm(categorieVOD: ICategorieVOD): void {
    this.editForm.patchValue({
      id: categorieVOD.id,
      libele: categorieVOD.libele,
      description: categorieVOD.description
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieVOD = this.createFromForm();
    if (categorieVOD.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieVODService.update(categorieVOD));
    } else {
      this.subscribeToSaveResponse(this.categorieVODService.create(categorieVOD));
    }
  }

  private createFromForm(): ICategorieVOD {
    return {
      ...new CategorieVOD(),
      id: this.editForm.get(['id'])!.value,
      libele: this.editForm.get(['libele'])!.value,
      description: this.editForm.get(['description'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieVOD>>): void {
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
