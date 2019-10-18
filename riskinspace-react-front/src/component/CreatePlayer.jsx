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
            <div>
            {this.state.texte}
            <form>
            <label htmlFor="name">Nom Joueur 1 : </label>
            <input type="text" name="player-name" id="name" />
            <select name="player-species">
            <option value="">Sélectionner une race</option>
            {
              this.state.species.map(
                specie => <option value={specie.speciesId}>{specie.speciesName}</option>
              )
            }

           </select>

           <label htmlFor="name">Nom Joueur 2 : </label>
          <input type="text" name="player-name2" id="name2" />
          <select name="player-species2">
          <option value="">Sélectionner une race</option>
          {
            this.state.species.map(
              specie => <option value={specie.speciesId}>{specie.speciesName}</option>
            )
          }
          </select>

         <input type="submit" />
            </form>
            </div>

        );
    }

}

export default CreatePlayer
