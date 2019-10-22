import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import './App.css';
import Home from './component/Home';
import Planets from './component/Planets';

function App() {
  return (
    <div className="App">
<<<<<<< HEAD
      <Home />
     <Planets/>
    </div>
    
=======
    <h1>Risk In Space</h1>
    <Router>
      <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/planet" component={Planets} />
>>>>>>> 8fed562a30d7d8e88604582ca067135aad23a940

      </Switch>
    </Router>

<<<<<<< HEAD
=======
      
    </div>
    

  );
}

>>>>>>> 8fed562a30d7d8e88604582ca067135aad23a940

export default App;
