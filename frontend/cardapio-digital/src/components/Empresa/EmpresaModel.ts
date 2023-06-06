import { Endereco } from "../Empresa/EnderecoModel";
import { HorarioFuncionamento } from "../Empresa/HorarioFuncionamentoModel";

export interface Empresa {
  id?: string;
  nomeEstabelecimento: string;
  cnpj?: number;
  logo?: string;
  imagemCapa?: string;
  descricao?: string;
  site?: string;
  instagram?: string;
  facebook?: string;
  whatsapp?: string;
  telefoneContato: string;
  idioma?: string;
  fusoHorario?: string;
  email: string;
  dataCriacaoRegistro?: Date;
  dataModificacaoRegistro?: Date;
  endereco?: Endereco;
  horario?: HorarioFuncionamento;
}
