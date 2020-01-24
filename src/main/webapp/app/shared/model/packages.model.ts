import { IChaine } from 'app/shared/model/chaine.model';
import { IRessourceVOD } from 'app/shared/model/ressource-vod.model';

export interface IPackages {
  id?: number;
  libelle?: string;
  description?: string;
  prixtUnitaire?: number;
  pathLogo?: string;
  resume?: string;
  packagechaines?: IChaine[];
  packageVODS?: IRessourceVOD[];
}

export class Packages implements IPackages {
  constructor(
    public id?: number,
    public libelle?: string,
    public description?: string,
    public prixtUnitaire?: number,
    public pathLogo?: string,
    public resume?: string,
    public packagechaines?: IChaine[],
    public packageVODS?: IRessourceVOD[]
  ) {}
}
