<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <!-- <b-container class="container">
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="5" lg="6" md="8" class="px-5">
              <h1 class="text-white">Create an account</h1>
              <p class="text-lead text-white">
                Use these awesome forms to login or create new account in your
                project for free.
              </p>
            </b-col>
          </b-row>
        </div>
      </b-container> -->
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg
          x="0"
          y="0"
          viewBox="0 0 2560 100"
          preserveAspectRatio="none"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            class="fill-default"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->

    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8">
          <b-card no-body class="bg-secondary border-0">
            <!-- <b-card-header class="bg-transparent pb-5">
              <div class="text-muted text-center mt-2 mb-4">
                <small>Sign up with</small>
              </div>
              <div class="text-center">
                <a href="#" class="btn btn-neutral btn-icon mr-4">
                  <span class="btn-inner--icon"
                    ><img src="img/icons/common/github.svg"
                  /></span>
                  <span class="btn-inner--text">Github</span>
                </a>
                <a href="#" class="btn btn-neutral btn-icon">
                  <span class="btn-inner--icon"
                    ><img src="img/icons/common/google.svg"
                  /></span>
                  <span class="btn-inner--text">Google</span>
                </a>
              </div>
            </b-card-header> -->
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <small>????????????</small>
              </div>
              <validation-observer
                v-slot="{ handleSubmit }"
                ref="formValidator"
              >
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <!-- ?????? ?????? ?????? -->
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-circle-08"
                    placeholder="Name"
                    name="Name"
                    :rules="{ required: true }"
                    v-model="model.name"
                  >
                  </base-input>

                  <!-- ?????? ?????? ?????? -->
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-building"
                    placeholder="Address"
                    name="Address"
                    :rules="{ required: true }"
                    v-model="model.address"
                  >
                  </base-input>

                  <!-- ???????????? ?????? ?????? -->
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-mobile-button"
                    placeholder="Phone"
                    name="Phone"
                    :rules="{ required: true }"
                    v-model="model.phone"
                  >
                  </base-input>

                  <!-- ????????? ?????? ?????? -->
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-email-83"
                    placeholder="Email"
                    name="Email"
                    :rules="{ required: true, email: true }"
                    v-model="model.email"
                  >
                  </base-input>

                  <!-- ???????????? ?????? ?????? -->
                  <base-input
                    alternative
                    class="mb-3"
                    prepend-icon="ni ni-lock-circle-open"
                    placeholder="password"
                    type="password"
                    name="Password"
                    :rules="{ required: true, min: 6 }"
                    v-model="model.password"
                  >
                  </base-input>

                  <!-- ???????????? ?????? ?????? ?????? ?????? -->
                  <!-- <div class="text-muted font-italic">
                    <small
                      >password strength:
                      <span class="text-success font-weight-700"
                        >strong</span
                      ></small
                    >
                  </div> -->
                  <b-row class="my-4">
                    <b-col cols="12">
                      <base-input
                        :rules="{ required: { allowFalse: false } }"
                        name="Privacy"
                        Policy
                      >
                        <b-form-checkbox v-model="model.agree">
                          <span class="text-muted">
                            <a href="#!">?????? ??????</a></span
                          >
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>
                  <!-- <b-row>
                    <b-col>
                      <vue-recaptcha sitekey="6LdZlCkjAAAAAL8m_q3clTEd9SXUxAA9AAb4F04V"></vue-recaptcha>
                    </b-col>
                  </b-row> -->

                  <b-row>
                    <b-col>
                      <vue-recaptcha
                        ref="recaptcha"
                        sitekey="6LdZlCkjAAAAAL8m_q3clTEd9SXUxAA9AAb4F04V"
                        @verify="onVerify"
                        @expired="onExpired"
                      >
                      </vue-recaptcha>
                      <!-- <v-spacer></v-spacer> -->
                    </b-col>
                  </b-row>

                  <div class="text-center">
                    <b-button @click="checkRobot" variant="primary" class="mt-4"
                      >????????????</b-button
                    >
                  </div>
                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import http from "@/api/http";
import { VueRecaptcha } from "vue-recaptcha";

export default {
  name: "register",
  components: {
    VueRecaptcha,
  },
  data() {
    return {
      model: {
        name: "",
        email: "",
        password: "",
        address: "",
        phone: "",
        agree: false,
        response: "",
      },
    };
  },
  methods: {
    onSubmit() {
      // alert(
      //   "?????? : " +
      //     this.model.name +
      //     "\n????????? : " +
      //     this.model.email +
      //     "\n???????????? : " +
      //     this.model.password +
      //     "\n?????????????????? : " +
      //     this.model.agree +
      //     "\n--------------------------------" +
      //     "\n?????? ?????? ????????? ???????????? ???????????????."
      // );

      http
        .post("member/checkid", null, {
          params: { id: this.model.email },
        })
        .then(({ data }) => {
          console.log(data);
          if (data == 0) {
            this.register();
          } else {
            alert("?????? ???????????? ??????????????????. ?????? ???????????????.");
          }
          // alert(this.boards[0].title);
        });
      // this will be called only after form is valid. You can do an api call here to register users
    },
    register() {
      http
        .post("member/register", null, {
          params: {
            id: this.model.email,
            pw: this.model.password,
            name: this.model.name,
            phone: this.model.phone,
            address: this.model.address,
          },
        })
        .then(({ data }) => {
          console.log(data);
          if (data == 0) {
            alert("?????? ???????????? ??????????????????. ?????? ???????????????.");
          } else {
            alert("???????????? ??????\n ????????? ???????????? ???????????????");
            this.$router.push({ name: "login" });
          }
          // alert(this.boards[0].title);
        });
    },
    onVerify(r) {
      this.response = r;
    },
    onExpired() {
      this.response = "";
      this.$refs.recaptcha.reset();
    },
    checkRobot() {
      if (this.response) {
        this.onSubmit();
      } else {
        alert("????????? ????????? ??????????????????");
        this.$refs.recaptcha.execute();
      }
    },
  },
};
</script>
<style></style>
