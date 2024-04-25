<template>
    <div class="userList">
      <h1>Users</h1>
      <br />
      <ul v-for="user in users" :key="user.id">
        <li v-if="user.username !== 'admin' && user.username !== 'user'">
          {{ user.username }}: {{ user.authorities[0].name }} &nbsp;
          <button v-on:click="deleteUser(user.id)">Delete User</button>
          <p> &nbsp;</p>
          <ol class="list-of-reviews">
            <li v-for="review in getUserReviews(user.id)" :key="review.id">
              {{ review.review }}
              <button v-if="showDelete" v-on:click="deleteReview(review.id)">Delete Review</button>
            </li>
          </ol>
          <br />
        </li>
      </ul>
      <h2>{{ this.message }}</h2>
    </div>
  </template>
  
  <script>
  import AuthService from "../services/AuthService";
  
  export default {
    data() {
      return {
        users: [],
        reviews: [],
        message: "",
        showDelete: true
      };
    },
    created() {
      this.fetchUsers();
      this.fetchReviews();
    },
    methods: {
      async fetchUsers() {
        const response = await AuthService.getAllUsers();
        this.users = response.data;
      },
      async fetchReviews() {
        const response = await AuthService.getAllReviews();
        this.reviews = response.data;
        console.log(this.reviews)
      },
      async deleteUser(id) {
        await AuthService.deleteUser(id);
        this.message = "User #" + id + "deleted";
        this.fetchUsers();
      },
      async deleteReview(id) {
        await AuthService.deleteReview(id);
        this.message = "Review #" + id + "deleted";
        this.fetchReviews();
      },
    },
    computed: {
    getUserReviews() {
      return (userId) => {
        const userReviews = this.reviews.filter((review) => review.userId === userId);
        if (userReviews.length > 0) {
          this.showDelete = true;
          return userReviews;
        } else {
          this.showDelete = false;
          return [{ review: "No Reviews Found" }];
        }
      };
    },
  },
  };
  </script>
  
  <style scoped>
  .userList {
    display: flex;
    flex-direction: column;
    margin-top: 100px;
    margin-left: 3vw;
    padding: 20px;
    color: white;
  }
  .userList li {
    font-size: 20px;
    margin: 5px;
  }
  .list-of-reviews {
    width: 20vw;
    min-height: 10vw;
    height: fit-content;
    contain: content;
    border: 1px solid white;
    margin-left: 20px;
    padding: 10px;
  }
  .list-of-reviews li{
      margin-left: 30px;
      padding: 3px;
  }
  </style>
  