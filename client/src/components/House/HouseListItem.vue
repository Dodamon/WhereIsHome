<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://cdn-icons-png.flaticon.com/512/195/195492.png"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ house.dealYear }}년 {{house.dealMonth}}월] &nbsp;&nbsp;&nbsp;&nbsp; {{house.area}} m<sup>2</sup> &nbsp;&nbsp;&nbsp;&nbsp;{{ house.dealAmount }} 만원
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    marker: Object,
  },
  methods: {
    selectHouse() {
      console.log(this.house);
      this.$emit("selectHouse", this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
