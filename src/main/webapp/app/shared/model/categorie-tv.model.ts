export interface ICategorieTV {
  id?: number;
  libele?: string;
  description?: string;
}

export class CategorieTV implements ICategorieTV {
  constructor(public id?: number, public libele?: string, public description?: string) {}
}
