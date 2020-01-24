import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRessourceVOD, RessourceVOD } from 'app/shared/model/ressource-vod.model';
import { RessourceVODService } from './ressource-vod.service';
import { RessourceVODComponent } from './ressource-vod.component';
import { RessourceVODDetailComponent } from './ressource-vod-detail.component';
import { RessourceVODUpdateComponent } from './ressource-vod-update.component';

@Injectable({ providedIn: 'root' })
export class RessourceVODResolve implements Resolve<IRessourceVOD> {
  constructor(private service: RessourceVODService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRessourceVOD> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ressourceVOD: HttpResponse<RessourceVOD>) => {
          if (ressourceVOD.body) {
            return of(ressourceVOD.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RessourceVOD());
  }
}

export const ressourceVODRoute: Routes = [
  {
    path: '',
    component: RessourceVODComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'iptvmanagerApp.ressourceVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RessourceVODDetailComponent,
    resolve: {
      ressourceVOD: RessourceVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.ressourceVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RessourceVODUpdateComponent,
    resolve: {
      ressourceVOD: RessourceVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.ressourceVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RessourceVODUpdateComponent,
    resolve: {
      ressourceVOD: RessourceVODResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.ressourceVOD.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
