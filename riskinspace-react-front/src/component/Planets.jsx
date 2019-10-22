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
        <div className="row">
          {this.state.planets.map(planet => {
            return (
              <Planet
<<<<<<< HEAD
               key={planet.planetId} name={planet.planetName}
=======
               id={planet.planetId} name={planet.planetName} key={planet.planetId}
>>>>>>> 8fed562a30d7d8e88604582ca067135aad23a940
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
