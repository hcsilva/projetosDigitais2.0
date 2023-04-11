import apiInstance from "../../api/apiInstance"

class LoginService {

    getLogin(login: string, senha: string): Promise<any> {
        return apiInstance.post("/login", {
            login: login,
            senha: senha
        });
    }

}

export default new LoginService();