<!-- <template>
  <div>
    <!-- nav bar 추가한부분 -->
    <b-navbar
      toggleable="lg"
      type="dark"
      variant="default"
      class="navbar-horizontal"
    >
      <b-navbar-brand href="#">DEFAULT COLOR</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <!-- 사용자 프로필 사진 -->
            <b-nav-item-dropdown right>
              <template v-slot:button-content>
                <i class="ni ni-settings-gear-65"></i>
                <span class="nav-link-inner--text d-lg-none">Settings</span>
              </template>
              <b-dropdown-item href="#">Action</b-dropdown-item>
              <b-dropdown-item href="#">Another action</b-dropdown-item>
              <b-dropdown-divider></b-dropdown-divider>
              <b-dropdown-item href="#">Something else here</b-dropdown-item>
            </b-nav-item-dropdown>

            <!-- 사용자 프로필 사진 클릭시 DropDown -->
            <b-navbar-nav class="align-items-center ml-auto ml-md-0">
              <base-dropdown
                menu-on-right
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
                >
                  <b-media no-body class="align-items-center">
                    <span class="avatar avatar-sm rounded-circle">
                      <img alt="Image placeholder" src="img/theme/team-4.jpg" />
                    </span>
                    <b-media-body class="ml-2 d-none d-lg-block">
                      <span class="mb-0 text-sm font-weight-bold"
                        >John Snow</span
                      >
                    </b-media-body>
                  </b-media>
                </a>

                <template>
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
                </template>
              </base-dropdown>
            </b-navbar-nav>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <!-- nav bar origin template -->
  </div>
</template>
<script>
import { CollapseTransition } from "vue2-transitions";
import { BaseNav, Modal } from "@/components";

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal,
  },
  props: {
    type: {
      type: String,
      default: "default", // default|light
      description:
        "Look of the dashboard navbar. Default (Green) or light (gray)",
    },
  },
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
  },
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: "",
    };
  },
  methods: {
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
  },
};
</script> -->
