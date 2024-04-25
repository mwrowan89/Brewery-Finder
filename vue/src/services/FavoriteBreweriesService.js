import axios from 'axios';


export default {
    getFavoriteBreweriesByUser(userId) {
        //TODO: return axios.post wherever the favorite breweries is stored.
        return axios.get(`/favoriteList/${userId}`);
    },
    addFavoriteBrewery(favoriteBrewery) {
        return axios.post(`/favorite/add`, favoriteBrewery);
    },
    deleteFavoriteBrewery(deleteFavorite) {
        return axios.delete('/favorite/delete', {data: deleteFavorite });
    },
    updateFavBreweryNotes(favoriteBrewery) {
        return axios.put('/update/favoritebrewery', favoriteBrewery);
    },
    getAllFavBreweries() {
        return axios.get(`/favoritebrewery`);
    }
}