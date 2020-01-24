import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IptvmanagerSharedModule } from 'app/shared/shared.module';
import { CategorieTVComponent } from './categorie-tv.component';
import { CategorieTVDetailComponent } from './categorie-tv-detail.component';
import { CategorieTVUpdateComponent } from './categorie-tv-update.component';
import { CategorieTVDeleteDialogComponent } from './categorie-tv-delete-dialog.component';
import { categorieTVRoute } from './categorie-tv.route';

@NgModule({
  imports: [IptvmanagerSharedModule, RouterModule.forChild(categorieTVRoute)],
  declarations: [CategorieTVComponent, CategorieTVDetailComponent, CategorieTVUpdateComponent, CategorieTVDeleteDialogComponent],
  entryComponents: [CategorieTVDeleteDialogComponent]
})
export class IptvmanagerCategorieTVModule {}
