import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRessourceVOD } from 'app/shared/model/ressource-vod.model';
import { RessourceVODService } from './ressource-vod.service';

@Component({
  templateUrl: './ressource-vod-delete-dialog.component.html'
})
export class RessourceVODDeleteDialogComponent {
  ressourceVOD?: IRessourceVOD;

  constructor(
    protected ressourceVODService: RessourceVODService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ressourceVODService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ressourceVODListModification');
      this.activeModal.close();
    });
  }
}
