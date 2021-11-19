import React, { useState } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import DefaultScreen from '../DefaultScreen';
import estilosGlobal from '../../estilos';
import estilos from './estilos';
import { Dimensions, ScrollView } from "react-native";
import { LineChart } from "react-native-chart-kit";

export default function Dashboard({ route, navigation }) {
    const screenWidth = Dimensions.get("window").width;
    const [expandCifra, setExpandCifra] = useState(true);
    const [expandPresil, setExpandPresil] = useState(false);
    const [expandS16, setExpandS16] = useState(false);

    console.log("\n\n\n\n\n\ recebido");
    console.log(route.params);

    const bank1 = route.params.data[0].data;
    const bank2 = route.params.data[1].data;
    const bank3 = route.params.data[2].data;
    const nameBank = "Banco Cifra";

    const reverseExpandCifra = () => {
        setExpandCifra(!expandCifra);
    }

    const reverseExpandPresil = () => {
        setExpandPresil(!expandPresil);
    }

    const reverseExpandS16 = () => {
        setExpandS16(!expandS16);
    }

    let value = [10030, 10031, 10032, 10033, 10040];

    const yearsFinance = (bank) => {
        let years = [2021, 2022, 2023, 2024, 2025];
        for (let i = 0; i < bank1.length; i++) {
            years.push(bank1[i].year);
        }
        return years;
    }

    const valueFinance = (bank) => {
        let value = [10030, 10031, 10032, 10033, 10040];
        return value;
    }

    const chartConfig = {
        backgroundColor: "#FFFFFF",
        backgroundGradientFromOpacity: 0,
        backgroundGradientToOpacity: 0,
        decimalPlaces: 2,
        color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
        labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
    };

    const data = {
        labels: yearsFinance(),
        datasets: [
            {
                data: valueFinance(),
                color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
                strokeWidth: 2
            }
        ],
        legend: [`${nameBank}`]
    };

    return <DefaultScreen style={estilosGlobal.preencher}>
        <ScrollView>
            <View style={estilos.containerLogin}>
                <Text style={estilos.textHeader}>Confira as taxas de cada banco referente aos dados mencionados</Text>

                <LineChart
                    data={data}
                    width={screenWidth}
                    height={256}
                    chartConfig={chartConfig}
                />

                <Text style={estilos.textBank}>Valor do imóvel:
                    {Intl.NumberFormat('pt-BR', {
                        style: 'currency', currency: 'BRL'
                    }).format(route.params.valorImovel)}</Text>

                <TouchableOpacity style={estilos.btnTouch} onPress={reverseExpandCifra}>
                    <Text style={estilos.textBtnTouch}>Banco Cifra</Text>
                </TouchableOpacity>

                {expandCifra && (

                    <>
                        <Text style={estilos.textTax}>Taxa Total: {bank1.taxaTotal.toFixed(2)}%</Text>
                        <Text>Valor dos Seguros: {bank1.dfi + bank1.mip.toFixed(2)}</Text>
                        <Text>Valor final do imóvel: R$650.000,00</Text>
                        <TouchableOpacity>
                            <Text style={estilos.textDetails}>Ver mais</Text>
                        </TouchableOpacity>
                    </>
                )}

                <TouchableOpacity style={estilos.btnTouch} onPress={reverseExpandPresil}>
                    <Text style={estilos.textBtnTouch}>Banco Presil</Text>
                </TouchableOpacity>

                {expandPresil && (
                    <>
                        <Text style={estilos.textTax}>Taxa Total: {bank2.taxaTotal.toFixed(2)}%</Text>
                        <Text>Valor dos Seguros: {bank2.dfi + bank2.mip.toFixed(2)}</Text>
                        <Text>Valor final do imóvel: R$650.000,00</Text>
                        <TouchableOpacity>
                            <Text style={estilos.textDetails}>Ver mais</Text>
                        </TouchableOpacity>
                    </>
                )}

                <TouchableOpacity style={estilos.btnTouch} onPress={reverseExpandS16}>
                    <Text style={estilos.textBtnTouch}>Banco S16</Text>
                </TouchableOpacity>

                {expandS16 && (
                    <>
                        <Text style={estilos.textTax}>Taxa Total: {bank3.taxaTotal.toFixed(2)}%</Text>
                        <Text>Valor dos Seguros: {bank3.dfi + bank3.mip.toFixed(2)}</Text>
                        <Text>Valor final do imóvel: R$650.000,00</Text>
                        <TouchableOpacity>
                            <Text style={estilos.textDetails}>Ver mais</Text>
                        </TouchableOpacity>
                    </>
                )}

            </View>
        </ScrollView>
    </DefaultScreen>

}