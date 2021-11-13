
const dateHelper = (dataAnterior) => {

    let dataConvertida = dataAnterior.replace('T', ' ').replace('Z', '');
    
    for (let i = 0; i < dataConvertida.length; i++) {
        dataConvertida.replace('-', '/');
    }

    return dataConvertida;
}

export default dateHelper;