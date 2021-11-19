import { StyleSheet } from 'react-native';
import { cores } from '../../estilos';

export default StyleSheet.create({
    containerLogin: {
        flex: 1,
        flexDirection: 'column',
        margin: 20,
        // justifyContent: 'center',
        // alignItems: 'center',
    },
    textHeader: {
        fontSize: 30,
        fontWeight: 'bold',
        textAlign: 'center',
        marginTop: 100,
        marginBottom: 60
    },
    textBank: {
        fontSize: 20,
    },
    bold: {
        fontWeight: 'bold',
    },
    text: {
        fontSize: 20,
        lineHeight: 21,
        letterSpacing: 0.25,
        color: 'black',
    },
    btnTouch: {
        backgroundColor: '#154c79',
        marginTop: 15,
    },
    textBtnTouch: {
        fontSize: 20,
        color: 'white',
        padding: 10
    },
    textDetails: {
        fontSize: 18,
        color: "#154c79",
        fontWeight: '500',
        marginTop: 10
    },
    textTax: {
        marginTop: 10,
        fontSize: 18
    }
});
