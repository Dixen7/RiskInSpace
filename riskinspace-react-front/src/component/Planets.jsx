import React, { Component } from "react";
import ReactDOM from "react-dom";
import RiskinspaceService from '../service/RiskinspaceService';

class Planets extends Component {
    constructor() {
      super();
      this.state = {
        planets: [],
        player1:'',
        player2:'',
        planetsPlayer1:0,
        planetsPlayer2:0,
        show:false
      };
    }

    componentDidMount() {
      this.refresh();
    }

    refresh() {
      RiskinspaceService.planet()
      .then(
        response => {
          console.log(response);
          this.setState({ planets: response.data.planets })
          this.setState({player1:response.data.player1})
          this.setState({player2:response.data.player2})
          this.setState({planetsPlayer1:response.data.countPlanetPlayer1})
          this.setState({planetsPlayer2:response.data.countPlanetPlayer2})
          this.setState({player1Species:response.data.player1.species.speciesName})
          this.setState({player2Species:response.data.player2.species.speciesName})
        }
      )
    }



    render() {

      return (
        <div className="row">
          <div className="col-lg-6">
            <h2>{this.state.player1.playerName}</h2>
            <p>Race : {this.state.player1Species}</p>
            <p>Nb Planètes : {this.state.planetsPlayer1}</p>
          </div>
          <div className="col-lg-6">
            <h2>{this.state.player2.playerName}</h2>
            <p>Race : {this.state.player2Species}</p>
            <p>Nb Planètes : {this.state.planetsPlayer2}</p>
          </div>
          {this.state.planets.map(planet => {
            return (
              <Planet
               id={planet.planetId} name={planet.planetName} key={planet.planetId}
              />
            );
          })}
        </div>
      );
    }

  }

  class Planet extends Component {

    render() {
      return (
        <div className="col-lg-2">
        <article className={'card card--'+this.props.id}>
        <div className="card__planet">
          <div className="planet__atmosphere">
            <div className="planet__surface"></div>
          </div>
        </div>
        <div className="card__info">
          <h2 className="info__title">{this.props.name}</h2>
          </div>
       </article>
       </div>
      );
    }
  }



  export default Planets
