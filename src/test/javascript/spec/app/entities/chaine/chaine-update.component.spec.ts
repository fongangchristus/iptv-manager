import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { ChaineUpdateComponent } from 'app/entities/chaine/chaine-update.component';
import { ChaineService } from 'app/entities/chaine/chaine.service';
import { Chaine } from 'app/shared/model/chaine.model';

describe('Component Tests', () => {
  describe('Chaine Management Update Component', () => {
    let comp: ChaineUpdateComponent;
    let fixture: ComponentFixture<ChaineUpdateComponent>;
    let service: ChaineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [ChaineUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ChaineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChaineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChaineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Chaine(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Chaine();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
