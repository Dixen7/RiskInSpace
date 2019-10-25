import React, { Component } from 'react';
import { withRouter } from 'react-router';
import RiskinspaceService from '../service/RiskinspaceService';
import axios from 'axios';
import robot from '../../src/spaaace.png'
class CreatePlayer extends Component {

  constructor(props) {
    super(props)
    this.refreshCreatePlayer = this.refreshCreatePlayer.bind(this)
    this.state = {
      species: [],
      playerName:'',
      playerSpecies:0,
      playerName2:'',
      playerSpecies2:0
    }
    this.handleSubmit = this.handleSubmit.bind(this);
  }


   onChange = (e) =>{
        this.setState({ [e.target.name]: e.target.value });
   }

	handleSubmit = (e) =>{
		e.preventDefault();
		let players = {
			"playerName": this.state.playerName,
			"playerSpecies": parseInt(this.state.playerSpecies),
			"playerName2": this.state.playerName2,
			"playerSpecies2": parseInt(this.state.playerSpecies2)
		};
		const { history } = this.props;
	    axios.post('http://localhost:8080/createplayer', players)
		  .then(function (response) {
		    console.log(response);
		    if(history) history.push('/planet');
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
  }

  componentDidMount() {
    this.refreshCreatePlayer();
  }

  componentWillUnmount() {

  }

  refreshCreatePlayer() {
    RiskinspaceService.home()
    .then(
      response => {
        this.setState({ species: response.data })
      }
    )
  }

  render(){
  	const { history } = this.props;
    return (
      <div>
        <canvas id="canvas"></canvas>
      <div className="container-fluid">
        <h1>Risk In Space</h1>
      <div className="d-flex justify-content-center">
      <div className="">

      <form onSubmit={this.handleSubmit}>
      Joueur 1
      <div className="form-group">
          <select id="playerSpecies" name="playerSpecies"  value={this.state.playerSpecies} onChange={this.onChange}>
          <option value="">Selectionner une race</option>
          {
            this.state.species.map(
              specie => <option key={specie.speciesId} value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="name">Nom : </label>
          <input type="text" name="playerName" id="playerName" className="form-control"  value={this.state.playerName} onChange={this.onChange} />
        </div>
		Joueur 2
         <div className="form-group">
          <select name="playerSpecies2" id="playerSpecies2"  value={this.state.playerSpecies2} onChange={this.onChange}>
          <option value="">Selectionner une race</option>
          {
            this.state.species.map(
              specie => <option key={specie.speciesId} value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="name">Nom : </label>
          <input type="text" name="playerName2" id="playerName2"  className="form-control" value={this.state.playerName2} onChange={this.onChange}/>
        </div>

        <input type="submit" value="jouer" className="btn btn-primary"/>
      </form>
      </div>
      </div>
      </div>
      </div>
    );
  }

}

export default withRouter(CreatePlayer)
