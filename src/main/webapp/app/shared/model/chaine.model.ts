import { IPackages } from 'app/shared/model/packages.model';

export interface IChaine {
  id?: number;
  libelle?: string;
  description?: string;
  lien?: string;
  code?: string;
  pathLogo?: string;
  resume?: string;
  categorieTId?: number;
  packages?: IPackages[];
}

export class Chaine implements IChaine {
  constructor(
    public id?: number,
    public libelle?: string,
    public description?: string,
    public lien?: string,
    public code?: string,
    public pathLogo?: string,
    public resume?: string,
    public categorieTId?: number,
    public packages?: IPackages[]
  ) {}
}
