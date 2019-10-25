import axios from 'axios'

const API_URL = 'http://localhost:8080'

class RiskinspaceService {

    home() {
        return axios.get(`${API_URL}`);
    }
    planet() {
        return axios.get(`${API_URL}/planet`);
    }

    gamephase(){
        return axios.get(`${API_URL}/gamephase`);
    }
    
}

export default new RiskinspaceService()
