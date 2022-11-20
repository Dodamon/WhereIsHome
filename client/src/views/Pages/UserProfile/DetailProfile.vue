<template>
  <card>
    <b-row align-v="center" slot="header">
      <b-col cols="8">
        <h3 class="mb-0">회원정보 상세보기</h3>
      </b-col>
    </b-row>

    <b-form @submit.prevent="updateProfile">
      <!-- <h6 class="heading-small text-muted mb-4">회원 정보</h6> -->

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="이름"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.name"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="이메일"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.id"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="비밀번호"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.password"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="비밀번호 확인"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.password_check"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="주소"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.address"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col lg="8">
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="전화번호"
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="user.phone"
                disabled
              ></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-button type="button" class="m-1" variant="primary" @click="modify"
            >수정하기</b-button
          >
          <b-button
            type="button"
            class="m-1"
            variant="primary"
            @click="del_account"
            >탈퇴하기</b-button
          >
        </b-row>
      </div>
      <hr class="my-4" />
    </b-form>
  </card>
</template>

<script>
import http from "@/api/http";
// import share from "@/share/share";

export default {
  data() {
    return {
      user: {
        name: "",
        id: "",
        password: "",
        password_check: "",
        address: "",
        phone: "",
      },
    };
  },
  methods: {
    modify() {
      this.$router.push({ name: "profile_modify" });
    },
    del_account() {
      http.post(`member/delete`, null, {
        params: { id: sessionStorage.getItem("loggedin") },
      });

      alert("탈퇴 완료");
      sessionStorage.removeItem("loggedin");

      this.$router.push({ name: "maps" });
      this.$router.go();
    },
  },
  created() {
    http
      .post(`member/userinfo`, null, {
        params: { id: sessionStorage.getItem("loggedin") },
      })
      .then(({ data }) => {
        console.log(data);

        this.user.name = data.name;
        this.user.id = data.id;
        this.user.address = data.address;
        this.user.phone = data.phone;
      });
  },
};
</script>
<style></style>
