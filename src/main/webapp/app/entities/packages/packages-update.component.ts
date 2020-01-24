import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPackages, Packages } from 'app/shared/model/packages.model';
import { PackagesService } from './packages.service';
import { IChaine } from 'app/shared/model/chaine.model';
import { ChaineService } from 'app/entities/chaine/chaine.service';
import { IRessourceVOD } from 'app/shared/model/ressource-vod.model';
import { RessourceVODService } from 'app/entities/ressource-vod/ressource-vod.service';

type SelectableEntity = IChaine | IRessourceVOD;

@Component({
  selector: 'jhi-packages-update',
  templateUrl: './packages-update.component.html'
})
export class PackagesUpdateComponent implements OnInit {
  isSaving = false;

  chaines: IChaine[] = [];

  ressourcevods: IRessourceVOD[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    description: [],
    prixtUnitaire: [],
    pathLogo: [],
    resume: [],
    packagechaines: [],
    packageVODS: []
  });

  constructor(
    protected packagesService: PackagesService,
    protected chaineService: ChaineService,
    protected ressourceVODService: RessourceVODService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ packages }) => {
      this.updateForm(packages);

      this.chaineService
        .query()
        .pipe(
          map((res: HttpResponse<IChaine[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IChaine[]) => (this.chaines = resBody));

      this.ressourceVODService
        .query()
        .pipe(
          map((res: HttpResponse<IRessourceVOD[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IRessourceVOD[]) => (this.ressourcevods = resBody));
    });
  }

  updateForm(packages: IPackages): void {
    this.editForm.patchValue({
      id: packages.id,
      libelle: packages.libelle,
      description: packages.description,
      prixtUnitaire: packages.prixtUnitaire,
      pathLogo: packages.pathLogo,
      resume: packages.resume,
      packagechaines: packages.packagechaines,
      packageVODS: packages.packageVODS
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const packages = this.createFromForm();
    if (packages.id !== undefined) {
      this.subscribeToSaveResponse(this.packagesService.update(packages));
    } else {
      this.subscribeToSaveResponse(this.packagesService.create(packages));
    }
  }

  private createFromForm(): IPackages {
    return {
      ...new Packages(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
      prixtUnitaire: this.editForm.get(['prixtUnitaire'])!.value,
      pathLogo: this.editForm.get(['pathLogo'])!.value,
      resume: this.editForm.get(['resume'])!.value,
      packagechaines: this.editForm.get(['packagechaines'])!.value,
      packageVODS: this.editForm.get(['packageVODS'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPackages>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: SelectableEntity[], option: SelectableEntity): SelectableEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
