<template>
  <b-card>
    <b-container>
      <b-card-header class="border-0">
        <h3 class="mb-0">회원 관리</h3>
      </b-card-header>

      <el-table
        class="table-responsive table"
        header-row-class-name="thead-light"
        :data="members"
        @row-click="rowClickMember"
      >
        <!-- 회원 번호 -->
        <el-table-column label="회원 번호" prop="code" min-width="30px">
        </el-table-column>

        <!-- 아이디 -->
        <el-table-column label="아이디" prop="id" min-width="60px">
        </el-table-column>

        <!-- 이름 -->
        <el-table-column label="이름" prop="name" min-width="30px">
        </el-table-column>

        <!-- 주소  -->
        <el-table-column label="주소" prop="address" min-width="100px">
        </el-table-column>

        <!-- 전화번호  -->
        <el-table-column label="전화번호" prop="phone" min-width="60px">
        </el-table-column>
      </el-table>
      <b-container class="bv-example-row bv-example-row-flex-cols">
        <b-row align-self="end">
          <b-col> </b-col>
        </b-row>
      </b-container>

      <!-- 페이지 네비게이션 -->
      <b-card-footer class="py-4 d-flex justify-content-end">
        <b-pagination
          v-model="memberCurrentPage"
          :per-page="5"
          :total-rows="memberTotal"
        ></b-pagination>
      </b-card-footer>
    </b-container>

    <b-container>
      <b-card-header class="border-0">
        <h3 class="mb-0">게시판 관리</h3>
      </b-card-header>

      <el-table
        class="table-responsive table"
        header-row-class-name="thead-light"
        :data="boards"
        @row-click="rowClickBoard"
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

        <!-- 조회수  -->
        <!-- <el-table-column label="유저코드" prop="user_code" min-width="50px">
        </el-table-column> -->
      </el-table>
      <b-container class="bv-example-row bv-example-row-flex-cols">
        <b-row align-self="end">
          <b-col> </b-col>
        </b-row>
      </b-container>

      <!-- 페이지 네비게이션 -->
      <b-card-footer class="py-4 d-flex justify-content-end">
        <b-pagination
          v-model="boardCurrentPage"
          :per-page="5"
          :total-rows="boardTotal"
        ></b-pagination>
      </b-card-footer>
    </b-container>

    <b-container>
      <b-card-header class="border-0">
        <h3 class="mb-0">임장 모임 관리</h3>
      </b-card-header>

      <el-table
        class="table-responsive table"
        header-row-class-name="thead-light"
        :data="imjangs"
        @row-click="rowClickImjang"
      >
        <!-- 글번호 -->
        <el-table-column label="번호" prop="code" min-width="60px">
        </el-table-column>

        <!-- 제목 -->
        <el-table-column label="제목" prop="title" min-width="100px">
        </el-table-column>

        <!-- 날짜 -->
        <el-table-column label="모임 날짜" prop="date" min-width="100px">
        </el-table-column>

        <!-- 최소인원  -->
        <el-table-column label="최소인원" prop="min_people" min-width="60px">
        </el-table-column>

        <!-- 현재인원  -->
        <el-table-column label="현재인원" prop="cur_people" min-width="60px">
        </el-table-column>

        <!-- 최대인원  -->
        <el-table-column label="최대인원" prop="max_people" min-width="60px">
        </el-table-column>

        <!-- 시도  -->
        <el-table-column label="시도" prop="sidoName" min-width="100px">
        </el-table-column>

        <!-- 구군  -->
        <el-table-column label="구군" prop="gugunName" min-width="100px">
        </el-table-column>

        <!-- 동  -->
        <el-table-column label="동" prop="dongName" min-width="100px">
        </el-table-column>
      </el-table>
      <b-container class="bv-example-row bv-example-row-flex-cols">
        <b-row align-self="end">
          <b-col> </b-col>
        </b-row>
      </b-container>

      <!-- 페이지 네비게이션 -->
      <b-card-footer class="py-4 d-flex justify-content-end">
        <b-pagination
          v-model="imjangCurrentPage"
          :per-page="5"
          :total-rows="imjangTotal"
        ></b-pagination>
      </b-card-footer>
    </b-container>
  </b-card>
</template>

<script>
import http from "@/api/http";

import {
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Table,
  TableColumn,
} from "element-ui";

export default {
  components: {
    [Dropdown.name]: Dropdown,
    [DropdownItem.name]: DropdownItem,
    [DropdownMenu.name]: DropdownMenu,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      cur_session: 0,
      members: [],
      boards: [],
      imjangs: [],
      memberCurrentPage: 1,
      boardCurrentPage: 1,
      imjangCurrentPage: 1,
      memberTotal: 1,
      boardTotal: 1,
      imjangTotal: 1,
    };
  },
  created() {
    http
      .get("admin/getMemberSize")
      .then(({ data }) => {
        console.log(data);
        this.memberTotal = data;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        this.$router.push({ name: "error" });
      });

    http
      .get("admin/getBoardSize")
      .then(({ data }) => {
        console.log(data);
        this.boardTotal = data;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        //alert(err);
        this.$router.push({ name: "error" });
      });

    http
      .get("admin/getImjangSize")
      .then(({ data }) => {
        console.log(data);
        this.imjangTotal = data;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        //alert(err);
        this.$router.push({ name: "error" });
      });

    http
      .post("admin/selectMembers", null, {
        params: { pageNum: this.memberCurrentPage, pageSize: 5 },
      })
      .then(({ data }) => {
        console.log(data);
        this.members = data.list;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        //alert(err);
        this.$router.push({ name: "error" });
      });

    http
      .post("board/selectAll", null, {
        params: { pageNum: this.boardCurrentPage, pageSize: 5 },
      })
      .then(({ data }) => {
        console.log(data);
        this.boards = data.list;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        //alert(err);
        this.$router.push({ name: "error" });
      });

    http
      .post("admin/selectImjang", null, {
        params: { pageNum: this.imjangCurrentPage, pageSize: 5 },
      })
      .then(({ data }) => {
        console.log(data);
        this.imjangs = data.list;
        // alert(this.boards[0].title);
      })
      .catch((err) => {
        //alert(err);
        this.$router.push({ name: "error" });
      });
  },
  methods: {
    rowClickMember(row) {
      //   alert(row.code);
      this.$router
        .push({ name: "member_modify", params: { id: row.id } })
        .catch();
    },
    rowClickBoard(row) {
      //   alert(row.code);
      this.$router
        .push({ name: "board_modify", params: { code: row.code } })
        .catch();
    },
    rowClickImjang(row) {
      //   alert(row.code);
      this.$router
        .push({ name: "imjang_modify", params: { code: row.code } })
        .catch();
    },
  },
  watch: {
    memberCurrentPage: {
      deep: true,
      handler() {
        // alert(this.currentPage + "가 선택되었습니다.");
        http
          .post("admin/selectMembers", null, {
            params: { pageNum: this.memberCurrentPage, pageSize: 5 },
          })
          .then(({ data }) => {
            console.log(data);
            this.members = data.list;
            // alert(this.boards[0].title);
          });
      },
    },
    boardCurrentPage: {
      deep: true,
      handler() {
        // alert(this.currentPage + "가 선택되었습니다.");
        http
          .post("board/selectAll", null, {
            params: { pageNum: this.boardCurrentPage, pageSize: 5 },
          })
          .then(({ data }) => {
            console.log(data);
            this.boards = data.list;
            // alert(this.boards[0].title);
          });
      },
    },
    imjangCurrentPage: {
      deep: true,
      handler() {
        // alert(this.currentPage + "가 선택되었습니다.");
        http;
        http
          .post("admin/selectImjang", null, {
            params: { pageNum: this.imjangCurrentPage, pageSize: 5 },
          })
          .then(({ data }) => {
            console.log(data);
            this.imjangs = data.list;
            // alert(this.boards[0].title);
          });
      },
    },
  },
};
</script>
<style>
.el-table.table-dark {
  background-color: #172b4d;
  color: #f8f9fe;
}

.el-table.table-dark th,
.el-table.table-dark tr {
  background-color: #172b4d;
}

.el-table.table-dark td,
.el-table.table-dark th.is-leaf {
  border-bottom: none;
}
</style>
