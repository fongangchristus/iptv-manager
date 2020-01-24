import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieTV } from 'app/shared/model/categorie-tv.model';

type EntityResponseType = HttpResponse<ICategorieTV>;
type EntityArrayResponseType = HttpResponse<ICategorieTV[]>;

@Injectable({ providedIn: 'root' })
export class CategorieTVService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-tvs';

  constructor(protected http: HttpClient) {}

  create(categorieTV: ICategorieTV): Observable<EntityResponseType> {
    return this.http.post<ICategorieTV>(this.resourceUrl, categorieTV, { observe: 'response' });
  }

  update(categorieTV: ICategorieTV): Observable<EntityResponseType> {
    return this.http.put<ICategorieTV>(this.resourceUrl, categorieTV, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieTV>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieTV[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
