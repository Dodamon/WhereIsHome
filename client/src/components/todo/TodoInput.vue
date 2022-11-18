<template>
  <div class="todoInput">
    <input v-model="job" placeholder="할 일" @keyup.enter="addTodo" />
  </div>
</template>

<script>
export default {
  name: "TodoInput",
  data() {
    return {
      job: "",
    };
  },
  methods: {
    addTodo() {
      console.log(this.job);
      let item = { done: false, text: this.job };
      this.$emit("addTodo", item);
      this.job = "";
    },
  },
};
</script>

<style scoped>

</style>