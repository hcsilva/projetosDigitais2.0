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



export default axios;