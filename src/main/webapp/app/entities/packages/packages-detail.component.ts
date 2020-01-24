import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPackages } from 'app/shared/model/packages.model';

@Component({
  selector: 'jhi-packages-detail',
  templateUrl: './packages-detail.component.html'
})
export class PackagesDetailComponent implements OnInit {
  packages: IPackages | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ packages }) => {
      this.packages = packages;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
