import React, { Component } from 'react';
import RiskinspaceService from '../service/RiskinspaceService';
class CreatePlayer extends Component {

  constructor(props) {
    super(props)
    this.refreshCreatePlayer = this.refreshCreatePlayer.bind(this)
    this.state = {
      species: []
    }
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
          <input type="text" name="player-name" id="name" className="form-control"/>
        </div>
        <div className="form-group">
          <select name="player-species">
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
          <input type="text" name="player-name2" id="name2"  className="form-control"/>
        </div>
        <div className="form-group">
          <select name="player-species2">
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>
        </div>
        <input type="submit" value="jouer" className="btn btn-primary" id="jouer" />
      </form>
      </div>
      </div>
      </div>
    );
  }

}

export default CreatePlayer
