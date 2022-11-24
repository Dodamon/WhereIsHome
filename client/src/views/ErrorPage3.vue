<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-info py-7 py-lg-8 pt-lg-9">
      <b-container>
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            <b-col xl="12" lg="12" md="12" class="px-5">
              <i class="ni ni-satisfied text-white" style="font-size: 5rem"></i>
              <h1 style="font-size: 3rem" class="font-weight-bold text-white">
                ERROR : 오류페이지
              </h1>
              <h2 style="font-size: 1.5rem">해킹감지!!!!!!!!!!</h2>
              <div>
                <!-- <img
                  style="width: 50%; border-radius: 20px"
                  src="https://user-images.githubusercontent.com/62598865/203505744-cc13056b-8e4f-495d-a0d5-9d67cb301620.png"
                /> -->
                <img
                  style="width: 40%; border-radius: 20px"
                  src="img/error.jpg"
                />
              </div>
              <router-link
                to="/"
                class="font-weight-bold text-white mt-5"
                style="text-decoration: underline"
                >홈페이지로 돌아기기</router-link
              >
            </b-col>
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg
          x="0"
          y="0"
          viewBox="0 0 2560 100"
          preserveAspectRatio="none"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
        >
          <polygon
            class="fill-default"
            points="2560 0 2560 100 0 100"
          ></polygon>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "error3",
};
</script>
