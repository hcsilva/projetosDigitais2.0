import { Empresa } from "./EmpresaModel";
import apiInstance from "@/api/apiInstance";

class EmpresaService {
  async buscar(): Promise<Empresa> {
    const response = await apiInstance.get(
      "/empresa/444e4ab6-e2fe-11ed-9720-54bf644c0de0"
    );
    const empresa: Empresa = response;
    return empresa;
  }
}

export default new EmpresaService();
