import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieVOD } from 'app/shared/model/categorie-vod.model';

type EntityResponseType = HttpResponse<ICategorieVOD>;
type EntityArrayResponseType = HttpResponse<ICategorieVOD[]>;

@Injectable({ providedIn: 'root' })
export class CategorieVODService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-vods';

  constructor(protected http: HttpClient) {}

  create(categorieVOD: ICategorieVOD): Observable<EntityResponseType> {
    return this.http.post<ICategorieVOD>(this.resourceUrl, categorieVOD, { observe: 'response' });
  }

  update(categorieVOD: ICategorieVOD): Observable<EntityResponseType> {
    return this.http.put<ICategorieVOD>(this.resourceUrl, categorieVOD, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieVOD>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieVOD[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
