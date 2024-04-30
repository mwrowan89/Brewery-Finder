<template>
  <img id="background" src="https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_562,q_75,w_1920/v1/clients/johnstoncounty/Hatchet_Brewery_Floor_Web_Header_2e942773-1e28-4fc1-a5c0-ccce98885a27.jpg">
    <div class="header">
      <br />
      <h2>Discover your favorite brew with The Ale Atlas!</h2>
      <br />
      <label for="state">Filter by State</label>
      <input
        type="text"
        id="state"
        v-model="state"
        v-on:input="filterBreweries"
        placeholder="Enter state name"
      />
  
      &nbsp;<label for="rating">Filter by Rating</label>
      <select id="rating" v-model="rating" @change="filterRatings">
        <option value="3">3.0 - 3.5</option>
        <option value="3.5">3.5 - 4.0</option>
        <option value="4">4.0 - 4.5</option>
        <option value="4.5">4.5 - 5.0</option>
      </select>
    </div>
  
    <h1>List of Breweries {{ filteredState }}</h1>
  
    <ul class="brewery">
      <li v-for="brewery in filteredBreweries" :key="brewery.id">
        <div class="brewer" v-bind:class="{favorited: brewery.isFavorite}">
          <router-link
            class="brewer-info"
            :to="{ name: 'brewery_details', params: { id: brewery.id } }"
          >
            <img v-bind:src="brewery.img" />
            <div id="name-and-place">
              <p>{{ brewery.name }}</p>
              <p>{{ brewery.city }}, {{ brewery.state }}</p>
              <p id="rating">Rating: {{ brewery.rating }}</p>
            </div>
          </router-link>
          
          <button id="heart"
            v-if="brewery.isFavorite && this.$store.state.token != ''"
            v-on:click="toggleFavorite(brewery.id)"
            style="background-image: url('../../src/assets/heart_full_red.png');"
          >
            <!-- <img src="../assets/heart_full_red.png"/> -->
          </button>
          <button id="heart"
            v-if="!brewery.isFavorite && this.$store.state.token != ''"
            v-on:click="toggleFavorite(brewery.id)"
            style="background-image: url('../../src/assets/heart_hollow.png');" 
          >
            <!-- <img src="../assets/heart_hollow.png"/> -->
          </button>
        </div>
      </li>
    </ul>
  
  </template>
  
  <script>
  import BreweryService from "../services/BreweryService";
  import BeerService from "../services/BeerService";
  import FavoriteBreweriesService from "../services/FavoriteBreweriesService";
  
  export default {
    data() {
      return {
        breweries: [],
        beers: [],
        filteredBeers: [],
        typeFilter: "",
        filteredBreweries: [],
        stateFilter: " ",
        filteredState: "",
        favoriteBrewery: {},
        state: "",
      };
    },
    async created() {
      await this.fetchBreweries();
      await this.getFavoriteBreweries();
    },
  
    methods: {
      async fetchBreweries() {
        const response = await BreweryService.getBreweries();
        this.breweries = response.data;
        this.filteredBreweries = this.breweries;
      },
      async fetchBeers() {
        const response = await BeerService.getBeers();
        this.beers = response.data;
        this.filteredBeers = this.beers;
      },
      async getFavoriteBreweries() {
        const response =
          await FavoriteBreweriesService.getFavoriteBreweriesByUser(
            this.$store.state.user.id
          );
        this.breweryList = response.data;
  
        this.filteredBreweries.forEach((brewery) => {
          if (this.breweryList.includes(brewery.id)) {
            brewery.isFavorite = true;
          } else {
            brewery.isFavorite = false;
          }
        });
      },
      filterTypes() {
        this.filteredBreweries = this.breweries.filter((brewery) => {
          return brewery.beers.filter((beer) => {
            return beer.beerType
              .toLowerCase()
              .includes(this.typeFilter.toLowerCase());
          });
        });
  
      },
      filterBreweries() {
        this.filteredBreweries = this.breweries.filter((brewery) => {
          return brewery.state.toLowerCase().includes(this.state.toLowerCase());
        });
        this.filteredState = this.stateFilter.toUpperCase();
      },
      filterRatings() {
        this.filteredBreweries = this.breweries.filter((brewery) => {
          const rating = parseFloat(this.rating);
          const stateMatch =
            brewery.state.toLowerCase().includes(this.state.toLowerCase()) ||
            this.state === " ";
          const ratingMatch =
            brewery.rating >= rating &&
            brewery.rating < rating + 0.5 &&
            stateMatch;
          return ratingMatch;
        });
      },
      async toggleFavorite(breweryId) {
        await this.getFavoriteBreweries();
  
        let isFavorite = false;
        for (const favorite of this.breweryList) {
          if (favorite === breweryId) {
            const deleteFavorite = {
              userId: this.$store.state.user.id,
              breweryId: breweryId,
            };
            console.log(deleteFavorite);
            await FavoriteBreweriesService.deleteFavoriteBrewery(deleteFavorite);
            this.filteredBreweries.forEach((brewery) => {
              if (brewery.id === breweryId) {
                brewery.isFavorite = false;
              }
            });
            isFavorite = true;
            break;
          }
        }
        if (!isFavorite) {
          const newFavorite = {
            userId: this.$store.state.user.id,
            breweryId: breweryId,
          };
          await FavoriteBreweriesService.addFavoriteBrewery(newFavorite);
          this.filteredBreweries.forEach((brewery) => {
            if (brewery.id === breweryId) {
              brewery.isFavorite = true;
            }
          });
        }
      },
    },
  };
  </script>
  
  <style scoped>
  #background {
    z-index: -1;
    position: fixed;
    height: 100vh;
  }
  .brewery {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    width: 100vw;
    margin-left: auto;
    margin-right: auto;
    list-style-type: none;
    margin-left: 9vw;
  }
  .header {
    display: flex;
    flex-wrap: wrap;
    flex-direction: column;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
    padding-right: 37vw;
  }
  h1 {
    color: white;
    padding-left: 23.5vw;
  }
  .brewery .brewer,
  .brewery .brewer-info {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
  }
  .brewery .brewer-info {
    flex-grow: 5;
  }
  .brewery .brewer img {
    padding-right: 15px;
    height: 15vh;
    width: 7vw;
  }
  .brewery .brewer #name-and-place {
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    text-align: left;
  }
  .brewery .brewer #name-and-place p {
    color: white;
    font-size: 15pt;
  }
  .brewery .brewer {
    background-color: rgba(167, 136, 97, 0.5);
    height: 15vh;
    padding: 10px;
    margin: 5px;
    width: 40vw;
  }
  .brewery .brewer:hover {
    background-color: rgba(167, 136, 97, 1);
  }
  .brewery .brewer #checkbox {
    margin-left: 40px;
    width: 30px;
    height: 30px;
  }
  .brewery .favorited {
    background-color: rgba(168, 126, 20, 0.5);
  }
  .brewery .favorited:hover {
    background-color: rgba(168, 126, 20, 1);
  }
  
  #name-and-place #rating {
    color: white;
    font-size: 10pt;
  }
  
  #heart {
    width: 14vh;
    scale: 0.5;
    margin-top: -25px;
    margin-right: -20px;
  }
  h2 {
  margin-top: 70px;
}
  
  @media screen and (max-width: 1200px) {
    .brewery {
      flex-direction: column;
      align-items: center;
    }
    .brewery .brewer {
      width: 60vw;
    }
  }
  .header {
    color: white;
    margin-bottom: 5px;
  }
  label {
    margin-bottom: 8px;
  }
  /* admin use only */
  
  button {
    background-color: rgba(189, 171, 149, 0.0);
    color: white;
    border: none;
    padding: 10px;
    margin: 5px;
    /* width: fit-content; */
    height: 100px;
    width: 100px;
    cursor: pointer;
    background-repeat: no-repeat;
    background-size: 100px 100px;
  }
  </style>
  