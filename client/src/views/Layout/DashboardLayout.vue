<template>
  <div>
    <!-- <notifications></notifications> -->
    <side-bar>
      <template slot="links">
        <!-- <sidebar-item
          :link="{
            name: 'Dashboard',
            path: '/dashboard',
            icon: 'ni ni-tv-2 text-primary',
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: 'Icons',
            path: '/icons',
            icon: 'ni ni-planet text-blue',
          }"
        >
        </sidebar-item> -->
        <!-- 사용자 프로필 사진 클릭시 DropDown -->
        <sicebar-item>
          <b-navbar-nav class="align-items-right ml-auto ml-md-3">
            <!-- <base-dropdown
              menu-on-left
              class="nav-item"
              tag="li"
              title-tag="a"
              title-classes="nav-link pr-0"
            >
              <a
                href="#"
                class="nav-link pr-0"
                @click.prevent
                slot="title-container"
              > -->
            <b-media v-if="id == undefined" no-body class="align-items-center">
              <span class="avatar avatar-xl rounded-circle">
                <img alt="Image placeholder" src="img/theme/team-0.png" />
              </span>
              <b-media-body class="ml-2 d-none d-lg-block">
                <span class="mb-0 text-sm font-weight-bold"
                  >로그인이 필요합니다</span
                >
              </b-media-body>
            </b-media>

            <b-media v-else no-body class="align-items-center">
              <span class="avatar avatar-xl rounded-circle">
                <img alt="Image placeholder" src="img/theme/team-4.jpg" />
              </span>
              <b-media-body class="ml-2 d-none d-lg-block">
                <span class="mb-0 text-sm font-weight-bold">
                  {{ id }}
                </span>
              </b-media-body>
            </b-media>
            <!-- </a> -->
            <!-- Drop Down 부분 -->
            <!-- <template>
                <b-dropdown-header class="noti-title">
                  <h6 class="text-overflow m-0">Welcome!</h6>
                </b-dropdown-header>
                <b-dropdown-item href="#!">
                  <i class="ni ni-single-02"></i>
                  <span>My profile</span>
                </b-dropdown-item>
                <b-dropdown-item href="#!">
                  <i class="ni ni-settings-gear-65"></i>
                  <span>Settings</span>
                </b-dropdown-item>
                <b-dropdown-item href="#!">
                  <i class="ni ni-calendar-grid-58"></i>
                  <span>Activity</span>
                </b-dropdown-item>
                <b-dropdown-item href="#!">
                  <i class="ni ni-support-16"></i>
                  <span>Support</span>
                </b-dropdown-item>
                <div class="dropdown-divider"></div>
                <b-dropdown-item href="#!">
                  <i class="ni ni-user-run"></i>
                  <span>Logout</span>
                </b-dropdown-item>
              </template> -->
            <!-- </base-dropdown> -->
          </b-navbar-nav>
        </sicebar-item>

        <sidebar-item
          :link="{
            name: '지도',
            path: '/maps',
            icon: 'ni ni-pin-3 text-orange',
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '공지사항',
            path: '/tables',
            icon: 'ni ni-bullet-list-67 text-red',
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: 'User Profile',
            path: '/profile',
            icon: 'ni ni-single-02 text-yellow',
          }"
        >
        </sidebar-item>

        <!-- 로그인 되지 않았으면 로그인 버튼 표시 -->
        <sidebar-item
          v-if="id == undefined"
          :link="{
            name: 'Login',
            path: '/login',
            icon: 'ni ni-key-25 text-info',
          }"
        >
        </sidebar-item>

        <!-- 로그인 되지 않았으면 회원가입 버튼 표시 -->
        <sidebar-item
          v-if="id == undefined"
          :link="{
            name: 'Register',
            path: '/register',
            icon: 'ni ni-circle-08 text-pink',
          }"
        >
        </sidebar-item>

        <!-- 로그인 되었으면 로그아웃 버튼 표시 -->
        <div @click="logout">
          <sidebar-item
            v-if="id != undefined"
            :link="{
              name: '로그아웃',
              path: '#',
              icon: 'ni ni-circle-08 text-pink',
              beforeEnter: (to, from, next) => {
                alert('beforeEnter');
              },
            }"
          >
          </sidebar-item>
        </div>
      </template>

      <template slot="links-after">
        <hr class="my-3" />
        <h6 class="navbar-heading p-0 text-muted">Documentation</h6>

        <b-nav class="navbar-nav mb-md-3">
          <b-nav-item
            href="https://www.creative-tim.com/learning-lab/bootstrap-vue/quick-start/argon-dashboard"
          >
            <i class="ni ni-spaceship"></i>
            <b-nav-text class="p-0">Getting started</b-nav-text>
          </b-nav-item>
          <b-nav-item
            href="https://www.creative-tim.com/learning-lab/bootstrap-vue/colors/argon-dashboard"
          >
            <i class="ni ni-palette"></i>
            <b-nav-text class="p-0">Foundation</b-nav-text>
          </b-nav-item>
          <b-nav-item
            href="https://www.creative-tim.com/learning-lab/bootstrap-vue/avatar/argon-dashboard"
          >
            <i class="ni ni-ui-04"></i>
            <b-nav-text class="p-0">Components</b-nav-text>
          </b-nav-item>
        </b-nav>
      </template>
    </side-bar>
    <div class="main-content">
      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
/* eslint-disable no-new */
import PerfectScrollbar from "perfect-scrollbar";
import "perfect-scrollbar/css/perfect-scrollbar.css";

function hasElement(className) {
  return document.getElementsByClassName(className).length > 0;
}

function initScrollbar(className) {
  if (hasElement(className)) {
    new PerfectScrollbar(`.${className}`);
  } else {
    // try to init it later in case this component is loaded async
    setTimeout(() => {
      initScrollbar(className);
    }, 100);
  }
}

import DashboardNavbar from "./DashboardNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import { FadeTransition } from "vue2-transitions";

export default {
  data() {
    return {
      // log_session: this.$cookies.isKey("loggedin"),
      //log_session: this.$session.isKey("loggedin"),
      // id: this.$cookies.get("loggedin"),
      id: this.$session.get("loggedin"),
    };
  },
  components: {
    DashboardNavbar,
    ContentFooter,
    DashboardContent,
    FadeTransition,
  },
  // created() {},
  methods: {
    initScrollbar() {
      let isWindows = navigator.platform.startsWith("Win");
      if (isWindows) {
        initScrollbar("sidenav");
      }
    },
    logout() {
      // this.$cookies.remove("loggedin");
      this.$session.remove("loggedin");
      alert("로그아웃 완료\n새로고침합니다.");
      location.reload();
    },
  },
  mounted() {
    this.initScrollbar();
  },
};
</script>
<style lang="scss"></style>
