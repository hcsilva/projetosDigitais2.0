import { Empresa } from "./EmpresaModel";
import { Endereco } from "./EnderecoModel";
import apiInstance from "@/api/apiInstance";

class EmpresaService {

  atualizar(empresa: Empresa): Promise<any> {
    return apiInstance.put("/empresa/", empresa, {});
  }

  async buscar(): Promise<Empresa> {
    const response = await apiInstance.get(
      "/empresa/"
    );
    const empresa: Empresa = response.data;
    return empresa;
  }

  async carregarCEP(codCep: number): Promise<Endereco> {
    const aux: string = codCep.toString()
    const response = await apiInstance.get(
      "/endereco/cep/" + +aux.replace("-","")
    );
    const endereco: Endereco = response.data;
    endereco.cep = codCep;
    return endereco;
  }

}

export default new EmpresaService();
