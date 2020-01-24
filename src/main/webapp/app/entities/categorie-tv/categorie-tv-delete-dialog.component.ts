import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieTV } from 'app/shared/model/categorie-tv.model';
import { CategorieTVService } from './categorie-tv.service';

@Component({
  templateUrl: './categorie-tv-delete-dialog.component.html'
})
export class CategorieTVDeleteDialogComponent {
  categorieTV?: ICategorieTV;

  constructor(
    protected categorieTVService: CategorieTVService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieTVService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieTVListModification');
      this.activeModal.close();
    });
  }
}
