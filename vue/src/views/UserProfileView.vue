<template>
  <div class="main">
    <img
      id="background"
      src="https://t3.ftcdn.net/jpg/06/05/74/08/360_F_605740835_FleNdQO4RIijLZ0OPBw5YhMD9ZS5J2XT.jpg"
      alt=""
    />
    <div class="all-info">
      <h3>{{ this.$store.state.user.username }}'s Favorite Breweries</h3>
      <br />
      <h3 v-if="filteredBreweries.length === 0">
        You have no favorite breweries yet
      </h3>
      <ul class="brewery" v-if="filteredBreweries.length !== 0">
        <li v-for="brewery in filteredBreweries" :key="brewery.id">
          <div class="brewer">
            <p id="brewer-info">
              <router-link
                :to="{ name: 'brewery_details', params: { id: brewery.id } }"
              >
                {{ brewery.name }}<br /><br />
                {{ brewery.city }}, {{ brewery.state }}
              </router-link>
              <button id="add-note" v-on:click="toggleForm(brewery.id)">
                Add Note
              </button>
            </p>
            <div
              class="notes"
              v-for="favBrewery in filteredFavoriteBreweries(brewery.id)"
              v-bind:key="favBrewery.breweryId"
            >
              <div v-if="this.$store.state.user.id === favBrewery.userId">
                <p>Notes: {{ favBrewery.notes }}</p>
              </div>
            </div>
            <div class="add-form" v-if="showForm[brewery.id]">
              <form v-on:submit.prevent="addNotes(brewery)">
                <textarea
                  placeholder="Add a note"
                  v-model="favoriteBrewery.notes"
                ></textarea>
                <button id="submit-note" type="submit">Submit</button>
              </form>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import BreweryService from "../services/BreweryService";
import FavoriteBreweriesService from "../services/FavoriteBreweriesService";

export default {
  data() {
    return {
      brewery: {},
      currentBrewery: {},
      filteredBreweries: [],
      showForm: {},
      favoriteBrewery: {},
      allFavoriteBreweries: [],
    };
  },
  async created() {
    await this.fetchFavoriteBreweries();
    await this.fetchAllFavoriteBreweries();
  },
  methods: {
    filteredFavoriteBreweries(breweryId) {
      return this.allFavoriteBreweries
        .filter((favBrewery) => favBrewery.breweryId === breweryId)
        .filter(
          (favBrewery) => favBrewery.userId === this.$store.state.user.id
        );
    },
    async fetchFavoriteBreweries() {
      let userID = this.$store.state.user.id;
      let response = await FavoriteBreweriesService.getFavoriteBreweriesByUser(
        userID
      );
      let favoriteBreweries = response.data;

      for (const favorite of favoriteBreweries) {
        const breweryResponse = await BreweryService.getBrewery(favorite);
        this.filteredBreweries.push(breweryResponse.data);
      }
    },

    async fetchAllFavoriteBreweries() {
      let response = await FavoriteBreweriesService.getAllFavBreweries();
      this.allFavoriteBreweries = response.data;
    },

    toggleForm(breweryId) {
      this.showForm[breweryId] = !this.showForm[breweryId];
    },
    addNotes(brewery) {
      this.favoriteBrewery.userId = this.$store.state.user.id;
      this.favoriteBrewery.breweryId = brewery.id;
      FavoriteBreweriesService.updateFavBreweryNotes(this.favoriteBrewery);
      this.showForm[brewery.id] = false;
      this.favoriteBrewery.notes = "";
      this.fetchAllFavoriteBreweries();
      this.fetchAllFavoriteBreweries();
    },
  },
};
</script>

<style scoped>
#background {
  z-index: -1;
  position: fixed;
  height: 100vh;
  width: 100vw;
  margin: 0;
  padding: 0;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
.main {
  height: 100%;
  width: 100%;
}
.all-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 2vw 2vw 1vw 40vw ;
}
h3 {
  display: flex;
  justify-content: center;
  color: white;
  margin-top: 10%;
}
.brewery {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  list-style-type: none;
}
#brewer-info {
  width: 20vw;
}
.brewery .brewer {
  display: flex;
  background-color: rgba(167, 136, 97, 0.5);
  height: 14vh;
  padding: 10px;
  margin: 5px;
  width: 50vw;
  font: 20px;
}

.brewer p {
  color: white;
  font-size: 16pt;
}

.brewery .brewer:hover {
  background-color: rgba(167, 136, 97, 1);
}
a {
  text-decoration: none;
  color: white;
}
button {
  display: flex;
  place-content: flex-end;
  justify-content: flex-end;
  align-items: flex-end;
}

form {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-end;
  margin-top: -12vh;
}

textarea {
  display: flex;
  width: 10vw;
  height: 8vh;
  position: absolute;
  margin-bottom: 1.3vw;
}
#submit-note {
  padding: 4px;
  margin-bottom: -0.4vw;
}
.brewery .brewer #add-note {
  background-color: rgba(53, 44, 33, 0.5);
  color: white;
  border: none;
  padding: 5px;
  margin-top: 13px;
  margin-left: 10px;
}
.brewery .brewer .add-form {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-end;
  position: relative;
}
.brewery .brewer .notes {
  display: flex;
  flex-wrap: wrap;
  border: 2px solid white;
  width: 25vw;
  height: 80px;
  padding: 10px;
  contain: content;
  word-wrap: break-word;
  overflow-wrap: break-word;
  white-space: pre-wrap;
  color: white;
  font-size: 12pt;
  position: absolute;
  margin-left: 23.2vw;
  margin-top: 8px;
}
</style>
