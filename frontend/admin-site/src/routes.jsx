import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import MainAdmin from './pages/MainAdmin';
import Detail from './pages/Detail';
import NotFound from './pages/NotFound';

function Routes() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/admin" component={MainAdmin} />
        <Route exact path="/detail/:id" component={Detail} />
        <Route exact path="*" component={NotFound} />
      </Switch>
    </Router>
  );
}

export default Routes;