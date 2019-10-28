import React, { Component } from "react";
import ReactDOM from "react-dom";
import RiskinspaceService from '../service/RiskinspaceService';
import {Button,Modal} from 'react-bootstrap';
import axios from 'axios';


class Planets extends Component {

    constructor() {
      super();
      let shipCount;
      if(!localStorage.getItem("shipCount")){
        shipCount = 25;
      }else{
        shipCount = parseInt(localStorage.getItem("shipCount"));
      }
      this.state = {
        planets: [],
        player1:'',
        player2:'',
        planetsPlayer1:0,
        planetsPlayer2:0,
        currentPlayer:'',
        shipCount:shipCount,
        tourJoueur:false
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
          //this.setState({shipCount:response.data.shipCount})
        }

      )
    }

    fightEnd() {
      alert("bravo");
    }

    changePlayer = (e) => {

      let tourJoueur = {
  			"tourJoueur": this.state.tourJoueur,
      };

      console.log(this.state.tourJoueur);
      axios.post(`http://localhost:8080/changeplayer`, tourJoueur)
      .then(
        response => {
          console.log(response);
          this.setState({tourJoueur:response.data});
          if(this.state.tourJoueur == true){
            this.setState({currentPlayer:this.state.player2.playerName});
          }else{
            this.setState({currentPlayer:this.state.player1.playerName});
          }
          console.log(this.state.tourJoueur);
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

          <div className="findetour">
          <button id="button1" className="risk-button" onClick={this.fightEnd}>
              Fin de Combat
          </button>
            <button className="risk-button" id="button2" onClick={this.changePlayer}>Fin de tour</button>
          </div>
          <div className="grid-container">
            {this.state.planets.map(planet => {
              return (
                <Planet
                 id={planet.planetId} name={planet.planetName} key={planet.planetId} owner={planet.planetOwner.playerName} nbships={planet.planetShipsNbr} currentPlayer={this.state.currentPlayer} shipCount={this.state.shipCount} {...this.state}
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
          planetOwner:props.owner,
          planetId:props.id,
          shipCount:props.shipCount,
          nbShips:props.nbships
        }

    }


    handleClose(e){
       e.stopPropagation();
      this.setState({show:false});
    }

    handleShow(){
      this.setState({show:true});
    }

    attack(e){
      console.log('attack');
      return <form>
        <input type="hidden" value="toto"/>
        <select>
        <option>Sélectionner une planète attaquante</option>
        </select>
      </form>;
    }

    placeShip = (e) =>{
      console.log('placeShip');
      console.log(this.state);
      this.setState({ [e.target.name]: e.target.value });
      this.setState({ shipCount: this.state.shipCount - 1 })
      localStorage.setItem("shipCount",parseInt(this.state.shipCount - 1));
      this.setState({nbShips : this.state.nbShips + 1})
      let planet = {
        "planetId": parseInt(this.state.planetId),
        "shipCount":parseInt(this.state.shipCount),
      };

      console.log(this.state.shipCount);
      console.log(localStorage.getItem("shipCount"));

      axios.post('http://localhost:8080/gamephase', planet)
		  .then(function (response) {
        console.log(response);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
    }

    render() {

      let text;
      let action;
      if(this.props.currentPlayer != this.props.owner){
        text = "Attack";
        action = this.attack;
      } else {
        text = "placer Troupe";
        action = this.placeShip;
      }

      return (
        <article className={'card card--'+this.props.id} onClick={this.handleShow}>
        <div className="card__planet">
          <div className="planet__atmosphere">
            <div className="planet__surface"></div>


          </div>
          <input type="hidden" id={"planetId-"+this.props.id} name="planetId" value={this.props.id} />
        </div>
        <div className="card__info">
          <p className="info__title" style={{'fontSize':'10px'}} >{this.props.name}</p>
          </div>
          <Modal show={this.state.show} animation={false}>
              <Modal.Title>{this.props.name}</Modal.Title>
            <Modal.Body><br/>Proprietaire : {this.props.owner} <br/>Nb de vaisseaux : {this.state.nbShips}<br/>Nd de vaisseaux restants a placer : {this.state.shipCount}</Modal.Body>
            <Modal.Footer>
              <Button variant="primary" onClick={action}>
                {text}
              </Button>
              <Button variant="primary" onClick={this.handleClose}>
                Fermer
              </Button>
            </Modal.Footer>
          </Modal>
       </article>

      );
    }
  }



  export default Planets
