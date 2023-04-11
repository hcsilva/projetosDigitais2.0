import { AxiosResponse, AxiosError } from 'axios';

const axios = require('axios');

axios.defaults.baseURL = 'http://localhost:8081/api';

// axios.interceptors.response.use(
//     (response: AxiosResponse) => {
//         return response;
//     },
//     (error: AxiosError) => {
//         if (error.response?.status === 403) {
//           console.log("Erro Ao logar")
//         }
//         return Promise.reject(error);
//     }
// );

export default axios;