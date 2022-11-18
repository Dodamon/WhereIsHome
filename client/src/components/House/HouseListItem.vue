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
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ house.aptCode }}] {{ marker.apartmentName }}
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
