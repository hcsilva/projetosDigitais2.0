import axios from "axios";
import router from "@/router";
import store from "@/store/index";

const instance = axios.create({
  baseURL: "http://localhost:8081/api"
});

instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("authToken");
    const idTenant = store.getters.getIdTenant;

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      config.headers["IdTenant"] = idTenant;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 403) {
      store.dispatch("clearToken");
      router.push("/login");
    }
    return Promise.reject(error);
  }
);

export default instance;