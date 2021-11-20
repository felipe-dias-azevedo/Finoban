import React from 'react';
import { View, Text } from 'react-native';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from  '../../estilos';
import estilos from './estilos';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Button from '../../components/Button';

const Stack = createNativeStackNavigator(); 

export default function LandingPage({ navigation }) {

    return <DefaultScreen style={estilosGlobal.preencher}>
        <View style={estilos.containerLandingPage}>
            <View style={estilos.sectionHeader}>
            <Text style={estilos.textLogo}>Finoban</Text>
            <Text style={estilos.textHeader}>Bem-vindo ao Finoban.</Text>
            <Text style={estilos.textSecondary}>A melhor solução para seu financiamento imobiliário.</Text>
            </View>
            <Button 
            text="Fazer Simulação" 
            onPress={() => navigation.navigate("Simulator")}
            ></Button>
        </View>
    </DefaultScreen>

}