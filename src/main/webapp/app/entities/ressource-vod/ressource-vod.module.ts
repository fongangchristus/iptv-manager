import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IptvmanagerSharedModule } from 'app/shared/shared.module';
import { RessourceVODComponent } from './ressource-vod.component';
import { RessourceVODDetailComponent } from './ressource-vod-detail.component';
import { RessourceVODUpdateComponent } from './ressource-vod-update.component';
import { RessourceVODDeleteDialogComponent } from './ressource-vod-delete-dialog.component';
import { ressourceVODRoute } from './ressource-vod.route';

@NgModule({
  imports: [IptvmanagerSharedModule, RouterModule.forChild(ressourceVODRoute)],
  declarations: [RessourceVODComponent, RessourceVODDetailComponent, RessourceVODUpdateComponent, RessourceVODDeleteDialogComponent],
  entryComponents: [RessourceVODDeleteDialogComponent]
})
export class IptvmanagerRessourceVODModule {}
