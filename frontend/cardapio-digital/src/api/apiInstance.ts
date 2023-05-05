import { AxiosError, AxiosRequestConfig } from "axios";

const axios = require("axios");

axios.defaults.baseURL = "http://localhost:8081/api";

axios.interceptors.request.use(
  (config: AxiosRequestConfig) => {
    const token = localStorage.getItem("authToken");

    if (token != null) {
      if (config.headers) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

export default axios;