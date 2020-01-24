import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IChaine, Chaine } from 'app/shared/model/chaine.model';
import { ChaineService } from './chaine.service';
import { ICategorieTV } from 'app/shared/model/categorie-tv.model';
import { CategorieTVService } from 'app/entities/categorie-tv/categorie-tv.service';

@Component({
  selector: 'jhi-chaine-update',
  templateUrl: './chaine-update.component.html'
})
export class ChaineUpdateComponent implements OnInit {
  isSaving = false;

  categorietvs: ICategorieTV[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    description: [],
    lien: [null, [Validators.required]],
    code: [],
    pathLogo: [],
    resume: [],
    categorieTId: []
  });

  constructor(
    protected chaineService: ChaineService,
    protected categorieTVService: CategorieTVService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chaine }) => {
      this.updateForm(chaine);

      this.categorieTVService
        .query()
        .pipe(
          map((res: HttpResponse<ICategorieTV[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICategorieTV[]) => (this.categorietvs = resBody));
    });
  }

  updateForm(chaine: IChaine): void {
    this.editForm.patchValue({
      id: chaine.id,
      libelle: chaine.libelle,
      description: chaine.description,
      lien: chaine.lien,
      code: chaine.code,
      pathLogo: chaine.pathLogo,
      resume: chaine.resume,
      categorieTId: chaine.categorieTId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chaine = this.createFromForm();
    if (chaine.id !== undefined) {
      this.subscribeToSaveResponse(this.chaineService.update(chaine));
    } else {
      this.subscribeToSaveResponse(this.chaineService.create(chaine));
    }
  }

  private createFromForm(): IChaine {
    return {
      ...new Chaine(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
      lien: this.editForm.get(['lien'])!.value,
      code: this.editForm.get(['code'])!.value,
      pathLogo: this.editForm.get(['pathLogo'])!.value,
      resume: this.editForm.get(['resume'])!.value,
      categorieTId: this.editForm.get(['categorieTId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChaine>>): void {
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

  trackById(index: number, item: ICategorieTV): any {
    return item.id;
  }
}
