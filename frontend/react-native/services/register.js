import api from '../services/api';
import ToastUtils from '../utils/toast';

async function RegisterService({ name, cpf, email, password, cep, number, neighborhood }) {
    const req = {
        nome: name.value,
        cpf: cpf.value,
        email: email.value,
        senha: password.value,
        cep: cep,
        numero: number,
        bairro: neighborhood,
    }

    try {
        const response = await api.post('/cadastro', req);
        ToastUtils({
            type: 'success', text1: 'Finoban',
            text2: 'Cadastro realizado com sucesso.'
        });
        return response.data;
    }
    catch (error) {
        ToastUtils({ type: 'error', text1: 'Finoban', text2: 'Ocorreu um erro ao realizar o seu cadastro! Tente novamente mais tarde.'});
        return error;
    }
}

export default RegisterService;