import apiInstance from "@/api/apiInstance";

class LinkWebService {
  getById(): Promise<any> {
    return apiInstance.get("/link/1");
  }
}

export default new LinkWebService();
