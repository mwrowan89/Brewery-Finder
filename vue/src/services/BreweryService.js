import axios from "axios";

export default {
  getBreweries() {
    return axios.get("/");
  },
  getBrewery(id) {
    return axios.get(`/brewery/${id}`);
  },
  getBeersByBreweryId(id) {
    return axios.get(`/beers/${id}`);
  },

  updateBreweryById(brewery, breweryId) {
    return axios.put(`/brewery/${breweryId}/update`, brewery)
  }
  // addBrewery(brewery) {
  //     return axios.post('/breweries', brewery)
  // },
  // updateBrewery(brewery) {
  //     return axios.put(`/breweries/${brewery.id}`, brewery)
  // },
  // deleteBrewery(id) {
  //     return axios.delete(`/breweries/${id}`)
  // }
};
