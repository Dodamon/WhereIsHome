/*!

=========================================================
* BootstrapVue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/bootstrap-vue-argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from "vue";
import DashboardPlugin from "./plugins/dashboard-plugin";
// import MyPlugin from "./plugins/dashboard-plugin";
import App from "./App.vue";

// 로그인 유지를 위한 쿠키 설정
import VueCookies from "vue-cookies";

// router setup
import router from "./routes/router";
import "bootstrap-vue/dist/bootstrap-vue.css";

// plugin setup
Vue.use(DashboardPlugin);
// Vue.use(MyPlugin);

// cookie setup
Vue.use(VueCookies);
Vue.$cookies.config("7d"); // 쿠키 유효기간 7일로

/* eslint-disable no-new */
new Vue({
  el: "#app",
  render: (h) => h(App),
  router,
});
