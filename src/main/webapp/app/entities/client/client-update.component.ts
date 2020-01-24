import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IAdresse } from 'app/shared/model/adresse.model';
import { AdresseService } from 'app/entities/adresse/adresse.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html'
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;

  adresses: IAdresse[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    login: [],
    password: [null, [Validators.required]],
    description: [],
    adresseId: []
  });

  constructor(
    protected clientService: ClientService,
    protected adresseService: AdresseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);

      this.adresseService
        .query({ filter: 'client-is-null' })
        .pipe(
          map((res: HttpResponse<IAdresse[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IAdresse[]) => {
          if (!client.adresseId) {
            this.adresses = resBody;
          } else {
            this.adresseService
              .find(client.adresseId)
              .pipe(
                map((subRes: HttpResponse<IAdresse>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAdresse[]) => {
                this.adresses = concatRes;
              });
          }
        });
    });
  }

  updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      nom: client.nom,
      login: client.login,
      password: client.password,
      description: client.description,
      adresseId: client.adresseId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      login: this.editForm.get(['login'])!.value,
      password: this.editForm.get(['password'])!.value,
      description: this.editForm.get(['description'])!.value,
      adresseId: this.editForm.get(['adresseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  trackById(index: number, item: IAdresse): any {
    return item.id;
  }
}
