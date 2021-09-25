import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Cadastro from './pages/Cadastro';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import PaginaInicial from './pages/PaginaInicial';
import Simulador from './pages/Simulador';
import Openbanking from './pages/Openbanking';
import DashboardNegocio from './pages/AnaliseNegocio/DashboardNegocio';
import LoginNegocio from './pages/AnaliseNegocio/LoginNegocio';
import GraficoNegocio from './pages/AnaliseNegocio/GraficoNegocio';
import SobreNos from './pages/SobreNos';
import { NegocioProvider } from './contexts/NegocioContext';
import { SwitchGraficoNegocioProvider } from './contexts/SwitchGraficoNegocioContext';

function Routes() {

    return (
        <Router>
            <Switch>
                <Route exact path="/" component={PaginaInicial} />
                <Route exact path="/simulador" component={Simulador} />
                <Route exact path="/dashboard" component={Dashboard} />
                <Route exact path="/login" component={Login} />
                <Route exact path="/cadastro" component={Cadastro} />
                <Route exact path="/openbanking" component={Openbanking} />
                <Route exact path="/sobrenos" component={SobreNos}/>
                <Route exact path="/analise" component={LoginNegocio} />

                <NegocioProvider>
                    <SwitchGraficoNegocioProvider>
                        <Route exact path="/analise/dashboard" component={DashboardNegocio} />
                    </SwitchGraficoNegocioProvider>
                    <Route exact path="/analise/dashboard/:id" component={GraficoNegocio} />
                </NegocioProvider>

                <Route exact path="*" component={PaginaInicial} />

            
            </Switch>
        </Router>
    );
}

export default Routes;