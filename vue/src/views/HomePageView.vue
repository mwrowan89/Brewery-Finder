<template>
  <!-- popup window -->
  <div v-if="showPopup" id="overlay">
    <video autoplay muted loop id="myVideo">
      <source src="../assets/Delirium Beer Pour(1080p).mp4" type="video/mp4" />
      </video>
      <div id="popup">
        <p>Are you over 21?</p>
        <button @click="confirmAge">Yes</button>
        <button @click="rejectAge">No</button>
      </div>
    </div>
<!-- popup window -->

  <div class="image"></div>
  <div class="text">
    <h2>Welcome to the</h2>
    <h1>Ale Atlas</h1>
    <p>Discover Breweries and Beers Across the USA</p>
  </div>
  <div class="description">
    <h3>Welcome to Explore America's Brews, your ultimate destination for discovering breweries and their eclectic offerings spanning the United States! With an expansive database boasting over 2000 beers and continually growing, we're your go-to platform for all things craft beer.

    Whether you're a seasoned beer enthusiast or just starting your journey into the world of craft brews, our intuitive brewery finder makes it effortless to locate breweries near you or in your desired destination. Explore bustling urban hubs, quaint towns, and scenic locales, all while indulging in the diverse flavors of American craft beer culture.
    </h3>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showPopup: !localStorage.getItem('ageConfirmed')
    }
  },
  methods: {
    confirmAge() {
      this.showPopup = false;
      localStorage.setItem('ageConfirmed', true);
    }
  }
}
</script>

<style scoped>
@font-face {
  font-family: "monthoers";
  src: url("Fonts/Monthoers.ttf");
}
@font-face {
  font-family: "hughs";
  src: url("Fonts/Hughs.woff");
}


/* popup */
#overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
  color: rgb(220, 64, 64);
  font-size: 24px;
  text-align: center;
  padding-top: 200px;
  overflow: hidden;
}
#myVideo {
  position: absolute;
  top: 27vw;
  width: fit-content;
  height: fit-content;
  transform: translate(-50%, -50%);
  z-index: -1;
  border-radius: 25px;
}
#popup {
  position: relative;
  z-index: 1; 
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  margin: auto;
}
#popup button {
  padding: 10px 20px;
  margin: 10px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

#popup button.yes {
  background-color: green;
  color: white;
}

#popup button.no {
  background-color: red;
  color: white;
}
/* popup */

.top {
  background-color: white;
}

.text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: rgb(170, 130, 78);
  background-color: rgba(255, 255, 255, 0.671);
  margin-top: -28vw;
  padding: 20px;
}
.text h1 {
  font-size: 100px;
  font-family: "monthoers";
  font-weight: 800;
  color: rgba(27, 26, 26, 0.829);
}
.text h2 {
  font-family: "hughs";
  font-size: 40px;
}
.text p {
  font-size: 50px;
  font-family: "hughs";
}
.description {
    display: flex;
    text-align: center;
    word-wrap: break-word;
    white-space: wrap;
    border: 2px solid white;
    margin: 17vw 10vw 1.5vw 10vw;
    padding: 20px;
}
.description h3 {
    font-size: 30px;
    font-weight: 500;
}
.image {
  height: 60vh;
  width: 100vw;
  background-image: url("https://cdn.brewersassociation.org/wp-content/uploads/2020/06/small-brewery-demographics2.jpg");
  background-position: center;
  margin-top: 5%;
}
</style>
