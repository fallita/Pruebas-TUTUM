import React from 'react';
// import logo from './logo.svg';
import './App.css';

import "primereact/resources/themes/nova/theme.css" 
import "primereact/resources/primereact.min.css"
import "primeicons/primeicons.css"
import EmpleadoContextProvider from './contexts/EmpleadoContext';
import EmpleadoList from './components/EmpleadoList';

function App(){
  return(
<div className='App'>
  <EmpleadoContextProvider>
      <EmpleadoList/>
  </EmpleadoContextProvider>

</div>
  )

}

export default App;
