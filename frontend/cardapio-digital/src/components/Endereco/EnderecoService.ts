import { Endereco } from "./EnderecoModel";
import apiInstance from "@/api/apiInstance";

class EnderecoService {
  async carregarCEP(codCep: number): Promise<Endereco> {
    const cepSomenteNumero: string = codCep.toString().replace("-", "");
    const response = await apiInstance.get(`/endereco/cep/${cepSomenteNumero}`);
    const endereco: Endereco = response.data;
    endereco.cep = codCep;
    return endereco;
  }
}

export default new EnderecoService();
