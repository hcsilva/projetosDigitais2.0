import { Usuario } from "./UsuarioModel";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8081/api";

class LoginService {
  salvar(usuario: Usuario): Promise<any> {
    return axios.post("/usuario", usuario, {
      headers: {
        "X-Skip-Interceptor": true,
      },
    });
  }

  getLogin(login: string, senha: string): Promise<any> {
    return axios.post(
      "/login",
      {
        login: login,
        senha: senha,
      },
      {
        headers: {
          "X-Skip-Interceptor": true,
        },
      }
    );
  }
}

export default new LoginService();
