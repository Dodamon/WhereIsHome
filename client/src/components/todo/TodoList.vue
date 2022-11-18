<template>
  <div class="todoList">
    <div v-for="(item, index) in todos" :key="index">
      <input type="checkbox" v-model="item.done" />
      <span :class="{ donestyle: item.done }">{{ item.text }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "TodoList",
  props: ["todos"],
};
</script>

<style scoped>
.donestyle {
  text-decoration: line-through;
  color: gray;
}
</style>