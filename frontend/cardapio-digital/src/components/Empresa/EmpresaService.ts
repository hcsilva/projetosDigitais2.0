import { Empresa } from "./EmpresaModel";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8081/api";

class UpdateEmpresaService {
  salvar(empresa: Empresa): Promise<any> {
    return axios.post("/empresa", empresa, {
      headers: {
        "X-Skip-Interceptor": true,
      },
    });
  }
}

export default new UpdateEmpresaService();
