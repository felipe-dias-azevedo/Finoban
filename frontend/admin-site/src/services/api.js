import axios from 'axios';

const api = axios.create({
    baseURL: 'http://54.226.124.160:8080/api-finoban'
})

export default api;
