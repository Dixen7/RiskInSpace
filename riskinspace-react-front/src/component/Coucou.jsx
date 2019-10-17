import React, { Component } from 'react';
import RiskinspaceService from '../service/RiskinspaceService';
class Coucou extends Component {

    constructor(props) {
        super(props)
        this.refreshCoucou = this.refreshCoucou.bind(this)
        this.state = {
            texte: null
        }
    }

    componentDidMount() {
        this.refreshCoucou();
    }

    refreshCoucou() {
        RiskinspaceService.home()//HARDCODED
            .then(
                response => {
                    console.log(response);
                    this.setState({ texte: response.data })
                }
            )
    }

    render(){
        return (
            <div>{this.state.texte}</div>
        );
    }

}

export default Coucou