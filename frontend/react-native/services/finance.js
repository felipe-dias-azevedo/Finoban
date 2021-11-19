import api from './api';
import Toast from 'react-native-toast-message';

async function FinanceService({ cpf, renda, valorImovel, tempoFinanciamento }) {

    const req = {
        cpf: cpf.value,
        renda: renda.value,
        valorImovel: valorImovel.value,
        tempoFinanciamento: tempoFinanciamento.value
    };

    try {
        const response = await api.post('/financiamento', req);
        response.data.valorImovel = valorImovel.value;
        Toast.show({
            type: 'success',
            text1: 'Sucesso',
            text2: 'Sua simulação de financiamento foi realizada com sucesso!',
            topOffset: 50,
        });
        return response.data;
    }
    catch (error) {
        Toast.show({
            type: 'error',
            text1: 'Erro',
            text2: 'Ocorreu um erro ao realizar a sua simulação. Tente novamente mais tarde.',
            topOffset: 50,
        });
        return error;
    };

}

export default FinanceService