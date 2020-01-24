import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieVOD } from 'app/shared/model/categorie-vod.model';
import { CategorieVODService } from './categorie-vod.service';

@Component({
  templateUrl: './categorie-vod-delete-dialog.component.html'
})
export class CategorieVODDeleteDialogComponent {
  categorieVOD?: ICategorieVOD;

  constructor(
    protected categorieVODService: CategorieVODService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieVODService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieVODListModification');
      this.activeModal.close();
    });
  }
}
