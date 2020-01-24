import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChaine } from 'app/shared/model/chaine.model';

type EntityResponseType = HttpResponse<IChaine>;
type EntityArrayResponseType = HttpResponse<IChaine[]>;

@Injectable({ providedIn: 'root' })
export class ChaineService {
  public resourceUrl = SERVER_API_URL + 'api/chaines';

  constructor(protected http: HttpClient) {}

  create(chaine: IChaine): Observable<EntityResponseType> {
    return this.http.post<IChaine>(this.resourceUrl, chaine, { observe: 'response' });
  }

  update(chaine: IChaine): Observable<EntityResponseType> {
    return this.http.put<IChaine>(this.resourceUrl, chaine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IChaine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChaine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
