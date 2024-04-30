<template>
  <body class="main">
    <img src="https://teachrare.org/wp-content/uploads/2023/12/rare-disease-beer.webp" alt="retro beer">
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
    </body>
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
  .main {
    background-color: rgba(214, 191, 147, 0.491);
    height: 100vh;
    width: 100vw;
    margin: 0; 
    padding: 0; 
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
  }
  img {
    z-index: -1;
    position: fixed;
    height: 100vh;
    width: 100vw;
  }
  .beerInfo {
    display: flex;
    flex-direction: column;
    margin-top: 7vw;
    margin-left: 3vw;
    padding: 20px;
    border: 1px solid black;
    border-radius: 10px;
    background-color: rgba(0, 0, 0, 0.801);
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
    color: rgb(250, 250, 250);
  }
  </style>
  