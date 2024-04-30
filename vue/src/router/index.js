import { createRouter as createRouter, createWebHistory } from "vue-router";
import { useStore } from "vuex";

// Import components
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import LogoutView from "../views/LogoutView.vue";
import RegisterView from "../views/RegisterView.vue";
import BreweryDetailsView from "../views/BreweryDetailsView.vue";
import ListOfBeersView from "../views/ListOfBeersView.vue";
import UserProfileView from "../views/UserProfileView.vue";
import BreweriesView from "../views/BreweriesView.vue";
import EditRemoveBeerView from "../views/EditRemoveBeerView.vue";
import EditBreweryView from "../views/EditBreweryView.vue";
import BeerInfoView from "../views/BeerInfoView.vue";
import UserListView from "../views/UserListView.vue";
import HomePageView from "../views/HomePageView.vue";
import AboutView from "../views/AboutView.vue";
import { compile } from "vue";

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: "/",
    name: "home",
    component: HomePageView,
    meta: {
      requiresAuth: false,
    },
  },
  
  {
    path: "/about",
    name: "about",
    component: AboutView
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: "/brewery/:id",
    name: "brewery_details",
    component: BreweryDetailsView,
  },
  {
    path: "/beers",
    name: "beers",
    component: ListOfBeersView,
  },
  {
    path: "/users/:id",
    name: "user",
    component: UserProfileView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/breweries",
    name: "breweries",
    component: BreweriesView,
  },

  // admin use only

  {
    path: "/editBeer/:id",
    name: "editBeer",
    component: EditRemoveBeerView
  },
  {
    path: "/editBrewery/:id",
    name: "editBrewery",
    component: EditBreweryView
  },
  {
    path: "/beerInfo/:id",
    name: "beerInfo",
    component: BeerInfoView
  },
  {
    path: "/admin/userList",
    name: "userList",
    component: UserListView
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
