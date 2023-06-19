import apiInstance from "@/api/apiInstance";
import { LinkWeb } from "./LinkWebModel";
import PageResult from "../PageResult/PageResult";

class LinkWebService {

  async salvarLink(link: LinkWeb): Promise<LinkWeb> {
    const response = await apiInstance.post("/link", link);
    const linkSalvo: LinkWeb = response.data;

    return linkSalvo;
  }

  async atualizarLink(linkId: string, link: LinkWeb): Promise<LinkWeb> {
    const response = await apiInstance.put(`/link/${linkId}`, link);
    const linkAtualizado: LinkWeb = response.data;
  
    return linkAtualizado;
  }

  async deletarLink(linkId: string): Promise<void> {
    await apiInstance.delete(`/link/${linkId}`);
  }

  async buscarTodosLinks(): Promise<PageResult> {
    const response = await apiInstance.get("/link");
    const pageResult: PageResult = response.data;

    return pageResult;
  }

  async buscarLinkPorId(linkId: string): Promise<LinkWeb> {
    const response = await apiInstance.get(`/link/${linkId}`);
    const link: LinkWeb = response.data;

    return link;
  }
}

export default new LinkWebService();
