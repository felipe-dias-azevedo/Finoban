import React, { useEffect } from 'react';
import { SafeAreaView, KeyboardAvoidingView, StatusBar, Platform, TouchableWithoutFeedback, Keyboard } from 'react-native';
import estilosGlobal from  '../../estilos';

const DismissKeyboard = ({ children }) => (
    <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
    { children }
    </TouchableWithoutFeedback>
  );

export default function DefaultScreen({ children }) {
    
    useEffect(() => {
        StatusBar.setBarStyle('dark-content');
      });
    
    return <SafeAreaView style={estilosGlobal.preencher}>
    <StatusBar style="dark"/>
    <DismissKeyboard>
    <KeyboardAvoidingView 
    behavior={Platform.OS === "ios" ? "padding" : "height"}
    style={estilosGlobal.preencher}>
        {children}
        </KeyboardAvoidingView>
        </DismissKeyboard>
        </SafeAreaView>
}