import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieVOD, CategorieVOD } from 'app/shared/model/categorie-vod.model';
import { CategorieVODService } from './categorie-vod.service';
import { CategorieVODComponent } from './categorie-vod.component';
import { CategorieVODDetailComponent } from './categorie-vod-detail.component';
import { CategorieVODUpdateComponent } from './categorie-vod-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieVODResolve implements Resolve<ICategorieVOD> {
  constructor(private service: CategorieVODService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieVOD> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieVOD: HttpResponse<CategorieVOD>) => {
          if (categorieVOD.body) {
            return of(categorieVOD.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieVOD());
  }
}

export const categorieVODRoute: Routes = [
  {
    path: '',
    component: CategorieVODComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'iptvmanagerApp.categorieVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategorieVODDetailComponent,
    resolve: {
      categorieVOD: CategorieVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategorieVODUpdateComponent,
    resolve: {
      categorieVOD: CategorieVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategorieVODUpdateComponent,
    resolve: {
      categorieVOD: CategorieVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.categorieVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
