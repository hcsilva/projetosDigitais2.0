import { Empresa } from "./EmpresaModel";
import axios from "axios";
import apiInstance from "../../api/apiInstance"

axios.defaults.baseURL = "http://localhost:8081/api";

class UpdateEmpresaService {

  async buscar() {

    const empresaId = localStorage.getItem("empresaId");
    const response = await apiInstance.get("/empresa/$(empresaId)");
    return response.data;

  }

  salvar(empresa: Empresa): Promise<any> {
    return axios.post("/empresa", empresa, {
      headers: {
        "X-Skip-Interceptor": true,
      },
    });
  }
}

export default new UpdateEmpresaService();
