import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRessourceVOD, RessourceVOD } from 'app/shared/model/ressource-vod.model';
import { RessourceVODService } from './ressource-vod.service';
import { ICategorieVOD } from 'app/shared/model/categorie-vod.model';
import { CategorieVODService } from 'app/entities/categorie-vod/categorie-vod.service';

@Component({
  selector: 'jhi-ressource-vod-update',
  templateUrl: './ressource-vod-update.component.html'
})
export class RessourceVODUpdateComponent implements OnInit {
  isSaving = false;

  categorievods: ICategorieVOD[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    description: [],
    lienRessourceVOD: [null, [Validators.required]],
    pathLogo: [],
    code: [],
    resume: [],
    categorieVODId: []
  });

  constructor(
    protected ressourceVODService: RessourceVODService,
    protected categorieVODService: CategorieVODService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ressourceVOD }) => {
      this.updateForm(ressourceVOD);

      this.categorieVODService
        .query()
        .pipe(
          map((res: HttpResponse<ICategorieVOD[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategorieVOD[]) => (this.categorievods = resBody));
    });
  }

  updateForm(ressourceVOD: IRessourceVOD): void {
    this.editForm.patchValue({
      id: ressourceVOD.id,
      libelle: ressourceVOD.libelle,
      description: ressourceVOD.description,
      lienRessourceVOD: ressourceVOD.lienRessourceVOD,
      pathLogo: ressourceVOD.pathLogo,
      code: ressourceVOD.code,
      resume: ressourceVOD.resume,
      categorieVODId: ressourceVOD.categorieVODId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ressourceVOD = this.createFromForm();
    if (ressourceVOD.id !== undefined) {
      this.subscribeToSaveResponse(this.ressourceVODService.update(ressourceVOD));
    } else {
      this.subscribeToSaveResponse(this.ressourceVODService.create(ressourceVOD));
    }
  }

  private createFromForm(): IRessourceVOD {
    return {
      ...new RessourceVOD(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
      lienRessourceVOD: this.editForm.get(['lienRessourceVOD'])!.value,
      pathLogo: this.editForm.get(['pathLogo'])!.value,
      code: this.editForm.get(['code'])!.value,
      resume: this.editForm.get(['resume'])!.value,
      categorieVODId: this.editForm.get(['categorieVODId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRessourceVOD>>): void {
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

  trackById(index: number, item: ICategorieVOD): any {
    return item.id;
  }
}
