<template>
    <div class="reviews">
      <p id="no-reviews" v-if="reviews.length === 0">No Reviews yet.</p>
      <div v-else>
      <p id="reviews-header">Reviews:</p>
      <ul>
        <li
          class="review"
          v-for="beerReview in reviews"
          v-bind:key="beerReview.id"
        >
          <p>
            {{ getUserName(beerReview.userId) }}: {{ beerReview.review }} <br>
            Rating:
            {{ beerReview.rating.toFixed(1) }}
          </p>
        </li>
      </ul>
      </div>
    </div>
  </template>
  
  <script>
  import BeerReviewService from "../services/BeerReviewService";
  import AuthService from "../services/AuthService";
  
  export default {
    data() {
      return {
        reviews: [],
        users: {},
      };
    },
  
    async created() {
      await this.fetchBeerReviews();
      await this.fetchUsersForReviews();
    },
  
    computed: {},
  
    methods: {
      async fetchBeerReviews() {
        const response = await BeerReviewService.getReviewsByBeerId(
          this.$route.params.id
        );
        this.reviews = response.data;
      },
  
      async fetchUsersForReviews() {
        for (const review of this.reviews) {
          await this.getUser(review.userId);
        }
      },
  
      async getUser(userId) {
        if (!this.users[userId]) {
          const response = await AuthService.getUserById(userId);
          this.users[userId] = response.data;
        }
      },
  
      getUserName(userId) {
        return this.users[userId].username;
      },
    },
  };
  </script>
  
  <style scoped>
  .review {
    display: flex;
    flex-direction: column;
    background-color: rgba(228, 225, 225, 0.725);
    margin: 5px;
    padding: 10px;
    border: 1px solid black;
    border-radius: 5px;
  }
  #reviews-header {
    color: rgb(231, 224, 212);
    font-size: 18px;
  }
  #no-reviews {
    color: rgb(231, 224, 212);
    font-size: 18px;
    text-decoration: overline;
  }
  </style>
  