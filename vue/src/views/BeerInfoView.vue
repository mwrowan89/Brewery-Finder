<template>
    <div class="beerInfo">
      <h1 id="beer-name">{{ filteredBeer.beerName }}</h1>
      <p>
        {{ filteredBeer.beerType }}, &nbsp;ABV %{{ filteredBeer.beerAbv }},
        &nbsp;IBU {{ filteredBeer.beerIbu }}
      </p><br>
      <p>{{ filteredBeer.beerDescription }}</p>
      <br />
      <ListOfReviews></ListOfReviews>
    </div>
  </template>
  
  <script>
  import BeerService from "../services/BeerService";
  import ListOfReviews from "../components/ListOfReviews.vue";
  
  export default {
    components: {
      ListOfReviews,
    },
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
          beerName: "",
          beerType: "",
          beerAbv: "",
          beerDescription: "",
        },
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
    },
  };
  </script>
  
  <style scoped>
  .beerInfo {
    display: flex;
    flex-direction: column;
    margin-top: 100px;
    margin-left: 3vw;
    padding: 20px;
    border: 1px solid black;
    border-radius: 10px;
    background-color: rgba(255, 230, 172, 0.472);
    width: fit-content;
    max-width: 40vw;
    contain: content;
    word-wrap: break-word;
    overflow-wrap: break-word;
    white-space: pre-wrap;
    color: white;
  }
  #beer-name {
    display: flex;
    justify-content: center;
    font-size: 30px;
    margin-bottom: 10px;
    text-decoration: underline;
    color: rgb(231, 224, 212);
  }
  </style>
  