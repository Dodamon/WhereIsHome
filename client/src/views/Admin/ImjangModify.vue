<template>
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">임장 모임 수정</h3>
    </b-card-header>

    <b-container>
      <b-row>
        <b-col style="text-align: left">
          <b-form @submit="modify">
            <b-form-group label="제목" label-for="title">
              <b-form-input
                id="title"
                placeholder="제목"
                v-model="imjang.title"
              ></b-form-input>
            </b-form-group>

            <!-- <b-form-group label="날짜" label-for="writer">
              <b-form-input id="writer" v-model="imjang.date"></b-form-input>
            </b-form-group> -->

            <div>
              <b-button type="submit" class="m-1" variant="primary"
                >수정</b-button
              >
              <b-button
                type="reset"
                class="m-1"
                variant="danger"
                @click="deleteImjang"
                >삭제</b-button
              >
              <b-button @click="gotoList">목록</b-button>
            </div>
          </b-form>
        </b-col>
      </b-row>
    </b-container>

    <!-- 페이지 네비게이션 -->
    <b-card-footer class="py-4 d-flex justify-content-end"> </b-card-footer>
  </b-card>
</template>

<script>
import http from "@/api/http";

export default {
  name: "board-table",
  data() {
    return {
      imjang: Object,
      code: 0,
    };
  },
  methods: {
    modify() {
      http
        .post("admin/modifyImjang", null, {
          params: {
            code: this.code,
            title: this.imjang.title,
          },
        })
        .catch();
      alert("게시글 수정 완료");
      this.$router.push({ name: "admin" });
    },
    gotoList() {
      this.$router.push({ name: "admin" });
    },
    deleteImjang() {
      http
        .post("admin/deleteImjang", null, {
          params: {
            code: this.code,
          },
        })
        .catch();
      alert("삭제 완료");
      this.$router.push({ name: "admin" });
    },
  },
  created() {
    this.code = this.$route.params.code;
    http
      .post(`admin/clickImjang`, null, {
        params: { code: this.code },
      })
      .then(({ data }) => {
        this.imjang = data;
      });
  },
};
</script>
