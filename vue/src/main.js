import { createApp } from 'vue'
import BreweryApp from './App.vue'
import { createStore } from './store'
import router from './router'
import axios from 'axios'

axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

let currentToken = localStorage.getItem('token');
let currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken) {
  // Set token axios requests
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

// Create the Vuex store passing in the stored credentials
const store = createStore(currentToken, currentUser);

const app = createApp(BreweryApp);
app.use(store);
app.use(router);
app.mount('#app');