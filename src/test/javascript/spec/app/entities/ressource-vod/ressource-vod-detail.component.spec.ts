import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { RessourceVODDetailComponent } from 'app/entities/ressource-vod/ressource-vod-detail.component';
import { RessourceVOD } from 'app/shared/model/ressource-vod.model';

describe('Component Tests', () => {
  describe('RessourceVOD Management Detail Component', () => {
    let comp: RessourceVODDetailComponent;
    let fixture: ComponentFixture<RessourceVODDetailComponent>;
    const route = ({ data: of({ ressourceVOD: new RessourceVOD(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [RessourceVODDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RessourceVODDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RessourceVODDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ressourceVOD on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ressourceVOD).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
