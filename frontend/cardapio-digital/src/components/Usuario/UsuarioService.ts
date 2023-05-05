import { Usuario } from "./UsuarioModel";
import apiInstance from "@/api/apiInstance";

class LoginService {
  salvar(usuario: Usuario): Promise<any> {
    return apiInstance.post("/usuario", usuario, {});
  }

  getLogin(login: string, senha: string): Promise<any> {
    return apiInstance.post("/login", {
      login: login,
      senha: senha,
    });
  }
}

export default new LoginService();