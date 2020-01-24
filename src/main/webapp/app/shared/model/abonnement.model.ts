import { Moment } from 'moment';
import { StatutAbonnement } from 'app/shared/model/enumerations/statut-abonnement.model';

export interface IAbonnement {
  id?: number;
  libelle?: string;
  description?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
  lienRessourceVOD?: string;
  lienRessourceTv?: string;
  statut?: StatutAbonnement;
  packagesId?: number;
  clientId?: number;
}

export class Abonnement implements IAbonnement {
  constructor(
    public id?: number,
    public libelle?: string,
    public description?: string,
    public dateDebut?: Moment,
    public dateFin?: Moment,
    public lienRessourceVOD?: string,
    public lienRessourceTv?: string,
    public statut?: StatutAbonnement,
    public packagesId?: number,
    public clientId?: number
  ) {}
}
