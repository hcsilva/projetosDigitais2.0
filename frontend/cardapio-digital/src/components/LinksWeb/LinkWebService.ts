import apiInstance from "@/api/apiInstance";
import { LinkWeb } from "./LinkWebModel";

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

  async buscarTodosLinks(): Promise<LinkWeb[]> {
    const response = await apiInstance.get("/link");
    const links: LinkWeb[] = response.data;

    return links;
  }

  async buscarLinkPorId(linkId: string): Promise<LinkWeb> {
    const response = await apiInstance.get(`/link/${linkId}`);
    const link: LinkWeb = response.data;

    return link;
  }
}

export default new LinkWebService();
