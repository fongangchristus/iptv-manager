import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IptvmanagerTestModule } from '../../../test.module';
import { CategorieTVUpdateComponent } from 'app/entities/categorie-tv/categorie-tv-update.component';
import { CategorieTVService } from 'app/entities/categorie-tv/categorie-tv.service';
import { CategorieTV } from 'app/shared/model/categorie-tv.model';

describe('Component Tests', () => {
  describe('CategorieTV Management Update Component', () => {
    let comp: CategorieTVUpdateComponent;
    let fixture: ComponentFixture<CategorieTVUpdateComponent>;
    let service: CategorieTVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IptvmanagerTestModule],
        declarations: [CategorieTVUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategorieTVUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieTVUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieTVService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieTV(123);
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
        const entity = new CategorieTV();
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
