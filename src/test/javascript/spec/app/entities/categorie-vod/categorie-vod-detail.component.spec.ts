import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { CategorieVODDetailComponent } from 'app/entities/categorie-vod/categorie-vod-detail.component';
import { CategorieVOD } from 'app/shared/model/categorie-vod.model';

describe('Component Tests', () => {
  describe('CategorieVOD Management Detail Component', () => {
    let comp: CategorieVODDetailComponent;
    let fixture: ComponentFixture<CategorieVODDetailComponent>;
    const route = ({ data: of({ categorieVOD: new CategorieVOD(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [CategorieVODDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategorieVODDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategorieVODDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categorieVOD on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categorieVOD).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
