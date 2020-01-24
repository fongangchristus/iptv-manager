import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { PackagesDetailComponent } from 'app/entities/packages/packages-detail.component';
import { Packages } from 'app/shared/model/packages.model';

describe('Component Tests', () => {
  describe('Packages Management Detail Component', () => {
    let comp: PackagesDetailComponent;
    let fixture: ComponentFixture<PackagesDetailComponent>;
    const route = ({ data: of({ packages: new Packages(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [PackagesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PackagesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PackagesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load packages on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.packages).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
