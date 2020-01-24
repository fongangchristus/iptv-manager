import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { ChaineDetailComponent } from 'app/entities/chaine/chaine-detail.component';
import { Chaine } from 'app/shared/model/chaine.model';

describe('Component Tests', () => {
  describe('Chaine Management Detail Component', () => {
    let comp: ChaineDetailComponent;
    let fixture: ComponentFixture<ChaineDetailComponent>;
    const route = ({ data: of({ chaine: new Chaine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [ChaineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ChaineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChaineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load chaine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chaine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
