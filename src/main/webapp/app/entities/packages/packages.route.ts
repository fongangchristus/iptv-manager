import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPackages, Packages } from 'app/shared/model/packages.model';
import { PackagesService } from './packages.service';
import { PackagesComponent } from './packages.component';
import { PackagesDetailComponent } from './packages-detail.component';
import { PackagesUpdateComponent } from './packages-update.component';

@Injectable({ providedIn: 'root' })
export class PackagesResolve implements Resolve<IPackages> {
  constructor(private service: PackagesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPackages> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((packages: HttpResponse<Packages>) => {
          if (packages.body) {
            return of(packages.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Packages());
  }
}

export const packagesRoute: Routes = [
  {
    path: '',
    component: PackagesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'iptvmanagerApp.packages.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PackagesDetailComponent,
    resolve: {
      packages: PackagesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.packages.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PackagesUpdateComponent,
    resolve: {
      packages: PackagesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.packages.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PackagesUpdateComponent,
    resolve: {
      packages: PackagesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'iptvmanagerApp.packages.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
