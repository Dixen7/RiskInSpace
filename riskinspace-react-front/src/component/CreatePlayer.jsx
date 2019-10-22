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

  changeHandler = (e) => {
    this.setState({[e.target.name]: e.target.value})
  }

 handleSubmit = e =>{
   e.preventDefault()
   axios.post('http://localhost:8080/createplayer', this.state)
     .then((response) => {
       console.log(response);
     }, (error) => {
       console.log(error);
     })
   alert(this.state);
   /*
    await fetch('http://localhost:8080/createplayer', {
			method: 'POST',
			body: JSON.stringify({
        playerName: document.getElementById('playerName').value,
        playerSpecies: document.getElementById('playerSpecies').value,
        playerName2:document.getElementById('playerName2').value,
        playerSpecies2:document.getElementById('playerSpecies2').value
			}),
			headers: {
				"Content-type": "application/json; charset=UTF-8"
			}
    }).then((response) => {
      console.log(response);
    }, (error) => {
      console.log(error);
    });*/


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
    return (
      <div className="container-fluid">
      <div className="d-flex justify-content-center">
      <div className="">
      <form>
        <div className="form-group">
          <label htmlFor="name">Nom Joueur 1 : </label>
          <input type="text" name="playerName" id="playerName" className="form-control" onChange={this.changeHandler}/>
        </div>
        <div className="form-group">
          <select id="playerSpecies" name="playerSpecies" onChange={this.changeHandler}>
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
          <input type="text" name="playerName2" id="playerName2"  className="form-control" onChange={this.changeHandler}/>
        </div>
        <div className="form-group">
          <select name="playerSpecies2" id="playerSpecies2" onChange={this.changeHandler}>
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
