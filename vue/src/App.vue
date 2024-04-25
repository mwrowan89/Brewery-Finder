<template>
  <div id="capstone-app">
    <div id="nav" v-if="showNav"><br>
      <img src="./assets/aleAtlasLogoSmall.png"/>
      <h3>Ale Atlas</h3>
      <div id="nav-text">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'breweries' }">Brewery List</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'beers' }">Beer List</router-link>&nbsp;|&nbsp;
      </div>
      <div id="login_logout">
        <p> </p>
        <router-link v-bind:to="{ name: 'userList' }" v-if="$store.state.user.username === 'admin'">User List</router-link>
        <RouterLink v-bind:to="{ name: 'user', params: {id: this.$store.state.user.id} }" v-if="$store.state.token != ''">My Profile</RouterLink>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login / Sign Up</router-link>
      </div>
    </div>
    <router-view />
  </div>

  <footer>
  <p>&nbsp;© 2024 Ale Atlas</p>
</footer>
</template>

<script>
export default {
  
  data() {
    return {
      showNav: false,
    };
  },
  watch: {
    $route() {
      this.toggleNav();
    }
  },
  created() {
    this.toggleNav();
  },
  methods: {
    toggleNav() {
      if (this.$route.path === '/') {
        this.showNav = false;
      } else {
        this.showNav = true;
      }
    }
  }

};
</script>

<style>
* {
  font-family: Arial, Helvetica, sans-serif;
  padding: 0;
  margin: 0;
}
body {
  background-color: rgba(245, 235, 215, 0.75);
  background-image: url(https://i.pinimg.com/originals/12/23/99/122399ae99e3350302245e9ebb1c78bd.jpg);
  background-repeat: no-repeat;
  background-attachment: fixed;
  white-space: nowrap;
  background-size: cover;
}
#nav {
  display: flex;
  background-color: #643211;
  position: fixed;
  white-space: nowrap;
  top: 0;
  width: 100vw;
  height: 60px;
  align-items: center;
  color: white;
}
#nav #nav-text {
  color: white;
  padding: 20px;
  opacity: 100%;
}
#nav #nav-text a {
  color: white;
  padding: 20px;
  opacity: 100%;
}
#nav img {
  height: 50px;
  top: 0;
  padding-left: 10px;
}
#login_logout {
  margin-left: auto;
  margin-right: 30px;
}
#login_logout a {
  color: white;
  padding: 10px;
}
footer {
  display: flex;
  justify-content: flex-end;
  color: white;
  bottom: 0;
  width: 99vw;
  height: 30px;
  align-items: center;
  margin-left: -50px;
  font-size: 12px;
  opacity: 100%;
  position: fixed;
}
</style>
