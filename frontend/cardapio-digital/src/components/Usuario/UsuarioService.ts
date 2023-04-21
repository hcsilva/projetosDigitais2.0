import { Usuario } from "./UsuarioModel";
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8081/api';

class LoginService {
  salvar(usuario: Usuario): Promise<any> {
    return axios.post("/usuario", usuario);
  }

  getLogin(login: string, senha: string): Promise<any> {
    return axios.post("/login", {
      login: login,
      senha: senha,
    });
  }
}

export default new LoginService();
