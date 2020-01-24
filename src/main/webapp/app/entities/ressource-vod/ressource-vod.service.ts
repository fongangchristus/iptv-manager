import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRessourceVOD } from 'app/shared/model/ressource-vod.model';

type EntityResponseType = HttpResponse<IRessourceVOD>;
type EntityArrayResponseType = HttpResponse<IRessourceVOD[]>;

@Injectable({ providedIn: 'root' })
export class RessourceVODService {
  public resourceUrl = SERVER_API_URL + 'api/ressource-vods';

  constructor(protected http: HttpClient) {}

  create(ressourceVOD: IRessourceVOD): Observable<EntityResponseType> {
    return this.http.post<IRessourceVOD>(this.resourceUrl, ressourceVOD, { observe: 'response' });
  }

  update(ressourceVOD: IRessourceVOD): Observable<EntityResponseType> {
    return this.http.put<IRessourceVOD>(this.resourceUrl, ressourceVOD, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRessourceVOD>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRessourceVOD[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
