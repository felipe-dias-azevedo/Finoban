import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    containerLogin: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
    },
    textHeader: {
        fontSize: 30,
        fontWeight: 'bold',
        marginBottom: 100,
    },
    forgotPassword: {
        marginTop: 15,
        alignItems: 'flex-end',
    },
    registerAccount: {
        marginTop: 10,
    },
    bold: {
        fontWeight: 'bold',
    },
    containerButton: {
        backgroundColor: 'red',
        flexDirection: 'row',
        margin: 50,
    },
    button: {
        alignItems: 'center',
        justifyContent: 'center',
        paddingVertical: 12,
        paddingHorizontal: 32,
        borderRadius: 4,
        elevation: 3,
        backgroundColor: 'black',
    },
    text: {
        fontSize: 16,
        lineHeight: 21,
        fontWeight: 'bold',
        letterSpacing: 0.25,
        color: 'white',
    },
});
