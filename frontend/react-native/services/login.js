import api from './api';
import ToastUtils from '../utils/toast';

async function LoginService({ email: email, password: senha }) {

    try {
        const response = await api.post('/login', { email: email.value, senha: senha.value });
        ToastUtils({ type: 'success', text1: 'Finoban', text2: 'Bem-vindo! Você foi autenticado com sucesso!'});
        return response.data;
    } 
    catch (error) {
        ToastUtils({ type: 'error', text1: 'Finoban', text2: 'Ocorreu um erro ao realizar o seu login! Tente novamente mais tarde.'});
        return error;
};

}

export default LoginService;