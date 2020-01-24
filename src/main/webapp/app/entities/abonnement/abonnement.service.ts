import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAbonnement } from 'app/shared/model/abonnement.model';

type EntityResponseType = HttpResponse<IAbonnement>;
type EntityArrayResponseType = HttpResponse<IAbonnement[]>;

@Injectable({ providedIn: 'root' })
export class AbonnementService {
  public resourceUrl = SERVER_API_URL + 'api/abonnements';

  constructor(protected http: HttpClient) {}

  create(abonnement: IAbonnement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(abonnement);
    return this.http
      .post<IAbonnement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(abonnement: IAbonnement): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(abonnement);
    return this.http
      .put<IAbonnement>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAbonnement>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAbonnement[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(abonnement: IAbonnement): IAbonnement {
    const copy: IAbonnement = Object.assign({}, abonnement, {
      dateDebut: abonnement.dateDebut && abonnement.dateDebut.isValid() ? abonnement.dateDebut.format(DATE_FORMAT) : undefined,
      dateFin: abonnement.dateFin && abonnement.dateFin.isValid() ? abonnement.dateFin.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDebut = res.body.dateDebut ? moment(res.body.dateDebut) : undefined;
      res.body.dateFin = res.body.dateFin ? moment(res.body.dateFin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((abonnement: IAbonnement) => {
        abonnement.dateDebut = abonnement.dateDebut ? moment(abonnement.dateDebut) : undefined;
        abonnement.dateFin = abonnement.dateFin ? moment(abonnement.dateFin) : undefined;
      });
    }
    return res;
  }
}
