import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieTV } from 'app/shared/model/categorie-tv.model';

@Component({
  selector: 'jhi-categorie-tv-detail',
  templateUrl: './categorie-tv-detail.component.html'
})
export class CategorieTVDetailComponent implements OnInit {
  categorieTV: ICategorieTV | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieTV }) => {
      this.categorieTV = categorieTV;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
