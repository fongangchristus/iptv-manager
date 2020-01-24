import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRessourceVOD } from 'app/shared/model/ressource-vod.model';

@Component({
  selector: 'jhi-ressource-vod-detail',
  templateUrl: './ressource-vod-detail.component.html'
})
export class RessourceVODDetailComponent implements OnInit {
  ressourceVOD: IRessourceVOD | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ressourceVOD }) => {
      this.ressourceVOD = ressourceVOD;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
