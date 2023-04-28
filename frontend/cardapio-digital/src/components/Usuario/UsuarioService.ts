import { Usuario } from "./UsuarioModel";
import apiInstance from "@/api/apiInstance";

class LoginService {
  salvar(usuario: Usuario): Promise<any> {
    return apiInstance.post("/usuario", usuario, {
      headers: {
        "X-Skip-Interceptor": true,
      },
    });

  }

  getLogin(login: string, senha: string): Promise<any> {
    return apiInstance.post(
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
