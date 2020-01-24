import { IPackages } from 'app/shared/model/packages.model';

export interface IRessourceVOD {
  id?: number;
  libelle?: string;
  description?: string;
  lienRessourceVOD?: string;
  pathLogo?: string;
  code?: string;
  resume?: string;
  categorieVODId?: number;
  packages?: IPackages[];
}

export class RessourceVOD implements IRessourceVOD {
  constructor(
    public id?: number,
    public libelle?: string,
    public description?: string,
    public lienRessourceVOD?: string,
    public pathLogo?: string,
    public code?: string,
    public resume?: string,
    public categorieVODId?: number,
    public packages?: IPackages[]
  ) {}
}
