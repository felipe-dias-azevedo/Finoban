import { StyleSheet } from 'react-native';

export default StyleSheet.create({
    containerLandingPage: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center'
    },
    backgroundImage: {
        flex: 1,
        justifyContent: 'center',
    },
    textLogo: {
        fontSize: 55,
        fontWeight: 'bold',
        textAlign: 'center',
        color: '#3A5C62',
        marginBottom: 200
    },
    textHeader: {
        fontSize: 35,
        fontWeight: 'bold',
        textAlign: 'center',
        color: 'black',
    },
    textSecondary: {
        fontSize: 16,
        textAlign: 'center',
        marginTop: 10,
        color: 'black',
    },
    sectionHeader: {
        marginBottom: 200
    },
})