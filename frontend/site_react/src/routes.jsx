import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Cadastro from './pages/Cadastro';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import PaginaInicial from './pages/PaginaInicial';
import Simulador from './pages/Simulador';

import DashboardNegocio from './pages/AnaliseNegocio/DashboardNegocio';
import LoginNegocio from './pages/AnaliseNegocio/LoginNegocio';

function Routes() {

    return (
        <Router>
            <Switch>
                <Route exact path="/" component={PaginaInicial} />
                <Route exact path="/simulador" component={Simulador} />
                <Route exact path="/dashboard" component={Dashboard} />
                <Route exact path="/login" component={Login} />
                <Route exact path="/cadastro" component={Cadastro} />

                <Route exact path="/analise" component={LoginNegocio} />
                <Route exact path="/analise/dashboard" component={DashboardNegocio} />

                <Route exact path="*" component={PaginaInicial} />
            </Switch>
        </Router>
    );
}

export default Routes;