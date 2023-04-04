import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081/api';


axios.interceptors.request.use(function (config) {
    console.log("passou aqui, pelo interceptor");
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    console.log("erro no token");
    return Promise.reject(error);
});

export default axios;
