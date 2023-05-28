import apiInstance from "@/api/apiInstance";
import { LinkWeb } from "./LinkWebModel";

class LinkWebService {
  async salvarLink(link: LinkWeb): Promise<LinkWeb> {
    const response = await apiInstance.post("/link", link);
    const linkSalvo: LinkWeb = response.data;

    return linkSalvo;
  }
}

export default new LinkWebService();
