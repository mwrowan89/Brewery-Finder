<template>
  <div class="brewery-list">
    <img
      id="background"
      src="https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_562,q_75,w_1920/v1/clients/johnstoncounty/Hatchet_Brewery_Floor_Web_Header_2e942773-1e28-4fc1-a5c0-ccce98885a27.jpg"
    />
    <div class="header">
      <h3>Discover your favorite brewery with The Ale Atlas!</h3>
      <br />
      <div class="filters">
        <label for="state">Filter by State: </label>
        <input
          type="text"
          id="state"
          v-model="state"
          v-on:input="filterBreweries"
          placeholder="Enter state name"
        />
        &nbsp;<label for="rating">Filter by Rating: </label>
        <select id="rating" v-model="rating" @change="filterRatings">
          <option value="3">3.0 - 3.5</option>
          <option value="3.5">3.5 - 4.0</option>
          <option value="4">4.0 - 4.5</option>
          <option value="4.5">4.5 - 5.0</option>
        </select>
      </div>
    </div>

    <h1>List of Breweries {{ state }} {{ filteredState }}</h1>

    <ul class="brewery">
      <li v-for="brewery in displayedBreweries" :key="brewery.id">
        <div class="brewer" v-bind:class="{ favorited: brewery.isFavorite }">
          <router-link
            class="brewer-info"
            :to="{ name: 'brewery_details', params: { id: brewery.id } }"
          >
            <img v-bind:src="brewery.img" />
            <div id="name-and-place">
              <p>{{ brewery.name }}</p>
              <p>{{ brewery.city }}, {{ brewery.state }}</p>
              <p id="rating" v-if="brewery.rating">
                Rating: {{ brewery.rating }}
              </p>
            </div>
          </router-link>

          <button
            id="heart"
            v-if="brewery.isFavorite && this.$store.state.token != ''"
            v-on:click="toggleFavorite(brewery.id)"
            style="background-image: url('../../src/assets/heart_full_red.png')"
          >
            <!-- <img src="../assets/heart_full_red.png"/> -->
          </button>
          <button
            id="heart"
            v-if="!brewery.isFavorite && this.$store.state.token != ''"
            v-on:click="toggleFavorite(brewery.id)"
            style="background-image: url('../../src/assets/heart_hollow.png')"
          >
            <!-- <img src="../assets/heart_hollow.png"/> -->
          </button>
        </div>
      </li>
    </ul>
    <div class="next-buttons">
      <button
        id="previousPage"
        @click="showPreviousPage"
        v-bind:class="{ disabled: previousButtonDisable }"
      >
        Previous Page
      </button>

      <button
        class="page-numbers"
        v-for="pageNumber in totalPages"
        :key="pageNumber"
        v-bind:class="{ 'current-page': isCurrentPage(pageNumber) }"
        @click="goToPage(pageNumber)"
      >
        {{ pageNumber }}
      </button>

      <button
        id="nextPage"
        @click="showNextPage"
        v-bind:class="{ disabled: nextButtonDisable }"
      >
        Next Page
      </button>
    </div>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
import FavoriteBreweriesService from "../services/FavoriteBreweriesService";

export default {
  data() {
    return {
      breweries: [],
      beers: [],
      filteredBreweries: [],
      stateFilter: "",
      filteredState: "",
      favoriteBrewery: {},
      state: "",
      breweriesPerPage: 18,
      currentPage: 1,
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
          this.state === "";
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
    showNextPage() {
      this.currentPage++;
    },
    showPreviousPage() {
      this.currentPage--;
    },
    isCurrentPage(pageNumber) {
      return this.currentPage === pageNumber;
    },
    goToPage(pageNumber) {
      this.currentPage = pageNumber;
    },
  },
  computed: {
    totalBreweries() {
      return this.filteredBreweries.length;
    },
    totalPages() {
      return Math.ceil(this.totalBreweries / this.breweriesPerPage);
    },
    displayedBreweries() {
      if (this.state === "") {
        const startIndex = (this.currentPage - 1) * this.breweriesPerPage;
        const endIndex = startIndex + this.breweriesPerPage;
        return this.filteredBreweries.slice(startIndex, endIndex);
      }
      return this.filteredBreweries;
    },
    previousButtonDisable() {
      return this.currentPage <= 1;
    },
    nextButtonDisable() {
      return this.totalPages == 1 || this.totalPages == this.currentPage;
    },
  },
};
</script>

<style scoped>
.brewery {
  padding-top: 20px;
}
#background {
  z-index: -1;
  position: fixed;
  height: 100vh;
  margin-top: -6vw;
}
.brewery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  width: 100vw;
  margin-left: auto;
  margin-right: auto;
  list-style-type: none;
}
.header {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
  margin-left: auto;
  margin-right: auto;
  margin-top: 6vw;
  border: 2px solid white;
  width: min-content;
  height: min-content;
  padding: 30px;
  background-color: rgba(4, 4, 4, 0.682);
}
h1 {
  color: white;
  display: flex;
  justify-content: center;
}
.brewery .brewer,
.brewery .brewer-info {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}
.brewery .brewer-info {
  flex-grow: 5;
  text-decoration: none;
}
.brewery .brewer img {
  padding-right: 15px;
  height: 10vh;
  width: 5vw;
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
  background-color: rgba(192, 191, 189, 0.84);
  height: 10vh;
  padding: 10px;
  margin: 5px;
  width: 28vw;
}
.brewery .brewer:hover {
  background-color: rgba(192, 191, 189, 0.763);
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
#nextPage,
#previousPage {
  background: rgba(9, 9, 9, 0.776);
  width: fit-content;
  height: fit-content;
  padding: 10px;
  margin: 1vw 10vw 3vw 10vw;
  cursor: pointer;
  border-radius: 4px;
}
.next-buttons {
  display: flex;
  justify-content: center;
}
.disabled {
  color: #ccc;
  pointer-events: none;
}
.page-numbers {
  display: flex;
  color: white;
  background-color: rgba(9, 9, 9, 0.776);
  width: min-content;
  height: min-content;
  list-style: none;
  font-size: 20px;
  padding: 10px;
  margin-top: 1vw;
  border-radius: 8px;
}
.current-page {
  background-color: #e0e0e0b6;
  font-weight: bold;
}
button {
  width: min-content;
  height: min-content;
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
  background-color: rgba(189, 171, 149, 0);
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
