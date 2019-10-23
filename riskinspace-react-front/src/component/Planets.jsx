import React, { Component } from "react";
import ReactDOM from "react-dom";
import RiskinspaceService from '../service/RiskinspaceService';
import {Button,Modal} from 'react-bootstrap';

class Planets extends Component {
    constructor() {
      super();
      this.state = {
        planets: [],
        player1:'',
        player2:'',
        planetsPlayer1:0,
        planetsPlayer2:0,
        currentPlayer:'',
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
          this.setState({currentPlayer:this.state.player1.playerName})
          this.setState({planetsPlayer1:response.data.countPlanetPlayer1})
          this.setState({planetsPlayer2:response.data.countPlanetPlayer2})
          this.setState({player1Species:response.data.player1.species.speciesName})
          this.setState({player2Species:response.data.player2.species.speciesName})
        }
      )
    }

    render() {

      return (


        <div className="">

          <div className="joueur1">
            <h2>{this.state.player1.playerName}</h2>
            <p>Race : {this.state.player1Species}</p>
            <p>Nb Planètes : {this.state.planetsPlayer1}</p>
          </div>
          <div className="tour">
            <h3>Tour de </h3>
            <p>{this.state.currentPlayer}</p>
          </div>
          <div className="joueur2">
            <h2>{this.state.player2.playerName}</h2>
            <p>Race : {this.state.player2Species}</p>
            <p>Nb Planètes : {this.state.planetsPlayer2}</p>
          </div>
          <div>
            <button onClick={this.changePlayer}>Fin de tour</button>
          </div>
          <div className="grid-container">
            {this.state.planets.map(planet => {
              return (
                <Planet
                 id={planet.planetId} name={planet.planetName} key={planet.planetId} owner={planet.planetOwner.playerName} nbships={planet.planetShipsNbr}
                />

              );
            })}
          </div>
        </div>


      );
    }

  }

  class Planet extends Component {

    constructor(props){
      super(props);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
          show:false,
          currentPlayer:props.currentPlayer,
          planetOwner:props.owner
        }
    }

    handleClose(e){
       e.stopPropagation();
      this.setState({show:false});
    }

    handleShow(){
      this.setState({show:true});
    }



    render() {

      console.log(this.state.currentPlayer);
      let text;
      if(this.state.currentPlayer != this.state.planetOwner){
        text = "Attack";
      } else {
        text = "placer Troupe";
      }

      return (
        <article className={'card card--'+this.props.id} onClick={this.handleShow}>
        <div className="card__planet">
          <div className="planet__atmosphere">
            <div className="planet__surface"></div>


          </div>
        </div>
        {/* <div className="card__info">
          <h2 className="info__title">{this.props.name}</h2>
          </div> */}
          <Modal show={this.state.show} onHide={this.handleClose} animation={false}>
              <Modal.Title>{this.props.name}</Modal.Title>
            <Modal.Body><br/>Proprietaire : {this.props.owner} <br/>Nb de vaisseaux : {this.props.nbships}</Modal.Body>
            <Modal.Footer>
              <Button variant="primary" onClick={this.handleClose}>
                {text}
              </Button>
            </Modal.Footer>
          </Modal>
       </article>

      );
    }
  }


  export default Planets
