<template>
  <body>
    <div id="background">
      <img src="../assets/beer-bubbles.jpeg" />
    </div>
    <div id="login">
      <form v-on:submit.prevent="login">
        <h1>Please Sign In</h1>
        <br />
        <img src="../assets/beer sign.png" />
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
        <button type="submit">Sign in</button>
        <p>
          <router-link v-bind:to="{ name: 'register' }"
            >Need an account? Sign up.</router-link
          >
        </p>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  padding: 10px;
}
label {
  margin-right: 0.5rem;
}
body {
  height: 72vh;
  width: 100vw;
}
#login {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: auto;
  margin-right: auto;
  margin-top: 200px;
  width: fit-content;
  height: fit-content;
  background-color: rgba(245, 222, 179, 0.7);
  text-align: center;
  padding: 10px;
  border-radius: 10px;
}
#login h1 {
  color: white;
}
#login p {
  padding: 20px;
}
img {
  height: 60%;
  width: 60%;
}
#background img {
  margin-top: -11vw;
  height: 100vh;
  width: 100vw;
  z-index: -1;
  position: fixed;
}
</style>
