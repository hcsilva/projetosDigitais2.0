import { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import router from "@/router";
import store from "@/store/index";

const axios = require("axios");

axios.defaults.baseURL = "http://localhost:8081/api";

axios.interceptors.request.use(
  (config: AxiosRequestConfig) => {
    debugger
    const token = localStorage.getItem("authToken");

    if (token != null) {
      if (config.headers) {
        config.headers.Authorization = `Bearer ${token}`;
        config.headers['IdTenant'] = store.state.idTenant;
      }
    }

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  (response: AxiosResponse) => {
    return response;
  },
  (error: AxiosError) => {
    if (error.response && error.response.status === 403) {
      store.dispatch("clearToken")
      router.push("/login");
    }
    return Promise.reject(error);
  }
);

export default axios;