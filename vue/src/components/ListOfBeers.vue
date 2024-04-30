<template>
  <body class="main-beers">
    <img id="background" src="https://images.squarespace-cdn.com/content/v1/61f86e8e794a883d2fc6880e/8d56d054-5e4d-4622-8ffa-24537eb62cde/Taps+BW+2.jpg" alt="taps">
    <div class="header">
      <h2>Find your favorite type of brew</h2>
      <br />
      <span id="select">
        <h3>Select Beer Type:</h3>
        <select id="beer-filter" v-model="typeFilter" @change="filterTypes">
          <option value=""></option>
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
          <option value="Stout">Stout</option>
        </select>
      </span>
    </div>
    <br />
    <div class="review-form" v-show="showForm === true">
        <form v-on:submit.prevent="addReview(currentBeerId)">
          <h2>Beer:&nbsp;
          {{ beerName }}</h2> <br><br>
          <label for="rating">Rating:</label>
          <select id="rating" name="rating" v-model="beerReview.rating">&nbsp;
            <option value="1">1.0</option>
            <option value="2">2.0</option>
            <option value="3">3.0</option>
            <option value="4">4.0</option>
            <option value="5">5.0</option>
          </select>
          &nbsp;&nbsp;
          <label for="rating">Review:</label>
          <input
            type="text"
            id="rating"
            name="rating"
            v-model="beerReview.review"
          />&nbsp;
          <button type="submit">Submit</button>
        </form>
      </div>
  
    <!-- ADMIN VIEW ONLY -->
    <ul class="hidden-beers" v-if="this.$store.state.user.username == 'admin'">
      <h3>Hidden Beers:</h3>
      <li v-for="beer in filteredBeers" :key="beer.id">
        <div id="beer" v-if="beer.show === false">
          <router-link
            class="brewer-info"
            :to="{ name: 'brewery_details', params: { id: beer.breweryId } }"
          >
            <p>
              #{{ beer.beerId }},
              {{ beer.beerName }}
            </p>
          </router-link>
          <br />
          <div class="return-button">
            <button id="show-beer" v-on:click="showBeer(beer.beerId)">
              Return Beer to List
            </button>
          </div>
        </div>
      </li>
    </ul>
    <!-- ADMIN VIEW ONLY -->
  
    <div>
      <ul class="beer_list">
        <li v-for="beer in filteredBeers" :key="beer.id">
          <div id="beer" v-if="beer.show === true">
            <img class="beer_image" v-if="beer.image" v-bind:src="beer.image" />
            <router-link
              class="brewer-info"
              :to="{ name: 'brewery_details', params: { id: beer.breweryId } }"
            >
              <p>
                {{ beer.beerName }} <br />
                {{ beer.beerType }} <br> ABV:{{
                  beer.beerAbv
                }}%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- <input id="favorite-checkbox" type="checkbox" v-if="$store.state.token != ''"/>
                <label for="favorite-checkbox" v-if="$store.state.token != ''">  Add to Favorites</label> -->
              </p>
            </router-link>
            <div class="add-review-button" v-if="this.$store.state.token != '' && this.$store.state.user.username !== 'admin'">
              <button id="add-review-button" v-on:click="toggleShowForm(beer.beerId)">Add Review</button>
            </div>
  
            <!-- ADMIN VIEW ONLY -->
            <div
              class="buttons"
              v-if="this.$store.state.user.username === 'admin'"
            >
              <router-link
                :to="{ name: 'editBeer', params: { id: beer.beerId } }"
              >
                <button id="edit-beer">&nbsp; Edit &nbsp;</button>
              </router-link>
              &nbsp;
              <button id="remove-beer" v-on:click="hideBeer(beer.beerId)">
                &nbsp;Remove&nbsp;
              </button>
            </div>
            <!-- ADMIN VIEW ONLY -->
          </div>
        </li>
      </ul>
    </div>
    </body>
  </template>
  
  <script>
  import BeerReviewService from "../services/BeerReviewService";
  import BeerService from "../services/BeerService";
  import BreweryService from "../services/BreweryService";
  
  export default {
    data() {
      return {
        beers: [],
        filteredBeers: [],
        typeFilter: "",
        filteredName: "",
        breweries: [],
        filteredBreweries: [],
        stateFilter: "",
        filteredState: "",
        showForm: false,
        beerName: "",
        beerReview: {},
        currentBeerId: 0
      };
    },
    async created() {
      await this.fetchBeers();
      await this.fetchBreweries();
    },
    methods: {
      async fetchBeers() {
        const response = await BeerService.getBeers();
        this.beers = response.data;
        this.filteredBeers = this.beers;
        this.getBeerIcons();
      },
      async fetchBreweries() {
        const response = await BreweryService.getBreweries();
        this.breweries = response.data;
        this.filteredBreweries = this.breweries;
      },
      filterTypes() {
        this.filteredBeers = this.beers.filter((beer) => {
          return beer.beerType
            .toLowerCase()
            .includes(this.typeFilter.toLowerCase());
        });
      },
      filterBreweries() {
        this.filteredBreweries = this.breweries.filter((brewery) => {
          return brewery.state.toLowerCase().includes(this.state.toLowerCase());
        });
        this.filteredState = this.stateFilter.toUpperCase();
      },
      getBeerIcons () {
        this.beers.forEach(beer => {
          if(beer.beerType.toLowerCase()
            .includes('ipa') || beer.beerType.toLowerCase()
            .includes('pale')|| beer.beerType.toLowerCase()
            .includes('blonde')|| beer.beerType.toLowerCase()
            .includes('lager')) {
              beer.image = "../../src/assets/beerIcons/light_beer_1.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('stout')) {
              beer.image = "../../src/assets/beerIcons/dark_beer_2.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('porter')) {
              beer.image = "../../src/assets/beerIcons/med_beer_2.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('amber')) {
              beer.image = "../../src/assets/beerIcons/light_beer_3.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('wheat')) {
              beer.image = "../../src/assets/beerIcons/light_beer_3.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('cider')|| beer.beerType.toLowerCase()
            .includes('seltzer')) {
              beer.image = "../../src/assets/beerIcons/weird_beer_1.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('pilsner')) {
              beer.image = "../../src/assets/beerIcons/light_beer_2.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('brown')) {
              beer.image = "../../src/assets/beerIcons/dark_beer_1.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('red')) {
              beer.image = "../../src/assets/beerIcons/med_beer_1.png";
            }
          else if (beer.beerType.toLowerCase()
            .includes('ale')) {
              beer.image = "../../src/assets/beerIcons/light_beer_2.png";
            }
          else {
            beer.image = "../../src/assets/beerIcons/light_beer_3.png"
          }
        });
      },
      async hideBeer(beerId) {
        try {
          await BeerService.removeBeer(beerId, { show: false });
          this.fetchBeers();
        } catch (error) {
          console.log("Error hiding beer", error);
        }
      },
      async showBeer(beerId) {
        try {
          await BeerService.showBeer(beerId, { show: true });
          this.fetchBeers();
        } catch (error) {
          console.log("Error showing beer", error);
        }
      },
      toggleShowForm(beerId) {
        if (this.showForm === false) {
          this.showForm = true;
          this.getBeerName(beerId);
          this.currentBeerId = beerId;
        } else if (this.showForm === true) {
          this.showForm = false;
        }
      },
      getBeerName(beerId) {
        const beer = this.beers.find((beer) => beer.beerId === beerId);
        if(beer){
          this.beerName = beer.beerName;
          this.beerId = beer.beerId;
        }
      },
      addReview(currentBeerId) {
        this.beerReview.userId = this.$store.state.user.id;
        this.beerReview.beerId = currentBeerId
        BeerReviewService.addReview(this.beerReview);
        this.beerReview = {};
        this.showForm = false;
      }
    },
  };
  </script>
  
  <style scoped>
  .main-beers {
    width: 100vw;
  }
  #background {
    z-index: -1;
    position: fixed;
    height: 101vh;
    width: 100vw;
    margin-top: -5.3vw;
  }
  .beer_list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    list-style-type: none;
    height: max-content;
    width: 100vw;
    
  }
  .header,
  h1 {
    margin-top: 90px;
    color: white;
    text-align: start;
    padding-left: 21vw;
  }
  .beer {
    display: flex;
    background-color: rgba(167, 136, 97, 0.5);
    height: 15vh;
    padding: 10px;
    margin: 5px;
    width: 80vw;
  }
  #beer {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    flex-wrap: wrap;
    background-color: rgba(167, 136, 97, 0.5);
    height: 6vh;
    padding: 10px;
    margin: 5px;
    width: 21vw;
    color: white;
    font-size: 10pt;
    text-decoration: none;
  }
  .brewer-info {
    text-decoration: none;
    color: white;
  }
  #favorite-checkbox {
    place-content: center;
    width: 10px;
    height: 10px;
  }
  #select {
    display: flex;
  }
  #beer-filter {
    margin-left: 10px;
    width: 12vw;
    height: 3vh;
  }
  .beer_image {
    height: 50px;
  }
  #add-review-button {
    display: flex;
    justify-content: center;
    align-items: center;
    width: fit-content;
    height: fit-content;
    padding: 2px;
    background-color: rgba(251, 217, 174, 0.5);
    border: 1px solid white;
    color: white;
    border-radius: 4px;
    position: absolute;
    margin-left: 16vw;
    margin-top: 1.6vw;
    cursor: pointer;
  }
  .add-review-button {
    display: flex;
    justify-content: center;
    align-items: center;
    width: fit-content;
    height: fit-content;
    padding: 2px;
    border-radius: 4px;
    position: absolute;
    margin-left: 10vw;
    margin-top: 1.4vw;
    cursor: pointer;
  }
  /* admin use only */
  
  #add-beer {
    display: flex;
    margin-top: 90px;
    margin-left: 2vw;
    margin-bottom: -100px;
    color: black;
    border: none;
    border-radius: 4px;
    padding: 10px;
    margin-right: 10vw;
    width: fit-content;
    height: fit-content;
    cursor: pointer;
    position: fixed;
  }
  
  .hidden-beers {
    display: flex;
    flex-wrap: wrap;
    color: white;
    background-color: rgb(104, 101, 101);
    justify-content: center;
    align-items: flex-start;
    list-style-type: none;
    height: 80vh;
    width: 24vw;
    position: fixed;
    margin-top: -3vw;
    margin-left: 55vw;
    margin-right: 15px;
    border: 5px solid white;
    overflow-y: auto;
    padding: 8px;
  }
  .return-button {
    display: inline-block;
    align-content: end;
    margin-left: 10vw;
    margin-top: 1.5vw;
    position: absolute;
    cursor: pointer;
  }
  .buttons {
    display: inline-block;
    align-content: end;
    margin-top: -50px;
    margin-left: 200px;
    cursor: pointer;
  }
  #show-beer {
    border: none;
    width: fit-content;
    height: fit-content;
    padding: 4px;
    cursor: pointer;
    margin-left: -4vw;
  }
  .review-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border: 2px solid white;
    background-color: rgb(180, 141, 92);
    border-radius: 4px;
    margin-top: 10px;
    margin-bottom: 8px;
    width: 40vw;
    height: 10vw;
    color: white;
    position: fixed;
    margin-left: 55vw;
  }
  </style>
  