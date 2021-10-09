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
import EsqueciMinhaSenha from './pages/EsqueciMinhaSenha';
import RedefinirSenha from './pages/RedefinirSenha';
import { NegocioProvider } from './contexts/NegocioContext';
import { UserProvider } from './contexts/UserContext';
import { SwitchGraficoNegocioProvider } from './contexts/SwitchGraficoNegocioContext';
import PaginaNaoExiste from './pages/PaginaNaoExiste';

function Routes() {

    return (
        <Router>
            <Switch>
                <UserProvider>
                    <Route exact path="/" component={PaginaInicial} />
                    <Route exact path="/simulador" component={Simulador} />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/cadastro" component={Cadastro} />
                    <Route exact path="/openbanking" component={Openbanking} />
                    <Route exact path="/sobrenos" component={SobreNos} />
                    <Route exact path="/analise" component={LoginNegocio} />
                    <Route exact path="/esqueci-minha-senha" component={EsqueciMinhaSenha} />
                    <Route exact path="/esqueci-minha-senha/:jwt" component={RedefinirSenha} />

                    <NegocioProvider>
                        <SwitchGraficoNegocioProvider>
                            <Route exact path="/analise/dashboard" component={DashboardNegocio} />
                        </SwitchGraficoNegocioProvider>
                        <Route exact path="/analise/dashboard/:id" component={GraficoNegocio} />
                    </NegocioProvider>

                </UserProvider>
                <Route exact path="*" component={PaginaInicial} />
            </Switch>
        </Router>
    );
}

export default Routes;