import { Empresa } from "../Empresa/EmpresaModel";

export interface Usuario {
     id?: string;
     login: string;
     senha: string;
     nome: string;
     empresa: Empresa;
}