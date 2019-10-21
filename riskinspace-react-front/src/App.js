import React from 'react';
import logo from './logo.svg';
import './App.css';
import Home from './component/Home';
import Planets from './component/Planets';

function App() {
  return (
    <div className="App">
      <Home />
     <Planets/>
      {/* <App2 />
      <App3 /> */}
      
    </div>
    

  );
}

 
function App2(){
  return (
    <article className="card card--pluto">
    <div className="card__planet">
      <div className="planet__atmosphere">
        <div className="planet__surface"></div>
      </div>
    </div>
    <div className="card__info">
      <h2 className="info__title">pluto</h2>
      </div>
   </article>

  );
}

function App3(){
  return (
    <article className="card card--earth">
    <div className="card__planet">
      <div className="planet__atmosphere">
        <div className="planet__surface"></div>
      </div>
    </div>
    <div className="card__info">
      <h2 className="info__title">earth</h2>
      </div>
   </article>

  );
}

export default App;

