import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import PaginaInicial from './pages/PaginaInicial';
import Simulador from './pages/Simulador';

function Routes() {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={PaginaInicial} />
                <Route exact path="/simulador" component={Simulador} />
                <Route exact path="/dashboard" component={Dashboard} />
                <Route exact path="*" component={PaginaInicial} />
            </Switch>
        </Router>
    );
}

export default Routes;