import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import { DarkModeProvider } from './contexts/DarkModeContext';
import { UserProvider } from './contexts/UserContext';
import { NegocioProvider } from './contexts/NegocioContext';
import { SwitchGraficoNegocioProvider } from './contexts/SwitchGraficoNegocioContext';

ReactDOM.render(
  <React.StrictMode>
    <DndProvider backend={HTML5Backend}>
      <DarkModeProvider>
        <UserProvider>
          <NegocioProvider>
            <SwitchGraficoNegocioProvider>
              <App />
            </SwitchGraficoNegocioProvider>
          </NegocioProvider>
        </UserProvider>
      </DarkModeProvider>
    </DndProvider>
  </React.StrictMode>,
  document.getElementById('root')
);