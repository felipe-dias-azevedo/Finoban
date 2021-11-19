import React, { useState } from 'react';
import { View, Text, Pressable } from 'react-native';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from '../../estilos';
import estilos from './estilos';
import TextInput from '../../components/TextInput';
import Button from '../../components/Button';
import FinanceService from '../../../services/finance';
import {
    cpfValidator,
    rendaValidator,
    valorImovelValidator,
    porcentagemRendaValidator,
    tempoFinanciamentoValidator
} from '../../../utils/validation';

export default function Simulator({ navigation }) {
    const [cpf, setCpf] = useState({ value: '', error: '' });
    const [renda, setRenda] = useState({ value: '', error: '' });
    const [valorImovel, setValorImovel] = useState({ value: '', error: '' });
    const [porcentagemRenda, setPorcentagemRenda] = useState({ value: '', error: '' });
    const [tempoFinanciamento, setTempoFinanciamento] = useState({ value: '', error: '' });

    const submitSimulator = () => {
        const cpfError = cpfValidator(cpf.value);
        const rendaError = rendaValidator(renda.value);
        const valorImovelError = valorImovelValidator(valorImovel.value);
        const porcentagemRendaError = porcentagemRendaValidator(porcentagemRenda.value);
        const tempoFinanciamentoError = tempoFinanciamentoValidator(tempoFinanciamento.value);

        if (cpfError || rendaError || valorImovelError || porcentagemRendaError || tempoFinanciamentoError) {
            setCpf({ ...cpf, error: cpfError });
            setRenda({ ...renda, error: rendaError });
            setValorImovel({ ...valorImovel, error: valorImovelError });
            setPorcentagemRenda({ ...porcentagemRenda, error: porcentagemRendaError })
            setTempoFinanciamento({ ...tempoFinanciamento, error: tempoFinanciamentoError });
            return;
        }

        FinanceService({ cpf: cpf, renda: renda, valorImovel: valorImovel, tempoFinanciamento: tempoFinanciamento }).then((success) => {
            const infoFinance = success;
            console.log(infoFinance);
            navigation.navigate('Dashboard', infoFinance);
        });

    }

    return <DefaultScreen style={estilosGlobal.preencher}>
        <View style={estilos.containerLogin}>
            <Text style={estilos.textHeader}>Já sabe o valor do imóvel?</Text>

            <TextInput
                label="CPF"
                returnKeyType="next"
                value={cpf.value}
                onChangeText={text => setCpf({ value: text, error: '' })}
                error={!!cpf.error}
                errorText={cpf.error}
                autoCapitalize="none"
                autoCompleteType="email"
                textContentType="emailAddress"
                keyboardType="email-address"
            />

            <TextInput
                label="Renda"
                returnKeyType="next"
                value={renda.value}
                onChangeText={text => setRenda({ value: text, error: '' })}
                error={!!renda.error}
                errorText={renda.error}
                autoCapitalize="none"
                autoCompleteType="email"
                textContentType="emailAddress"
                keyboardType="email-address"
            />

            <TextInput
                label="Valor do imóvel"
                returnKeyType="next"
                value={valorImovel.value}
                onChangeText={text => setValorImovel({ value: text, error: '' })}
                error={!!valorImovel.error}
                errorText={valorImovel.error}
                autoCapitalize="none"
                autoCompleteType="email"
                textContentType="emailAddress"
                keyboardType="email-address"
            />

            <TextInput
                label="Tempo do Financiamento"
                returnKeyType="next"
                value={tempoFinanciamento.value}
                onChangeText={text => setTempoFinanciamento({ value: text, error: '' })}
                error={!!tempoFinanciamento.error}
                errorText={tempoFinanciamento.error}
                autoCapitalize="none"
                autoCompleteType="email"
                textContentType="emailAddress"
                keyboardType="email-address"
            />

            <TextInput
                label="Porcentagem de Renda"
                returnKeyType="next"
                value={porcentagemRenda.value}
                onChangeText={text => setPorcentagemRenda({ value: text, error: '' })}
                error={!!tempoFinanciamento.error}
                errorText={tempoFinanciamento.error}
                autoCapitalize="none"
                autoCompleteType="email"
                textContentType="emailAddress"
                keyboardType="email-address"
            />

            <Button
                text={"Fazer simulação"}
                onPress={submitSimulator}>
            </Button>

        </View>
    </DefaultScreen>

}