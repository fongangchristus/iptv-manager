import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { CategorieVODUpdateComponent } from 'app/entities/categorie-vod/categorie-vod-update.component';
import { CategorieVODService } from 'app/entities/categorie-vod/categorie-vod.service';
import { CategorieVOD } from 'app/shared/model/categorie-vod.model';

describe('Component Tests', () => {
  describe('CategorieVOD Management Update Component', () => {
    let comp: CategorieVODUpdateComponent;
    let fixture: ComponentFixture<CategorieVODUpdateComponent>;
    let service: CategorieVODService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [CategorieVODUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategorieVODUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieVODUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieVODService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieVOD(123);
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
        const entity = new CategorieVOD();
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
