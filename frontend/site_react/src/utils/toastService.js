import { toast } from 'react-toastify';

function configurarToast(resposta, mensagem) {
    switch (resposta) {
        case resposta.SUCESSO:
            return toast.success(mensagem);
        case resposta.ERROR:
            return toast.error(mensagem);
        default:
            return;
    }
}

export default configurarToast;