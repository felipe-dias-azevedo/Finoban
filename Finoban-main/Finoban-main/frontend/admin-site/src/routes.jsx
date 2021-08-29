import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import NotFound from './pages/NotFound';
import MainAdminCliente from './pages/MainAdminCliente';
import MainAdminRegiao from './pages/MainAdminRegiao';
import MainAdminAcesso from './pages/MainAdminAcesso';
import DetailCliente from './pages/DetailCliente';
import DetailRegiao from './pages/DetailRegiao';
import DetailAcesso from './pages/DetailAcesso';
import CadastroCliente from './pages/CadastroCliente';
import CadastroRegiao from './pages/CadastroRegiao';
import CadastroAcesso from './pages/CadastroAcesso';

function Routes() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/admin/cliente" component={MainAdminCliente} />
        <Route exact path="/admin/regiao" component={MainAdminRegiao} />
        <Route exact path="/admin/acesso" component={MainAdminAcesso} />
        <Route exact path="/admin/register/cliente" component={CadastroCliente} />
        <Route exact path="/admin/register/regiao" component={CadastroRegiao} />
        <Route exact path="/admin/register/acesso" component={CadastroAcesso} />
        <Route exact path="/admin/detail/cliente/:id" component={DetailCliente} />
        <Route exact path="/admin/detail/regiao/:id" component={DetailRegiao} />
        <Route exact path="/admin/detail/acesso/:id" component={DetailAcesso} />
        <Route exact path="*" component={NotFound} />
      </Switch>
    </Router>
  );
}

export default Routes;