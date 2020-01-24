import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { RessourceVODService } from 'app/entities/ressource-vod/ressource-vod.service';
import { IRessourceVOD, RessourceVOD } from 'app/shared/model/ressource-vod.model';

describe('Service Tests', () => {
  describe('RessourceVOD Service', () => {
    let injector: TestBed;
    let service: RessourceVODService;
    let httpMock: HttpTestingController;
    let elemDefault: IRessourceVOD;
    let expectedResult: IRessourceVOD | IRessourceVOD[] | boolean | null;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RessourceVODService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RessourceVOD(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RessourceVOD', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new RessourceVOD())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RessourceVOD', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            lienRessourceVOD: 'BBBBBB',
            pathLogo: 'BBBBBB',
            code: 'BBBBBB',
            resume: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RessourceVOD', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            lienRessourceVOD: 'BBBBBB',
            pathLogo: 'BBBBBB',
            code: 'BBBBBB',
            resume: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RessourceVOD', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
