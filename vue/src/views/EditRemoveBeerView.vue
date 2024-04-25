<template>
    <div class="form">
      <form v-on:submit.prevent="updateBeer(filteredBeer)" class="form-elements">
        <label for="name">Name</label>
        <input
          type="text"
          id="name"
          name="name"
          v-model="filteredBeer.beerName"
        /><br />
        <label for="type">Type</label>
        <input
          type="text"
          id="type"
          name="type"
          v-model="filteredBeer.beerType"
        /><br />
        <label for="abv">ABV</label>
        <input
          type="text"
          id="abv"
          name="abv"
          v-model="filteredBeer.beerAbv"
        /><br />
        <label for="description">Description</label>
        <textarea
          type="text"
          id="description"
          name="description"
          v-model="filteredBeer.beerDescription"
        ></textarea
        ><br />
        <button type="submit">Submit</button>
      </form>
      <div class="success-message" v-if="updateMessage">
        <h1>{{ updateMessage }}</h1>
          <router-link to="/beers">Back to Beers</router-link>
      </div>
    </div>
  </template>
  
  <script>
  import BeerService from "../services/BeerService";
  
  export default {
    data() {
      return {
        beer: {
          name: "",
          type: "",
          abv: "",
          brewery: "",
          description: "",
        },
        filteredBeer: {
          beerId: "",
          beerName: "",
          beerType: "",
          beerAbv: "",
          beerDescription: "",
        },
        updateMessage: "",
      };
    },
    created() {
      this.fetchBeers();
    },
  
    methods: {
      async fetchBeers() {
        const response = await BeerService.getBeerById(this.$route.params.id);
        this.filteredBeer = response.data;
        console.log(this.filteredBeer);
      },
  
      updateBeer(filteredBeer) {
        BeerService.updateBeer(filteredBeer, filteredBeer.beerId);
        this.updateMessage = "Beer updated successfully!";
      },
    },
  };
  </script>
  
  <style scoped>
  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 150px;
    width: 100vw;
    color: white;
  }
  textarea {
    width: 300px;
    height: 100px;
  }
  .form-elements {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  button {
    padding: 5px;
    margin-bottom: 20px;
    width: 200px;
    height: 30px;
  }
  .success-message h1,
  .success-message a {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  </style>
  