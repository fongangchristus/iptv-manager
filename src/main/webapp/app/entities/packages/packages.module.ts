import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IptvmanagerSharedModule } from 'app/shared/shared.module';
import { PackagesComponent } from './packages.component';
import { PackagesDetailComponent } from './packages-detail.component';
import { PackagesUpdateComponent } from './packages-update.component';
import { PackagesDeleteDialogComponent } from './packages-delete-dialog.component';
import { packagesRoute } from './packages.route';

@NgModule({
  imports: [IptvmanagerSharedModule, RouterModule.forChild(packagesRoute)],
  declarations: [PackagesComponent, PackagesDetailComponent, PackagesUpdateComponent, PackagesDeleteDialogComponent],
  entryComponents: [PackagesDeleteDialogComponent]
})
export class IptvmanagerPackagesModule {}
