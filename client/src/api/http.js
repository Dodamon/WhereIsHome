import axios from "axios";
axios.defaults.withCredentials = true;

export default axios.create({
  baseURL: "http://127.0.0.1:9999/",
  headers: {
    "Content-type": "application/json",
  },
});
