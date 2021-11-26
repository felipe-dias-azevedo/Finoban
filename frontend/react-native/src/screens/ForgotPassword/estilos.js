import React from 'react';
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
    input: {
        marginTop: 15,
        width: '75%',
        height: 50,
        backgroundColor: 'white',
        borderRadius: 5,
        borderWidth: 1,
        padding: 15
    },
    forgotPassword: {
        marginTop: 15,
        textAlign: 'right',
    },
    btnLogin: {
        width:"80%",
        backgroundColor:"#3A5C62",
        borderRadius:10,
        height:50,
        alignItems:"center",
        justifyContent:"center",
        marginTop:40,
        marginBottom:10
    },
    textLogin: {
        fontSize: 16,
        color: 'white',
        fontWeight: 'bold',
    },
    registerAccount: {
        marginTop: 10,
    },
    backToLogin: {
        flexGrow: 1,
        alignItems: 'flex-end',
    }
})