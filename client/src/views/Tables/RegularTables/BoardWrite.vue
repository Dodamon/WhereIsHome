<template>
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">글쓰기</h3>
    </b-card-header>

    <b-container>
      <b-row>
        <b-col style="text-align: left">
          <b-form @submit="write">
            <b-form-group label="작성자" label-for="writer">
              <b-form-input
                readonly
                id="writer"
                placeholder="작성자"
                v-model="article.writer"
                >{{ article.writer }}</b-form-input
              >
            </b-form-group>

            <b-form-group label="제목" label-for="title">
              <b-form-input
                id="title"
                placeholder="제목"
                v-model="article.title"
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
                >글등록</b-button
              >
              <b-button type="reset" class="m-1" variant="danger"
                >초기화</b-button
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
    };
  },
  created() {
    this.article.writer = sessionStorage.getItem("loggedin");
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
        .then(({ data }) => {
          alert(data);
        })
        .catch();
      this.$router.push({ name: "list" });
    },
    gotoList() {
      this.$router.push({ name: "list" });
    },
  },
};
</script>
