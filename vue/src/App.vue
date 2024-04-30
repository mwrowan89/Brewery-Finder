<template>
  <body>
    <div id="brewery-app">
      <div id="nav" :class="{ hidden: !showNav }">
        <br />
        <img src="./assets/aleAtlasLogoSmall.png" />
        <h3>Ale Atlas</h3>
        <div id="nav-text">
          <ul>
            <li>
              <router-link v-bind:to="{ name: 'home' }">Home</router-link
              >&nbsp;&nbsp;&nbsp;&nbsp;
            </li>
            <li>
              <router-link v-bind:to="{ name: 'about' }">About</router-link
              >&nbsp;&nbsp;&nbsp;&nbsp;
            </li>
            <li>
              <router-link v-bind:to="{ name: 'breweries' }"
                >Brewery List</router-link
              >&nbsp;&nbsp;&nbsp;&nbsp;
            </li>
            <li>
              <router-link v-bind:to="{ name: 'beers' }">Beer List</router-link
              >&nbsp;&nbsp;&nbsp;&nbsp;
            </li>
          </ul>
        </div>
        <div id="login_logout">
          <p></p>
          <router-link
            v-bind:to="{ name: 'userList' }"
            v-if="$store.state.user.username === 'admin'"
            >User List</router-link
          >
          <RouterLink
            v-bind:to="{
              name: 'user',
              params: { id: this.$store.state.user.id },
            }"
            v-if="$store.state.token != ''"
            >My Profile</RouterLink
          >
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
          <router-link
            v-bind:to="{ name: 'login' }"
            v-if="$store.state.token == ''"
            >Login / Sign Up</router-link
          >
        </div>
      </div>
      <router-view />
    </div>
  </body>

  <footer id="footer">
    <p>&nbsp;© 2024 Ale Atlas</p>
  </footer>
</template>

<script>
export default {
  data() {
    return {
      showNav: true,
      lastScrollTop: 0,
      threshold: 200,
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
    this.positionFooter();
  },
  beforeUnmount() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  watch: {
    $route() {
      this.positionFooter();
    },
  },
  methods: {
    handleScroll() {
      var st = document.documentElement.scrollTop;
      if (st > this.lastScrollTop) {
        if (st > this.threshold) {
          this.showNav = false;
        }
      } else {
        this.showNav = true;
      }
      this.lastScrollTop = st <= 0 ? 0 : st;
    },
    positionFooter() {
      var footer = document.getElementById("footer");
      var bodyHeight = document.body.offsetHeight;
      var windowHeight = window.innerHeight;

      if (bodyHeight < windowHeight) {
        footer.style.position = "fixed";
        footer.style.bottom = "0";
        footer.style.left = "0";
        footer.style.width = "100%";
      } else {
        footer.style.position = "static";
      }
    },
  },
};
</script>

<style>
* {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  padding: 0;
  margin: 0;
}
.hidden {
  transform: translateY(-100%);
}
@font-face {
  font-family: "monthoers";
  src: url("Fonts/Monthoers.ttf");
}
h3 {
  font-family: "monthoers";
  font-size: 60px;
  padding-left: 10px;
  padding-top: 10px;
}
body {
  background-color: rgba(214, 191, 147, 0.75);
  background-repeat: no-repeat;
  background-attachment: fixed;
  white-space: nowrap;
  background-size: cover;
  display: flex;
}
#nav {
  display: flex;
  background-color: rgba(0, 0, 0, 0.878);
  position: fixed;
  white-space: nowrap;
  top: 0;
  width: 100vw;
  height: 10%;
  align-items: center;
  color: white;
  transition: transform 0.3s ease;
  z-index: 1;
}
#nav #nav-text a {
  color: white;
  opacity: 100%;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  font-size: 20px;
}
#nav #nav-text ul {
  display: flex;
  flex-wrap: nowrap;
  color: white;
  opacity: 100%;
  text-decoration: none;
  list-style: none;
  margin-left: 45vw;
}
#nav #nav-text li {
  text-decoration: none;
  float: left;
  font-size: larger;
}
#nav img {
  height: 70px;
  top: 0;
  padding-left: 50px;
}
#login_logout {
  margin-left: auto;
  margin-right: 30px;
}
#login_logout a {
  color: white;
  padding: 10px;
  font-size: 20px;
}
footer {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}
footer {
  display: flex;
  justify-content: flex-end;
  background-color: rgba(245, 245, 245, 0.575);
  color: black;
  bottom: 0;
  width: 100vw;
  height: 50px;
  align-items: center;
  font-size: 12px;
  opacity: 100%;
  font-size: 15px;
}
footer p {
  margin-right: 20px;
}
</style>
