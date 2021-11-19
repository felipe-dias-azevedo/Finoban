import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';

function Button(props) {
    return <>
        <TouchableOpacity
            style={styles.btnDefault}
            onPress={props.onPress}
            disabled={props.disabled}>
            
            <Text style={styles.textBtnDefault}>{props.text}</Text>
        </TouchableOpacity>
    </>
}

const styles = StyleSheet.create({
    btnDefault: {
        width: "90%",
        backgroundColor: "#3A5C62",
        borderRadius: 20,
        height: 50,
        alignItems: "center",
        justifyContent: "center",
        position: "absolute",
        bottom: 15
    },
    textBtnDefault: {
        fontSize: 16,
        color: 'white',
        fontWeight: 'bold',    
    },
    
});

export default Button;
