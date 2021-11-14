import moment from 'moment';

const dateHelper = (dataAnterior) => {
    
    moment.locale('pt-br');

    return moment(dataAnterior).format("DD/MM/YYYY HH:mm");
}

export default dateHelper;