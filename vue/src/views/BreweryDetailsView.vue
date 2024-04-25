<template>
    <button
      v-if="this.$store.state.user.username == 'admin'"
      id="edit-brewery-button"
      v-on:click="toggleShowForm"
    >
      Edit Brewery Info
    </button>
    <div class="edit-brewery" v-if="this.$store.state.user.username == 'admin'">
      <form v-if="showForm === true" v-on:submit.prevent="updateBrewery(brewery)">
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="brewery.name" /><br />
        <label for="address">Address:</label>
        <input
          type="text"
          id="address"
          name="Address"
          v-model="brewery.address"
        /><br />
        <label for="city">City:</label>
        <input type="text" id="city" name="city" v-model="brewery.city" /><br />
        <label for="state">State:</label>
        <input
          type="text"
          id="state"
          name="state"
          v-model="brewery.state"
        /><br />
        <label for="hours">Hours:</label>
        <input
          type="text"
          id="hours"
          name="hours"
          v-model="brewery.hours"
        /><br />
        <label for="brewery">Phone:</label>
        <input
          type="text"
          id="phone"
          name="phone"
          v-model="brewery.phone"
        /><br />
        <label for="description">Description:</label><br />
        <textarea
          type="text"
          id="description"
          name="description"
          v-model="brewery.history"
        ></textarea
        ><br />
        <label for="image">Image URL:</label>
        <input type="text" id="image" name="image" v-model="brewery.img" /><br />
        <button type="submit">Submit</button>
      </form>
    </div>
  
    <div class="brewery-details">
      <div class="brewery-header">
        <img v-bind:src="brewery.img" />
        <div id="name-and-place">
          <h1>{{ brewery.name }}</h1>
          <p>
            Location: {{ brewery.address }} -- {{ brewery.city }},
            {{ brewery.state }}
          </p>
          <p>Rating: {{ brewery.rating }}</p>
        </div>
      </div>
      <div class="history-box">
        <p class="history">{{ brewery.history }}</p>
      </div>
      <br />
      <div class="time-and-contact">
        <h2>Hours of Operation {{ brewery.hours }}</h2>
        <br />
        <h2>Phone Number: {{ brewery.phone }}</h2>
        <br />
      </div>
  
      <a v-bind:href="brewery.webSite" target="_blank">Visit their website </a
      ><br />
    </div>
  
    <div class="review-form" v-show="reviewForm === true">
      <form v-on:submit.prevent="addReview(currentBeerId)">
        <h2>Beer:&nbsp; {{ beerName }}</h2>
        <br />
        <div id="rating-input">
          <label for="rating">Rating:</label>
          <input
            type="range"
            id="rating"
            name="rating"
            min="1"
            max="5"
            step="0.1"
            v-model="beerReview.rating"
          />&nbsp;
          {{ displayRating }}
        </div>
        <div id="review-textarea">
          &nbsp;
          <label for="rating">Review:</label>
          <textarea
            type="text"
            id="rating"
            name="rating"
            v-model="beerReview.review"
          ></textarea
          >&nbsp;
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  
    <div>
      <h2 id="beer_list">
        Available Beers
        <button
          id="add-beer"
          v-if="this.$store.state.user.username == 'admin' && this.adminForm === false"
          v-on:click="toggleAdminForm"
        >
          Add New Beer
        </button>
      </h2>
      <form
        id="add-beer-form"
        v-show="adminForm === true"
        v-on:submit.prevent="addBeer(brewery.id)"
      >
        <label for="name">Name:</label>
        <input type="text" name="name" v-model="beer.beerName" />
        <label for="type">Type:</label>
        <select id="beer-type" v-model="beer.beerType">
          <option value="Ale">Ale</option>
          <option value="Lager">Lager</option>
          <option value="Stout">Stout</option>
          <option value="IPA">IPA</option>
          <option value="Double IPA">Double IPA</option>
          <option value="Triple IPA">Triple IPA</option>
          <option value="Pilsner">Pilsner</option>
          <option value="Porter">Porter</option>
          <option value="Sour">Sour</option>
          <option value="Wheat">Wheat</option>
          <option value="Cider">Cider</option>
          <option value="Seltzer">Seltzer</option>
        </select>
        <label for="abv">ABV:</label>
        <input type="text" id="abv" name="abv" v-model="beer.beerAbv" />
        <label for="description">Description:</label>
        <textarea name="description" v-model="beer.beerDescription"></textarea>
        <button type="submit">Submit</button>
      </form>
  
      <ul class="beer_list">
        <li v-for="beer in filteredBeers" :key="beer.id">
          <div id="beer" v-if="beer.show === true">
            <router-link :to="{ name: 'beerInfo', params: { id: beer.beerId } }">
              <div>
                {{ beer.beerName }} <br />
                {{ beer.beerType }} : {{ beer.beerAbv }}% ABV<br /><br /><br />
  
                <h2
                  id="beer-rating"
                  v-for="(average, index) in ratings"
                  v-bind:key="index"
                >
                  <p v-if="average.beerId === beer.beerId">
                    Rating: {{ average.rating.toFixed(1) }}
                  </p>
                </h2>
                <!-- ADMIN USE ONLY -->
              <span class="edit-delete-buttons" v-if="this.$store.state.user.username == 'admin'">  
              <button id="delete-button"
                v-on:click.prevent="hideBeer(beer.beerId)">
                Delete
              </button>
  
              <router-link
                :to="{ name: 'editBeer', params: { id: beer.beerId } }"
              >
                <button id="edit-beer-button">&nbsp; Edit &nbsp;</button>
              </router-link>
            </span>
            <!--^^ ADMIN USE ONLY ^^-->
            
                <button
                  id="add-review-button"
                  v-on:click.prevent="toggleReviewForm(beer.beerId)"
                  v-if="
                    this.$store.state.token != '' &&
                    this.$store.state.user.username !== 'admin'
                  "
                >
                  Add Review
                </button>
              </div>
            </router-link>
          </div>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import BreweryService from "../services/BreweryService";
  import BeerService from "../services/BeerService";
  import FavoriteBreweriesService from "../services/FavoriteBreweriesService";
  import BeerReviewService from "../services/BeerReviewService";
  
  export default {
    data() {
      return {
        brewery: {},
        website: "",
        beers: [],
        filteredBeers: [],
        typeFilter: "",
        beerName: "",
        showForm: false,
        adminForm: false,
        reviewForm: false,
        beer: {},
        ratings: [],
        currentBeerId: 0,
        beerReview: {
          rating: 1.0,
        },
        breweryToUpdate: {},
      };
    },
    async created() {
      const id = this.$route.params.id;
      const response = await BreweryService.getBrewery(id);
      this.brewery = response.data;
      this.website = this.brewery.webSite;
      await this.fetchBeers();
      await this.fetchAverageRatings();
    },
  
    methods: {
      async hideBeer(beerId) {
        try {
          await BeerService.removeBeer(beerId, { show: false });
          this.fetchBeers();
        } catch (error) {
          console.log("Error hiding beer", error);
        }
        this.fetchBeers();
        this.fetchBeers();
      },
  
      async fetchBeers() {
        const response = await BeerService.getBeersByBreweryId(this.brewery.id);
        this.beers = response.data;
        this.filteredBeers = this.beers;
      },
      async fetchAverageRatings() {
        const response = await BeerReviewService.getAverageRatings();
        this.ratings = response.data;
      },
      toggleShowForm() {
        if (this.showForm === false) {
          this.showForm = true;
        } else if (this.showForm === true) {
          this.showForm = false;
        }
      },
      toggleAdminForm() {
        if (this.adminForm === false) {
          this.adminForm = true;
        } else if (this.adminForm === true) {
          this.adminForm = false;
        }
      },
      addBeer(id) {
        this.beer.breweryId = id;
        BeerService.addBeer(this.beer);
        this.beer = {};
        this.fetchBeers();
        this.fetchBeers();
        this.adminForm = false;
        console.log(this.beer.beerDescription)
      },
      filterTypes() {
        this.filteredBeers = this.beers.filter((beer) => {
          return beer.beerType
            .toLowerCase()
            .includes(this.typeFilter.toLowerCase());
        });
      },
      addReview(currentBeerId) {
        this.beerReview.userId = this.$store.state.user.id;
        this.beerReview.beerId = currentBeerId;
        BeerReviewService.addReview(this.beerReview);
        this.beerReview = {};
        this.fetchAverageRatings();
        this.fetchAverageRatings();
        this.reviewForm = false;
      },
      toggleReviewForm(beerId) {
        if (this.reviewForm === false) {
          this.reviewForm = true;
          this.getBeerName(beerId);
          this.currentBeerId = beerId;
        } else if (this.reviewForm === true) {
          this.reviewForm = false;
        }
      },
      getBeerName(beerId) {
        const beer = this.beers.find((beer) => beer.beerId === beerId);
        if (beer) {
          this.beerName = beer.beerName;
          this.beerId = beer.beerId;
          console.log(beer.beerName);
        }
      },
  
      updateBrewery(brewery) {
        BreweryService.updateBreweryById(brewery, brewery.id);
        this.showForm = false;
      },
    },
    computed: {
      displayRating() {
        return (this.beerReview.rating / 1).toFixed(1);
      },
    },
  };
  </script>
  
  <style scoped>
  #add-beer-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    padding-right: 10px;
    height: max-content;
    width: 60vw;
    margin-left: 10vw;
    justify-content: flex-start;
  }
  
  .brewery-details {
    margin-top: 150px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 2px solid white;
    color: white;
    margin: 100px;
    padding: 40px;
    background-color: rgba(167, 136, 97, 0.5);
  }
  
  .history-box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: fit-content;
    /* border: 2px solid white; */
    border-bottom: 2px solid white;
    padding: 10px;
    margin: 10px;
  }
  
  .history {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    width: 50vw;
    font-size: 20px;
    white-space: wrap;
  }
  
  .brewery-header {
    display: flex;
    margin-bottom: 50px;
  }
  
  .brewery-header #name-and-place {
    display: flex;
    flex-direction: column;
    padding: 50px;
  }
  
  .brewery-header img {
    height: 150px;
    width: auto;
  }
  
  img {
    width: min-content;
    height: min-content;
  }
  
  /* beer list details */
  .beer_list {
    display: flex;
    flex-wrap: wrap;
    margin-left: auto;
    margin-right: auto;
    list-style-type: none;
    padding-right: 10px;
    height: max-content;
    width: 60vw;
    margin-left: 10vw;
    justify-content: flex-start;
    margin-bottom: 50px;
  }
  
  #beer_list {
    margin-top: 90px;
    color: white;
    text-align: start;
    padding-left: 28.5vw;
  }
  
  .beer p {
    display: flex;
    background-color: rgba(167, 136, 97, 0.5);
    height: 15vh;
    padding: 10px;
    margin: 5px;
    width: 80vw;
  }
  
  #beer div {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    columns: 2;
    justify-content: space-around;
    flex-wrap: wrap;
    background-color: rgba(167, 136, 97, 0.5);
    height: 6vh;
    padding: 10px;
    margin: 5px;
    width: 23vw;
    color: white;
    font-size: 12pt;
    overflow: none;
  }
  a {
    text-decoration: none;
  }
  
  #beer-type {
    width: 10.5vw;
  }
  #beer-rating {
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 13pt;
    position: absolute;
    margin-left: 17vw;
    margin-bottom: 2vw;
  }
  #add-review-button {
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 10pt;
    width: fit-content;
    height: fit-content;
    padding: 2px;
    background-color: rgba(251, 217, 174, 0.5);
    border: 1px solid white;
    border-radius: 4px;
    position: absolute;
    margin-left: 17.5vw;
    margin-top: 1.6vw;
  }
  
  /* Rating display */
  
  .review-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 2px solid white;
    background-color: rgb(180, 141, 92);
    border-radius: 4px;
    width: 35vw;
    height: 8vw;
    color: white;
    margin-left: 18vw;
    margin-bottom: -5vw;
    margin-top: -5vw;
  }
  .review-form h2 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  #rating-input {
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 12pt;
    margin-bottom: 6px;
  }
  #review-textarea {
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 12pt;
    margin-top: 6px;
  }
  
  /* Admin use only */
  #edit-brewery-button {
    margin-top: 10vw;
    margin-left: 10vw;
    height: 1.5vw;
    width: 8.5vw;
  }
  .edit-brewery {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: white;
    padding-right: 10px;
    height: max-content;
    width: 60vw;
    justify-content: flex-start;
    margin-top: -6vw;
    margin-bottom: -3vw;
    margin-left: 5vw;
  }
  .edit-delete-buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: 10pt;
    width: fit-content;
    height: fit-content;
    padding: 2px;
    margin: 2px;
    border-radius: 10px;
    position: absolute;
    margin-left: 17.5vw;
    margin-top: 1.5vw;
    cursor: pointer;
  }
  
  #description {
    width: 25vw;
    height: 11vh;
  }
  </style>
  