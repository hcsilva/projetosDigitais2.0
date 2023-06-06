import { Endereco } from "../Endereco/EnderecoModel";
import { HorarioFuncionamento } from "../HorarioFuncionamento/HorarioFuncionamentoModel";

export interface Empresa {
  id?: string;
  nomeEstabelecimento: string;
  cnpj: number;
  logo: string;
  imagemCapa: string;
  descricao?: string;
  site: string;
  instagram: string;
  facebook: string;
  whatsapp: string;
  telefoneContato: string;
  idioma: string;
  fusoHorario: string;
  email: string;
  dataCriacaoRegistro?: Date;
  dataModificacaoRegistro?: Date;
  endereco?: Endereco;
  horario: HorarioFuncionamento;
}
