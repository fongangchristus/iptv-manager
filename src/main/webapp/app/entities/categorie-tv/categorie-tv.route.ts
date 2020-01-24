import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieTV, CategorieTV } from 'app/shared/model/categorie-tv.model';
import { CategorieTVService } from './categorie-tv.service';
import { CategorieTVComponent } from './categorie-tv.component';
import { CategorieTVDetailComponent } from './categorie-tv-detail.component';
import { CategorieTVUpdateComponent } from './categorie-tv-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieTVResolve implements Resolve<ICategorieTV> {
  constructor(private service: CategorieTVService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieTV> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieTV: HttpResponse<CategorieTV>) => {
          if (categorieTV.body) {
            return of(categorieTV.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieTV());
  }
}

export const categorieTVRoute: Routes = [
  {
    path: '',
    component: CategorieTVComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'iptvmanagerApp.categorieTV.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategorieTVDetailComponent,
    resolve: {
      categorieTV: CategorieTVResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieTV.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategorieTVUpdateComponent,
    resolve: {
      categorieTV: CategorieTVResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieTV.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategorieTVUpdateComponent,
    resolve: {
      categorieTV: CategorieTVResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieTV.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
