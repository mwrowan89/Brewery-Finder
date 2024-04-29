<template>
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

<style scoped>
@font-face {
  font-family: "monthoers";
  src: url("Fonts/Monthoers.ttf");
}
@font-face {
  font-family: "hughs";
  src: url("Fonts/Hughs.woff");
}

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
    margin: 17vw;
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
