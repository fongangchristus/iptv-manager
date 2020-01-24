export interface IClient {
  id?: number;
  nom?: string;
  login?: string;
  password?: string;
  description?: string;
  adresseId?: number;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public nom?: string,
    public login?: string,
    public password?: string,
    public description?: string,
    public adresseId?: number
  ) {}
}
