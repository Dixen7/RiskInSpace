import React, { Component } from 'react';
import CreatePlayer from './CreatePlayer'

class Home extends Component {
    render() {
        return (
            <div>
              <h1>Risk In Space</h1>
              <CreatePlayer/>
            </div>
        )
    }
}

export default Home
