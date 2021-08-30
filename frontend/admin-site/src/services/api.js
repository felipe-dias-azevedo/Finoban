import axios from 'axios';

const api = axios.create({
    baseURL: 'http://3.95.24.155:8080/api-finoban'
})

export default api;
