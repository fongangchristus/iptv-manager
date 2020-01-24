import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AbonnementService } from 'app/entities/abonnement/abonnement.service';
import { IAbonnement, Abonnement } from 'app/shared/model/abonnement.model';
import { StatutAbonnement } from 'app/shared/model/enumerations/statut-abonnement.model';

describe('Service Tests', () => {
  describe('Abonnement Service', () => {
    let injector: TestBed;
    let service: AbonnementService;
    let httpMock: HttpTestingController;
    let elemDefault: IAbonnement;
    let expectedResult: IAbonnement | IAbonnement[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AbonnementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Abonnement(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', StatutAbonnement.ACTIVE);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Abonnement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate
          },
          returnedFromService
        );
        service
          .create(new Abonnement())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Abonnement', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            lienRessourceVOD: 'BBBBBB',
            lienRessourceTv: 'BBBBBB',
            statut: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Abonnement', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            description: 'BBBBBB',
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            lienRessourceVOD: 'BBBBBB',
            lienRessourceTv: 'BBBBBB',
            statut: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate
          },
          returnedFromService
        );
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

      it('should delete a Abonnement', () => {
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
