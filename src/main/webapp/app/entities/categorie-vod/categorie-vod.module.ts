import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IptvmanagerSharedModule } from 'app/shared/shared.module';
import { CategorieVODComponent } from './categorie-vod.component';
import { CategorieVODDetailComponent } from './categorie-vod-detail.component';
import { CategorieVODUpdateComponent } from './categorie-vod-update.component';
import { CategorieVODDeleteDialogComponent } from './categorie-vod-delete-dialog.component';
import { categorieVODRoute } from './categorie-vod.route';

@NgModule({
  imports: [IptvmanagerSharedModule, RouterModule.forChild(categorieVODRoute)],
  declarations: [CategorieVODComponent, CategorieVODDetailComponent, CategorieVODUpdateComponent, CategorieVODDeleteDialogComponent],
  entryComponents: [CategorieVODDeleteDialogComponent]
})
export class IptvmanagerCategorieVODModule {}
