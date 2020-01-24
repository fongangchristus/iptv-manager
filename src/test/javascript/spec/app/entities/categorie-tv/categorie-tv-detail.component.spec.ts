import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { CategorieTVDetailComponent } from 'app/entities/categorie-tv/categorie-tv-detail.component';
import { CategorieTV } from 'app/shared/model/categorie-tv.model';

describe('Component Tests', () => {
  describe('CategorieTV Management Detail Component', () => {
    let comp: CategorieTVDetailComponent;
    let fixture: ComponentFixture<CategorieTVDetailComponent>;
    const route = ({ data: of({ categorieTV: new CategorieTV(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [CategorieTVDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategorieTVDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategorieTVDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categorieTV on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categorieTV).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
