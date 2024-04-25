import axios from "axios";

export default {
    getBeers() {
        return axios.get('/beers')
    },
    getBeersByBreweryId(id) {
        return axios.get(`/beers/${id}`)
    },
    getBeerById(id) {
        return axios.get(`/beer/${id}`)
    },
    addBeer(beer) {
        return axios.post('/add/beers', beer)
    },
    removeBeer(id, removeBeer) {
        return axios.put(`/removeBeer/${id}`, {removeBeer})
    },
    showBeer(id, showBeer) {
        return axios.put(`/showBeer/${id}`, {showBeer})
    },
    updateBeer(beer, beerId) {
        return axios.put(`/beer/${beerId}/update`, beer)
    }
}