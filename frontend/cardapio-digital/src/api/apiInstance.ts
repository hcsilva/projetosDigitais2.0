import { AxiosResponse, AxiosError } from 'axios';

const axios = require('axios');

axios.defaults.baseURL = 'http://localhost:8081/api';

axios.interceptors.response.use(
    (response: AxiosResponse) => {
        const token = localStorage.getItem('authToken');

        if (token != null) {
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
        }

        return response;
    },

    (error: AxiosError) => {
        return Promise.reject(error);
    }
);

const skipInterceptorHeader = { 'X-Skip-Interceptor': false };

export default {
  get: (url: string, config?: any) =>
    axios.get(url, { ...config, headers: { ...config?.headers, ...skipInterceptorHeader } }),
  post: (url: string, data?: any, config?: any) =>
    axios.post(url, data, { ...config, headers: { ...config?.headers, ...skipInterceptorHeader } }),
  put: (url: string, data?: any, config?: any) =>
    axios.put(url, data, { ...config, headers: { ...config?.headers, ...skipInterceptorHeader } }),
  delete: (url: string, config?: any) =>
    axios.delete(url, { ...config, headers: { ...config?.headers, ...skipInterceptorHeader } }),
};