import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPackages } from 'app/shared/model/packages.model';
import { PackagesService } from './packages.service';

@Component({
  templateUrl: './packages-delete-dialog.component.html'
})
export class PackagesDeleteDialogComponent {
  packages?: IPackages;

  constructor(protected packagesService: PackagesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.packagesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('packagesListModification');
      this.activeModal.close();
    });
  }
}
