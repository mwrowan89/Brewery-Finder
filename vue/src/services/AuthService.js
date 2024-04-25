import axios from "axios";

export default {
  login(user) {
    return axios.post("/login", user);
  },

  register(user) {
    return axios.post("/register", user);
  },

  getUserById(id) {
    return axios.get(`/user/${id}`);
  },
  getAllUsers() {
    return axios.get("/admin/userList");
  },
  deleteUser(id) {
    return axios.delete(`/admin/userList/delete/${id}`);
  },
  getAllReviews() {
    return axios.get(`admin/userList/reviews/`);
  },
  deleteReview(id) {
    return axios.delete(`/admin/userList/deleteReview/${id}`);
  },
};
