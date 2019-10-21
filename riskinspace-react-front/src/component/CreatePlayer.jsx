import React, { Component } from 'react';
import RiskinspaceService from '../service/RiskinspaceService';
import axios from 'axios'
class CreatePlayer extends Component {

  constructor(props) {
    super(props)
    this.refreshCreatePlayer = this.refreshCreatePlayer.bind(this)
    this.state = {
      species: [],
      playerName:'',
      playerSpecies:'',
      playerName2:'',
      playerSpecies2:''
    }
    this.handleSubmit = this.handleSubmit.bind(this);
  }

   onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

	handleSubmit = (e) =>{
		e.preventDefault();
		let players = {
			playerName: this.state.playerName, 
			playerSpecies: this.state.playerSpecies, 
			playerName2: this.state.playerName2, 
			playerSpecies2: this.state.playerSpecies2
		};
	    console.log(players);
	    axios.post('localhost:8080/createplayer', {
		    players
		  })
		  .then(function (response) {
		    console.log(response);
		    this.props.history.push('/planet');
		  })
		  .catch(function (error) {
		    console.log(error);
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
          <input type="text" name="playerName" id="playerName" className="form-control"  value={this.state.playerName} onChange={this.onChange} />
        </div>
        <div className="form-group">
          <select id="playerSpecies" name="playerSpecies"  value={this.state.playerSpecies} onChange={this.onChange}>
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option key={specie.speciesId} value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="name">Nom Joueur 2 : </label>
          <input type="text" name="playerName2" id="playerName2"  className="form-control" value={this.state.playerName2} onChange={this.onChange}/>
        </div>
        <div className="form-group">
          <select name="playerSpecies2" id="playerSpecies2"  value={this.state.playerSpecies2} onChange={this.onChange}>
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option key={specie.speciesId} value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <input type="submit" value="jouer" className="btn btn-primary" onSubmit={this.handleSubmit}/>
      </form>
      </div>
      </div>
      </div>
    );
  }

}

export default CreatePlayer
