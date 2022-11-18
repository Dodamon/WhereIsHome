<template>
  <div>
    <!-- 검색창, 로그인된 프로필 표시 구역 -->
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->

      <!-- todo -->
      <!-- <b-jumbotron>
        <b-row>
          <todo-header></todo-header>
        </b-row>
        <b-row>
          <todo-input @addTodo="addTodo"></todo-input>
        </b-row>
        <b-row>
          <todo-list :todos="todos"></todo-list>
        </b-row>
        <b-row>
          <todo-footer :todos="todos" @deleteTodo="deleteTodo"></todo-footer>
        </b-row>
      </b-jumbotron> -->
    </base-header>

    <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <router-view></router-view>
        </b-col>
      </b-row>
    </b-container>

    <!-- 기존 템플릿에 있던 테이블 -->
    <!-- <b-container fluid class="mt--7">
      <b-row>
        <b-col>
          <light-table />
        </b-col>
      </b-row>
      <div class="mt-5"></div>
      <dark-table></dark-table>
    </b-container> -->
  </div>
</template>
<script>
import {
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Table,
  TableColumn,
} from "element-ui";
import projects from "./Tables/projects";
import users from "./Tables/users";

import BoardTable from "./Tables/RegularTables/BoardTable";
import BoardWrite from "./Tables/RegularTables/BoardWrite";

import TodoHeader from "@/components/todo/TodoHeader.vue";
import TodoFooter from "@/components/todo/TodoFooter.vue";
import TodoInput from "@/components/todo/TodoInput.vue";
import TodoList from "@/components/todo/TodoList.vue";

export default {
  components: {
    BoardTable,
    BoardWrite,
    [Dropdown.name]: Dropdown,
    [DropdownItem.name]: DropdownItem,
    [DropdownMenu.name]: DropdownMenu,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,

    TodoHeader,
    TodoInput,
    TodoList,
    TodoFooter,
  },
  data() {
    return {
      projects,
      users,
      todos: [],
    };
  },
  methods: {
    addTodo(item) {
      console.log(item);
      this.todos.push(item);
    },
    deleteTodo(arr) {
      this.todos = arr;
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
