import React, { useState } from 'react';
import { Text, View, TouchableOpacity } from 'react-native';
import TextInput from '../../components/TextInput';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from  '../../estilos';
import estilos from './estilos';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import ForgotPasswordService from '../../../services/forgotPassword';
import { emailValidator } from '../../../utils/validation';

const Stack = createNativeStackNavigator();

export default function Login({ navigation }) {
    const [email, setEmail] = useState({ value: '', error: '' });
 
const submitForgotPassword = () => {
    const emailError = emailValidator(email.value);

    if (emailError) {
        setEmail({ ...email, error: emailError });
        return
    }

    ForgotPasswordService({ email: email });

};

    return <DefaultScreen style={estilosGlobal.preencher}>
        <View style={estilos.containerLogin}>
        <Text style={estilos.textHeader}>Esqueci minha senha</Text>
        <TextInput
        label="Email"
        returnKeyType="next"
        value={email.value}
        onChangeText={text => setEmail({ value: text, error: '' })}
        error={!!email.error}
        errorText={email.error}
        autoCapitalize="none"
        autoCompleteType="email"
        textContentType="emailAddress"
        keyboardType="email-address"
      />
      <Text style={estilos.forgotPassword}>Você vai receber um e-mail para redefinir a sua senha.</Text>
      <TouchableOpacity
            onPress={submitForgotPassword}
            style={estilos.btnLogin}>
          <Text style={estilos.textLogin}>Redefinir senha</Text>
      </TouchableOpacity>
      <Text 
        style={estilos.registerAccount}
        onPress={() => navigation.navigate('Login')}>
          <Text style={estilos.backToLogin}>Voltar para o login</Text>
          </Text>
      </View>
    </DefaultScreen>

}