import { Empresa } from "./EmpresaModel";
import { Endereco } from "@/components/Empresa/EnderecoModel";
import apiInstance from "@/api/apiInstance";

class EmpresaService {

  // EMPRESA

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


  //ENDEREÇO

  async carregarCEP(codCep: number): Promise<Endereco> {
    const aux: string = codCep.toString()
    const response = await apiInstance.get(
      "/endereco/cep/" + +aux.replace("-","")
    );
    const endereco: Endereco = response.data;
    endereco.cep = codCep;
    return endereco;
  }

  atualizarEndereco(endereco: Endereco): Promise<any> {
    let aux;

    if(endereco.dataCriacaoRegistro != null){
      alert("put");
      aux = apiInstance.put("/endereco", endereco, {});
    } else {
      alert("post");
      aux = apiInstance.post("/endereco", endereco, {});
    }

    return aux;
  }


}

export default new EmpresaService();
