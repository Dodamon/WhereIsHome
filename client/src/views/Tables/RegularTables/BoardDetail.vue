<template>
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">글보기</h3>
    </b-card-header>

    <b-container>
      <b-row>
        <b-col style="text-align: left">
          <b-card>
            <b-card-body>
              <div>제목 : {{ article.title }}</div>
            </b-card-body>
            <b-card-body>
              <div>작성자 : {{ article.writer }}</div>
            </b-card-body>
            <b-card-body>
              <div>내용 : {{ article.content }}</div>
            </b-card-body>
          </b-card>
          <router-link :to="{ name: 'modify', params: { code: code } }">
            <b-button variant="primary">수정</b-button></router-link
          >
          <b-button variant="danger" @click="deleteArticle">삭제</b-button>
          <b-button @click="gotoList">목록</b-button>
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
      article: { writer: "", title: "", content: "" },
      code: 0,
    };
  },
  methods: {
    write() {
      console.log(this.article.title);
      console.log(this.article.writer);
      console.log(this.article.content);

      http
        .post("board/write", null, {
          params: {
            writer: this.article.writer,
            title: this.article.title,
            content: this.article.content,
          },
        })
        .catch();
      this.$router.push({ name: "list" });
    },
    deleteArticle() {
      http
        .post("board/deleteArticle", null, {
          params: {
            code: this.code,
          },
        })
        .catch();
      alert("삭제 완료");
      this.$router.push({ name: "list" });
    },
    gotoList() {
      this.$router.push({ name: "list" });
    },
  },
  created() {
    this.code = this.$route.params.code;
    http
      .post(`board/clickArticle`, null, {
        params: { code: this.code },
      })
      .then(({ data }) => {
        this.article = data;
      });
  },
};
</script>
