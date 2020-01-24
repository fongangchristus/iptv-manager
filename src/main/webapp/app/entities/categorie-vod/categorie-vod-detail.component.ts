import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieVOD } from 'app/shared/model/categorie-vod.model';

@Component({
  selector: 'jhi-categorie-vod-detail',
  templateUrl: './categorie-vod-detail.component.html'
})
export class CategorieVODDetailComponent implements OnInit {
  categorieVOD: ICategorieVOD | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieVOD }) => {
      this.categorieVOD = categorieVOD;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
