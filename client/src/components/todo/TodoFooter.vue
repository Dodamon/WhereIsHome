<template>
  <div class="todoFooter">
    <p>{{ doneCnt }}/{{ todos.length }} 건 처리</p>
    <button @click="deleteTodo">완료항목 삭제</button>
  </div>
</template>

<script>
export default {
  name: "TodoFooter",
  props: ["todos"],
  computed: {
    doneCnt() {
      return this.todos.filter(function (item) {
        return item.done;
      }).length;
    },
  },
  methods: {
    deleteTodo() {
      let arr = this.todos.filter(function (item) {
        return item.done == false;
      });
      this.$emit("deleteTodo", arr);
    },
  },
};
</script>

<style scoped>

</style>