import axios from 'axios'

const API_URL = 'http://localhost:8080'

class RiskinspaceService {

    home() {
        return axios.get(`${API_URL}`);
    }
}

export default new RiskinspaceService()

