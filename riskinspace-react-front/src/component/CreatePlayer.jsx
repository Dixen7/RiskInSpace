import React, { Component } from 'react';
import RiskinspaceService from '../service/RiskinspaceService';
class CreatePlayer extends Component {

  constructor(props) {
    super(props)
    this.refreshCreatePlayer = this.refreshCreatePlayer.bind(this)
    this.state = {
      species: []
    }
    this.handleSubmit = this.handleSubmit.bind(this);
  }



 async handleSubmit(){
  	await fetch('https://localhost:8080/', {
	  method: 'POST',
	  headers: {
	    'Accept': 'application/json',
	    'Content-Type': 'application/json',
	  },
	  body: JSON.stringify({
	    playerName: document.getElementById('playerName').value,
	    playerSpecies: document.getElementById('playerSpecies').value,
	    playerName2:document.getElementById('playerName2').value,
	    playerSpecies2:document.getElementById('playerSpecies2').value
	  })
	  
	});
	
  }

   

  componentDidMount() {
    this.refreshCreatePlayer();
  }

  refreshCreatePlayer() {
    RiskinspaceService.home()//HARDCODED
    .then(
      response => {
        console.log(response);
        this.setState({ species: response.data })
      }
    )
  }

 

  render(){
    return (
      <div className="container-fluid">
      <div className="d-flex justify-content-center">
      <div className="">
      <form>
        <div className="form-group">
          <label htmlFor="name">Nom Joueur 1 : </label>
          <input type="text" name="playerName" id="playerName" className="form-control"/>
        </div>
        <div className="form-group">
          <select id="playerSpecies" name="playerSpecies">
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="name">Nom Joueur 2 : </label>
          <input type="text" name="playerName2" id="playerName2"  className="form-control"/>
        </div>
        <div className="form-group">
          <select name="playerSpecies2" id="playerSpecies2">
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <input type="submit" value="jouer" className="btn btn-primary" />
      </form>
      </div>
      </div>
      </div>
    );
  }

}

export default CreatePlayer
