import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IptvmanagerSharedModule } from 'app/shared/shared.module';
import { ChaineComponent } from './chaine.component';
import { ChaineDetailComponent } from './chaine-detail.component';
import { ChaineUpdateComponent } from './chaine-update.component';
import { ChaineDeleteDialogComponent } from './chaine-delete-dialog.component';
import { chaineRoute } from './chaine.route';

@NgModule({
  imports: [IptvmanagerSharedModule, RouterModule.forChild(chaineRoute)],
  declarations: [ChaineComponent, ChaineDetailComponent, ChaineUpdateComponent, ChaineDeleteDialogComponent],
  entryComponents: [ChaineDeleteDialogComponent]
})
export class IptvmanagerChaineModule {}
