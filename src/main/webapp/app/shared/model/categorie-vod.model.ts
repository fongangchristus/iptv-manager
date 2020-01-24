export interface ICategorieVOD {
  id?: number;
  libele?: string;
  description?: string;
}

export class CategorieVOD implements ICategorieVOD {
  constructor(public id?: number, public libele?: string, public description?: string) {}
}
