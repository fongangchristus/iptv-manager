import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChaine, Chaine } from 'app/shared/model/chaine.model';
import { ChaineService } from './chaine.service';
import { ChaineComponent } from './chaine.component';
import { ChaineDetailComponent } from './chaine-detail.component';
import { ChaineUpdateComponent } from './chaine-update.component';

@Injectable({ providedIn: 'root' })
export class ChaineResolve implements Resolve<IChaine> {
  constructor(private service: ChaineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChaine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((chaine: HttpResponse<Chaine>) => {
          if (chaine.body) {
            return of(chaine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Chaine());
  }
}

export const chaineRoute: Routes = [
  {
    path: '',
    component: ChaineComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'iptvmanagerApp.chaine.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ChaineDetailComponent,
    resolve: {
      chaine: ChaineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.chaine.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ChaineUpdateComponent,
    resolve: {
      chaine: ChaineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.chaine.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ChaineUpdateComponent,
    resolve: {
      chaine: ChaineResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.chaine.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
