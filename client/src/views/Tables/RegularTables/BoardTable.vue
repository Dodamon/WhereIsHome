<template slot-scope="scope">
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">공지사항</h3>
    </b-card-header>

    <el-table
      class="table-responsive table"
      header-row-class-name="thead-light"
      :data="boards"
      @row-click="rowClick"
    >
      <!-- 글번호 -->
      <el-table-column label="글번호" prop="code" min-width="30px">
      </el-table-column>

      <!-- 제목 -->
      <el-table-column label="제목" prop="title" min-width="60px">
      </el-table-column>

      <!-- 내용 -->
      <el-table-column label="내용" prop="content" min-width="100px">
      </el-table-column>

      <!-- 작성자  -->
      <el-table-column label="작성자" prop="writer" min-width="30px">
      </el-table-column>

      <!-- 작성일시  -->
      <el-table-column label="작성일시" prop="reg_datetime" min-width="60px">
      </el-table-column>

      <!-- 유저코드  -->
      <!-- <el-table-column label="유저코드" prop="user_code" min-width="50px">
      </el-table-column> -->
    </el-table>
    <b-container class="bv-example-row bv-example-row-flex-cols">
      <b-row align-self="end">
        <b-col>
          <b-button type="submit" class="m-1" variant="primary" @click="write"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>
    </b-container>

    <!-- 페이지 네비게이션 -->
    <b-card-footer class="py-4 d-flex justify-content-end">
      <b-pagination
        v-model="currentPage"
        :per-page="5"
        :total-rows="total"
      ></b-pagination>
    </b-card-footer>
  </b-card>
</template>

<script>
// import projects from "./../projects";
import http from "@/api/http";
import { Table, TableColumn } from "element-ui";
export default {
  name: "board-table",
  data() {
    return {};
  },
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
    // board,
  },
  data() {
    return {
      // projects,
      boards: [],
      currentPage: 1,
      total: null,
    };
  },
  created() {
    http.get("board/getSize").then(({ data }) => {
      console.log(data);
      this.total = data;
      // alert(this.boards[0].title);
    });

    http
      .post("board/selectAll", null, {
        params: { pageNum: this.currentPage, pageSize: 5 },
      })
      .then(({ data }) => {
        console.log(data);
        this.boards = data.list;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        alert(err.response.status);
        // this.$router.push({ name: "error" });
        switch (err.response.status) {
          case 501:
            this.$router.push({ name: "error1" });
            break;
          case 502:
            this.$router.push({ name: "error2" });
            break;
          case 503:
            this.$router.push({ name: "error3" });
            break;
          default:
            this.$router.push({ name: "error4" });
            break;
        }
      });
  },
  methods: {
    write() {
      this.$router.push({ name: "write" });
    },
    rowClick(row) {
      // alert(row.code);
      this.$router.push({ name: "detail", params: { code: row.code } });
    },
  },
  watch: {
    currentPage: {
      deep: true,
      handler() {
        // alert(this.currentPage + "가 선택되었습니다.");
        http
          .post("board/selectAll", null, {
            params: { pageNum: this.currentPage, pageSize: 5 },
          })
          .then(({ data }) => {
            console.log(data);
            this.boards = data.list;
            // alert(this.boards[0].title);
          });
      },
    },
  },
};
</script>
