<template>
  <b-card>
    <b-card-header class="border-0">
      <h3 class="mb-0">공지사항</h3>
    </b-card-header>

    <el-table
      class="table-responsive table"
      header-row-class-name="thead-light"
      :data="boards"
    >
      <!-- 글번호 -->
      <el-table-column label="글번호" prop="code" min-width="60px">
      </el-table-column>

      <!-- 제목 -->
      <el-table-column label="제목" prop="title" min-width="100px">
      </el-table-column>

      <!-- 내용 -->
      <el-table-column label="내용" prop="content" min-width="100px">
      </el-table-column>

      <!-- 작성자  -->
      <el-table-column label="작성자" prop="writer" min-width="60px">
      </el-table-column>

      <!-- 작성일시  -->
      <el-table-column label="작성일시" prop="reg_datetime" min-width="100px">
      </el-table-column>

      <!-- 조회수  -->
      <el-table-column label="유저코드" prop="user_code" min-width="50px">
      </el-table-column>
    </el-table>

    <!-- 페이지 네비게이션 -->
    <b-card-footer class="py-4 d-flex justify-content-end">
      <base-pagination
        v-model="currentPage"
        :per-page="10"
        :total="50"
      ></base-pagination>
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
  },
  data() {
    return {
      // projects,
      boards: [],
      currentPage: 1,
    };
  },
  created() {
    http.post("board/selectAll?pageNum=1&pageSize=5").then(({ data }) => {
      console.log(data);
      this.boards = data.list;
      alert(this.boards[0].title);
    });
  },
};
</script>
