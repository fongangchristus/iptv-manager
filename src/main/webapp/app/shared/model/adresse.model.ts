export interface IAdresse {
  id?: number;
  pays?: string;
  region?: string;
  ville?: string;
  telephone1?: string;
  telephone2?: string;
}

export class Adresse implements IAdresse {
  constructor(
    public id?: number,
    public pays?: string,
    public region?: string,
    public ville?: string,
    public telephone1?: string,
    public telephone2?: string
  ) {}
}
