<template>
  <!-- popup window -->
  <div v-if="showPopup" id="overlay">
    <video autoplay muted loop id="myVideo">
      <source src="../assets/Delirium Beer Pour(1080p).mp4" type="video/mp4" />
    </video>
    <div id="popup">
      <p>Are you over 21?</p>
      <br />
      <button @click="confirmAge">Yes</button>
      <button @click="rejectAge">No</button>
    </div>
  </div>
  <!-- popup window -->
  <div class="main-home">
    <div class="image"></div>
    <div class="text">
      <h2>Welcome to the</h2>
      <h1>Ale Atlas</h1>
      <p>Discover Breweries and Beers Across the USA</p>
    </div>
    <div class="description-images">
      <div class="description">
        <img id="lagunitas" src="https://i.pinimg.com/originals/32/26/90/32269040aa52697d874fcff8f17a041c.png">&nbsp;
        <img id="sierra" src="../assets/SierraNevada.png">&nbsp;
        <img id="lakefront" src="../assets/Lakefront.png">&nbsp;
        <img id="stone" src="../assets/Stone.png">&nbsp;
        <h3 class="description-text">
          Welcome to Explore America's Brews, your ultimate destination for
          discovering breweries and their eclectic offerings spanning the United
          States! With an expansive database boasting over 2000 beers and
          continually growing, we're your go-to platform for all things craft
          beer. Whether you're a seasoned beer enthusiast or just starting your
          journey into the world of craft brews, our intuitive brewery finder
          makes it effortless to locate breweries near you or in your desired
          destination. Explore bustling urban hubs, quaint towns, and scenic
          locales, all while indulging in the diverse flavors of American craft
          beer culture.
        </h3>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showPopup: !localStorage.getItem("ageConfirmed"),
    };
  },
  methods: {
    confirmAge() {
      this.showPopup = false;
      localStorage.setItem("ageConfirmed", true);
    },
    rejectAge() {
      window.location.href =
        "https://www.google.com/search?q=grow+up&sca_esv=37d8bbf9c1461c28&sca_upv=1&rlz=1C5CHFA_enUS1107US1107&biw=1800&bih=932&tbm=vid&sxsrf=ACQVn0_LcXvBRvmYFA6PMtP5nXs21E4N-Q%3A1714497063938&ei=JyYxZsH5OL6ekPIP_4y-yAQ&ved=0ahUKEwiBqdujt-qFAxU-D0QIHX-GD0kQ4dUDCA0&uact=5&oq=grow+up&gs_lp=Eg1nd3Mtd2l6LXZpZGVvIgdncm93IHVwMgoQABiABBhDGIoFMgUQABiABDINEAAYgAQYsQMYgwEYCjIFEAAYgAQyDRAAGIAEGLEDGIMBGAoyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABEj2DVC9A1j-C3ABeACQAQCYAT-gAegBqgEBNLgBA8gBAPgBAZgCBaAC9wHCAgYQABgFGB7CAggQABgFGAoYHsICBhAAGBYYHsICCBAAGBYYChgewgIOEAAYgAQYsQMYgwEYigWYAwCIBgGSBwE1oAfdFQ&sclient=gws-wiz-video#fpstate=ive&vld=cid:aa146b69,vid:m11TVYK0usM,st:0";
    },
    // reset local storage when user leaves page
    clearLocalStorage() {
      localStorage.removeItem("ageConfirmed");
    },
  },
  mounted() {
    window.addEventListener("beforeunload", this.clearLocalStorage);
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.clearLocalStorage);
  },
};
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
.main-home {
  height: 100%;
  background-color: rgba(214, 191, 147, 0.491);
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
  color: white;
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
  z-index: 1;
  background-color: rgba(255, 255, 255, 0.5);
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  margin: 10vw 40vw 0vw 40vw;
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

.text {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: rgb(170, 130, 78);
  background-color: rgba(255, 255, 255, 0.671);
  margin-top: -32vw;
  padding: 30px;
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
  display: inline-block;
  flex-direction: column;
  text-align: center;
  word-wrap: break-word;
  white-space: wrap;
  border-radius: 4px;
  margin: 18vw 20vw 2vw 20vw;
  padding: 30px;
}
.description h3 {
  border: 2px solid white;
  font-size: 30px;
  font-weight: 500;
  padding: 30px;
}
.image {
  height: 80vh;
  width: 100vw;
  background-image: url("https://cdn.brewersassociation.org/wp-content/uploads/2020/06/small-brewery-demographics2.jpg");
  background-position: center;
  background-size: cover;
}

/* brewery images  */
#lagunitas {
  height: 10%;
  width: 10%;
}
#sierra {
  height: 20%;
  width: 20%;
}
#lakefront {
  height: 20%;
  width: 20%;
}
#stone {
  height: 11%;
  width: 11%;
}
</style>
