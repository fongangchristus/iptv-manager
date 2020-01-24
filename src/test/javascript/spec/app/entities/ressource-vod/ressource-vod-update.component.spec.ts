import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { RessourceVODUpdateComponent } from 'app/entities/ressource-vod/ressource-vod-update.component';
import { RessourceVODService } from 'app/entities/ressource-vod/ressource-vod.service';
import { RessourceVOD } from 'app/shared/model/ressource-vod.model';

describe('Component Tests', () => {
  describe('RessourceVOD Management Update Component', () => {
    let comp: RessourceVODUpdateComponent;
    let fixture: ComponentFixture<RessourceVODUpdateComponent>;
    let service: RessourceVODService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [RessourceVODUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RessourceVODUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RessourceVODUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RessourceVODService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RessourceVOD(123);
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
        const entity = new RessourceVOD();
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
