
export interface Endereco {
  id?: string;
  cep: number;
  logradouro: string;
  numero: number;
  bairro: string;
  complemento: string;
  cidade: string;
  estado: string;
  pais: string;
  dataCriacaoRegistro?: Date;
  dataModificacaoRegistro?: Date;
}
