<template>
  <div>
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
            <li class="nav-item">
              <a class="nav-link nav-link-icon" href="#">
                <i class="ni ni-favourite-28"></i>
                <span class="nav-link-inner--text d-lg-none">Discover</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-link-icon" href="#">
                <i class="ni ni-notification-70"></i>
                <span class="nav-link-inner--text d-lg-none">Profile</span>
              </a>
            </li>
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
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>
<script></script>
<style></style>
