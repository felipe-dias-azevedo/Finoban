import React, { useState } from 'react';
import { Text, View, TouchableOpacity } from 'react-native';
import TextInput from '../../components/TextInput';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from  '../../estilos';
import estilos from './estilos';
import Button from '../../components/Button';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { nameValidator, cpfValidator, emailValidator, passwordValidator } from '../../../utils/validation';
import axios from 'axios';
import RegisterService from '../../../services/register';
import ToastUtils from '../../../utils/toast';

const Stack = createNativeStackNavigator();

export default function Register({ navigation }) {
  const [showBtnRegister, setShowBtnRegister] = useState(false);
  const [name, setName] = useState({ value: '', error: '' });
  const [cpf, setCpf] = useState({ value: '', error: '' });
  const [email, setEmail] = useState({ value: '', error: '' });
  const [password, setPassword] = useState({ value: '', error: '' });
  const [cep, setCep] = useState("");
  const [address, setAddress] = useState("");
  const [addressNumber, setAddressNumber] = useState("");
  const [neighborhood, setNeighborhood] = useState("");

  const continueForm = () => {
    const nameError = nameValidator(name.value);
    const cpfError = cpfValidator(cpf.value);
    const emailError = emailValidator(email.value);
    const passwordError = passwordValidator(password.value);

  if (nameError || cpfError || emailError || passwordError) {
    setName({ ...name, error: nameError });
    setCpf({ ...cpf, error: cpfError });
    setEmail({ ...email, error: emailError });
    setPassword({ ...password, error: passwordError });
    return
  }
      setShowBtnRegister(true);
  }

  const searchCep = async (cep) => {
    console.log(cep);
    if (cep.length >= 8) {
      try {
        const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`, {})
        const cepDetails = await response.data;
        setAddress(cepDetails.logradouro);
        setNeighborhood(cepDetails.bairro);
      } catch (error) {
        console.log(error);
      }
  }
}

  const submitRegister = async () => {
    try {
      const response = await RegisterService({ name, cpf, email, password, cep, addressNumber, neighborhood });
      navigation.navigate('Login');
    } catch (error) {
      console.log(error);
    } 
} 

    return (
    <DefaultScreen style={estilosGlobal.preencher}>
        <View style={estilos.containerLogin}>
        <Text style={estilos.textHeader} onPress={() => setShowBtnRegister(false)}>Voltar</Text>
        <Text style={estilos.textHeader}>Crie sua conta</Text>

        {!showBtnRegister ? (
        <> 
        <TextInput
        label="Nome"
        returnKeyType="next"
        value={name.value}
        onChangeText={text => setName({ value: text, error: '' })}
        error={!!name.error}
        errorText={name.error}
        autoCapitalize="none"
        autoCompleteType="name"
        textContentType="name"
        keyboardType="name"
      />
        
        <TextInput
        label="CPF"
        returnKeyType="next"
        value={cpf.value}
        onChangeText={text => setCpf({ value: text, error: '' })}
        error={!!cpf.error}
        errorText={cpf.error}
        autoCapitalize="none"
        autoCompleteType="cpf"
        textContentType="cpf"
        keyboardType="cpf"
      />

        <TextInput
        label="Email"
        returnKeyType="next"
        value={email.value}
        onChangeText={text => setEmail({ value: text, error: '' })}
        error={!!email.error}
        errorText={email.error}
        autoCapitalize="none"
        autoCompleteType="emailAddress"
        textContentType="email"
        keyboardType="email"
      />
        
        <TextInput
        label="Senha"
        returnKeyType="next"
        value={password.value}
        onChangeText={text => setPassword({ value: text, error: '' })}
        error={!!password.error}
        errorText={password.error}
        autoCapitalize="none"
        autoCompleteType="password"
        textContentType="password"
        keyboardType="password"
        secureTextEntry
      />

      <Button   
        onPress={continueForm}
        text="Continuar">
      </Button>
      
      </>
        ) : (
          <>

        <TextInput
        label="CEP"
        returnKeyType="next"
        value={cep}
        onChangeText={(cep) => {
          setCep(cep);
          searchCep(cep);
        }}
      />
        
        <TextInput
        label="Endereço"
        returnKeyType="next"
        value={address}
      />

        <TextInput
        label="Número"
        returnKeyType="next"
        value={addressNumber}
        onChangeText={setAddressNumber}
        maxLength="4"
      />
        
        <TextInput
        label="Bairro"
        returnKeyType="submit"
        value={neighborhood}
      />

      <Button   
        onPress={submitRegister}
        text="Cadastrar">
      </Button>
  
    </>
        )}

      <Text style={estilos.registerAccount}>Já possui uma conta? 
      <Text 
        style={estilos.bold}
        onPress={() => navigation.navigate("Login")}>Entrar</Text>
          </Text>
      </View>
    </DefaultScreen>
    );
}