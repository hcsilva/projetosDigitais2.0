import { Empresa } from "./EmpresaModel";
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
}

export default new EmpresaService();
