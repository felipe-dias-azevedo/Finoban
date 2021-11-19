import api from '../services/api';
import ToastUtils from '../utils/toast';

async function RegisterService({ name, cpf, email, password, cep, number, neighborhood, dateBirth}) {

    const req = {
        nome: name,
        cpf: cpf,
        email: email,
        senha: password,
        cep: cep,
        numero: number,
        bairro: neighborhood,
        dataNasc: dateBirth,
    }
    
    try {
        const response = await api.post('/financiamento', req);
        return response.data;
    } 
    catch (error) {
        return error;
    }
}

export default RegisterService;