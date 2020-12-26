import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { applyMiddleware, createStore } from 'redux';
import rootReducer from './module';

import {Provider} from 'react-redux'
import { composeWithDevTools } from 'redux-devtools-extension';
import logmiddleware from './lib/logmiddleware';
import {createLogger} from 'redux-logger'
import ReduxThunk from 'redux-thunk'

const logger=createLogger()
const Store=createStore(rootReducer,composeWithDevTools(applyMiddleware(logger,ReduxThunk)))

ReactDOM.render(
  <Provider store={Store}>
  <React.StrictMode>
    <App />
  </React.StrictMode>
  </Provider>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
