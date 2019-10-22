import React, { Component } from "react";
import ReactDOM from "react-dom";
import RiskinspaceService from '../service/RiskinspaceService';

class Planets extends Component {
    constructor() {
      super();
      this.state = {
        planets: []
      };
    }

    componentDidMount() {
      this.refresh();
    }

    refresh() {
      RiskinspaceService.planet()//HARDCODED
      .then(
        response => {
          console.log(response);
          this.setState({ planets: response.data })
        }
      )
    }



    render() {
      return (
        <div class="grid-container">
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
        <article className={'card card--'+this.props.id}>
        <div className="card__planet">
          <div className="planet__atmosphere">
            <div className="planet__surface"></div>
          </div>
        </div>
        {/* <div className="card__info">
          <h2 className="info__title">{this.props.name}</h2>
          </div> */}
       </article>
      );
    }
  }



  export default Planets
