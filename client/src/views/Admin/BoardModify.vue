<template>
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">글수정</h3>
    </b-card-header>

    <b-container>
      <b-row>
        <b-col style="text-align: left">
          <b-form @submit="modify">
            <b-form-group label="제목" label-for="title">
              <b-form-input
                id="title"
                placeholder="제목"
                v-model="article.title"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="작성자" label-for="writer">
              <b-form-input
                id="writer"
                placeholder="작성자"
                v-model="article.writer"
              ></b-form-input>
            </b-form-group>

            <b-form-group label="내용" label-for="content">
              <b-form-textarea
                id="content"
                placeholder="내용"
                v-model="article.content"
              ></b-form-textarea>
            </b-form-group>

            <div>
              <b-button type="submit" class="m-1" variant="primary"
                >수정</b-button
              >
              <b-button
                type="reset"
                class="m-1"
                variant="danger"
                @click="deleteArticle"
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
      article: { writer: "", title: "", content: "" },
      code: 0,
    };
  },
  methods: {
    modify() {
      console.log(this.article.title);
      console.log(this.article.writer);
      console.log(this.article.content);

      http
        .post("board/modifyArticle", null, {
          params: {
            code: this.code,
            writer: this.article.writer,
            title: this.article.title,
            content: this.article.content,
          },
        })
        .catch();
      alert("게시글 수정 완료");
      this.$router.push({ name: "admin" });
    },
    gotoList() {
      this.$router.push({ name: "admin" });
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
      this.$router.push({ name: "admin" });
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
