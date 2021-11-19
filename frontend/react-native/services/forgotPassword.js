import api from './api';
import ToastUtils from '../utils/toast';

async function ForgotPasswordService({ email }) {
    
    try {
        const response = await api.post('/redefinir-senha', { email: email.value })
        ToastUtils({ type: 'success', text1: 'Finoban',
        text2: 'Você vai receber um e-mail com um link para redefinir a sua senha.'});
        return response.data;
    } 
    catch (error) {
        ToastUtils({ type: 'error', text1: 'Finoban', 
        text2: 'Ocorreu um erro ao redefinir a senha. Tente novamente mais tarde'});
        return error;
};

}

export default ForgotPasswordService;