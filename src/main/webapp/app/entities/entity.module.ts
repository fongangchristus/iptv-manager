import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'categorie-tv',
        loadChildren: () => import('./categorie-tv/categorie-tv.module').then(m => m.IptvmanagerCategorieTVModule)
      },
      {
        path: 'categorie-vod',
        loadChildren: () => import('./categorie-vod/categorie-vod.module').then(m => m.IptvmanagerCategorieVODModule)
      },
      {
        path: 'chaine',
        loadChildren: () => import('./chaine/chaine.module').then(m => m.IptvmanagerChaineModule)
      },
      {
        path: 'ressource-vod',
        loadChildren: () => import('./ressource-vod/ressource-vod.module').then(m => m.IptvmanagerRessourceVODModule)
      },
      {
        path: 'packages',
        loadChildren: () => import('./packages/packages.module').then(m => m.IptvmanagerPackagesModule)
      },
      {
        path: 'abonnement',
        loadChildren: () => import('./abonnement/abonnement.module').then(m => m.IptvmanagerAbonnementModule)
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.IptvmanagerClientModule)
      },
      {
        path: 'adresse',
        loadChildren: () => import('./adresse/adresse.module').then(m => m.IptvmanagerAdresseModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class IptvmanagerEntityModule {}
