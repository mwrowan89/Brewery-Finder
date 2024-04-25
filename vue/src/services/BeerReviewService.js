import axios from "axios";

export default {
    getAverageRatings() {
        return axios.get('/beers/average')
    },

    getReviewsByBeerId(beerId) {
        return axios.get(`/beer/${beerId}/reviews`)
    },

    addReview(beerReview) {
        return axios.post('/add/review', beerReview)
    }
}