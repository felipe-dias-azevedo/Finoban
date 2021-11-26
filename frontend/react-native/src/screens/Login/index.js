import React, { useState, useEffect } from 'react';
import { Text, View, Keyboard, TouchableWithoutFeedback } from 'react-native';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from '../../estilos';
import estilos from './estilos';
import Button from './../../components/Button';
import LoginService from '../../../services/login';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { emailValidator, passwordValidator } from '../../../utils/validation';
import TextInput from '../../components/TextInput';

const Stack = createNativeStackNavigator();

export default function Login({ route, navigation }) {
  const [email, setEmail] = useState({ value: '', error: '' });
  const [password, setPassword] = useState({ value: '', error: '' })

  const submitLogin = async () => {
    const emailError = emailValidator(email.value);
    const passwordError = passwordValidator(password.value);

    if (emailError || passwordError) {
      setEmail({ ...email, error: emailError });
      setPassword({ ...password, error: passwordError });
      return;
    }

    try {
      const response = await LoginService({ email: email, password: password });
      navigation.navigate("Dashboard", route.params);
    } catch (error) {
      console.log(error);
    }
  };
  return <DefaultScreen style={estilosGlobal.preencher}>
    <View style={estilos.containerLogin}>
      <Text style={estilos.textHeader}>Realize o seu login</Text>
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

      <TextInput
        label="Senha"
        returnKeyType="done"
        value={password.value}
        onChangeText={text => setPassword({ value: text, error: '' })}
        error={!!password.error}
        errorText={password.error}
        secureTextEntry
      />

      <Text
        onPress={() => navigation.navigate('ForgotPassword')}
        style={estilos.forgotPassword}>Esqueceu a sua senha?
      </Text>

      <Button
        text="Entrar"
        onPress={submitLogin}>
      </Button>

      <Text style={estilos.registerAccount}>Não tem uma conta?</Text>
      <Text
        style={estilos.bold}
        onPress={() => navigation.navigate("Register")}>Cadastre-se</Text>
    </View>
  </DefaultScreen>
}