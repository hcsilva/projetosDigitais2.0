import router from '@/router';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081/api';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');


axios.interceptors.request.use(function (config) {
    console.log("passou aqui, pelo interceptor");
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    console.log("erro no token");
    router.push({ path: "/" });
    return Promise.reject(error);
});

export default axios;
