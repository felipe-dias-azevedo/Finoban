import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import './styles/global.css';
import './styles/dashboard.css';
import './styles/home-admin.css';
import './styles/cadastro.css';
import './styles/login-admin.css';

ReactDOM.render(
  <React.StrictMode>
    <DndProvider backend={HTML5Backend}>
      <App />
    </DndProvider>
  </React.StrictMode>,
  document.getElementById('root')
);                                                                                          