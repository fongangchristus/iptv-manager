import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChaine } from 'app/shared/model/chaine.model';
import { ChaineService } from './chaine.service';

@Component({
  templateUrl: './chaine-delete-dialog.component.html'
})
export class ChaineDeleteDialogComponent {
  chaine?: IChaine;

  constructor(protected chaineService: ChaineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chaineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('chaineListModification');
      this.activeModal.close();
    });
  }
}
