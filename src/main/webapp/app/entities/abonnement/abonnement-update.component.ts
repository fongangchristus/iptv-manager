import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { IAbonnement, Abonnement } from 'app/shared/model/abonnement.model';
import { AbonnementService } from './abonnement.service';
import { IPackages } from 'app/shared/model/packages.model';
import { PackagesService } from 'app/entities/packages/packages.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

type SelectableEntity = IPackages | IClient;

@Component({
  selector: 'jhi-abonnement-update',
  templateUrl: './abonnement-update.component.html'
})
export class AbonnementUpdateComponent implements OnInit {
  isSaving = false;

  packages: IPackages[] = [];

  clients: IClient[] = [];
  dateDebutDp: any;
  dateFinDp: any;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    description: [],
    dateDebut: [null, [Validators.required]],
    dateFin: [null, [Validators.required]],
    lienRessourceVOD: [],
    lienRessourceTv: [],
    statut: [],
    packagesId: [],
    clientId: []
  });

  constructor(
    protected abonnementService: AbonnementService,
    protected packagesService: PackagesService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ abonnement }) => {
      this.updateForm(abonnement);

      this.packagesService
        .query()
        .pipe(
          map((res: HttpResponse<IPackages[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPackages[]) => (this.packages = resBody));

      this.clientService
        .query()
        .pipe(
          map((res: HttpResponse<IClient[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClient[]) => (this.clients = resBody));
    });
  }

  updateForm(abonnement: IAbonnement): void {
    this.editForm.patchValue({
      id: abonnement.id,
      libelle: abonnement.libelle,
      description: abonnement.description,
      dateDebut: abonnement.dateDebut,
      dateFin: abonnement.dateFin,
      lienRessourceVOD: abonnement.lienRessourceVOD,
      lienRessourceTv: abonnement.lienRessourceTv,
      statut: abonnement.statut,
      packagesId: abonnement.packagesId,
      clientId: abonnement.clientId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const abonnement = this.createFromForm();
    if (abonnement.id !== undefined) {
      this.subscribeToSaveResponse(this.abonnementService.update(abonnement));
    } else {
      this.subscribeToSaveResponse(this.abonnementService.create(abonnement));
    }
  }

  private createFromForm(): IAbonnement {
    return {
      ...new Abonnement(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      description: this.editForm.get(['description'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value,
      lienRessourceVOD: this.editForm.get(['lienRessourceVOD'])!.value,
      lienRessourceTv: this.editForm.get(['lienRessourceTv'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      packagesId: this.editForm.get(['packagesId'])!.value,
      clientId: this.editForm.get(['clientId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAbonnement>>): void {
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
}
