<template>
  <div>
    <div id="mapContainer" class="p-0 m-0">
      <b-container fluid id="map" class="map-canvas" style="height: 1100px">
        <b-row id="detailContainer">
          <b-sidebar
            id="sidebar-right"
            title="Sidebar"
            style="width: 600px"
            right
            shadow
          >
            <b-container v-if="selected_house" class="bv-example-row">
              <b-row>
                <b-col
                  ><h3>{{ marker.apartName }}</h3></b-col
                >
              </b-row>
              <b-row class="mb-2 mt-1">
                <b-col>
                  <b-img
                    thumbnail
                    src="https://picsum.photos/250/250/?image=58"
                    alt="Image 1"
                  ></b-img>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-alert show variant="secondary"
                    >일련번호 : {{ selected_house.aptCode }}</b-alert
                  >
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-alert show variant="secondary"
                    >아파트 이름 : {{ marker.apartmentName }}
                  </b-alert>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-alert show variant="secondary"
                    >법정동 : {{ marker.dongName }}
                  </b-alert>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-alert show variant="secondary"
                    >층수 : {{ selected_house.floor }}층</b-alert
                  >
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-alert show variant="danger"
                    >거래금액 :
                    {{ parseInt(selected_house.dealAmount) }}원</b-alert
                  >
                </b-col>
              </b-row>
            </b-container>

            <b-container
              v-if="housedeals && housedeals.length != 0"
              class="bv-example-row mt-3"
            >
              <house-list-item
                v-for="(house, index) in housedeals"
                :key="index"
                :house="house"
                :marker="marker"
                @selectHouse="selectHouse"
              />
            </b-container>
            <b-container v-else class="bv-example-row mt-3">
              <b-row>
                <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
              </b-row>
            </b-container>
          </b-sidebar>
        </b-row>

        <b-row id="buttons">
          <b-button type="success" @click="findAddress">
            역삼동 멀티 캠퍼스</b-button
          >
          <div id="location_select">
            <b-form-select v-model="selected_sido" class="w-25">
              <option
                v-for="(item, index) in sidos"
                :key="index"
                :value="item.sidoName"
              >
                {{ item.sidoName }}
              </option>
            </b-form-select>
            <b-form-select v-model="selected_gugun" class="w-25">
              <option
                v-for="(item, index) in guguns"
                :key="index"
                :value="item.gugunName"
              >
                {{ item.gugunName }}
              </option>
            </b-form-select>
            <b-form-select v-model="selected_dong" class="w-25">
              <option
                v-for="(item, index) in dongs"
                :key="index"
                :value="item.dongName"
              >
                {{ item.dongName }}
              </option>
            </b-form-select>
          </div>

          <div id="location_select">
            <b-form-select v-model="selected_sido" class="w-25">
              <option
                v-for="(item, index) in sidos"
                :key="index"
                :value="item.sidoName"
              >
                {{ item.sidoName }}
              </option>
            </b-form-select>
            <b-form-select v-model="selected_gugun" class="w-25">
              <option
                v-for="(item, index) in guguns"
                :key="index"
                :value="item.name"
              >
                {{ item.name }}
              </option>
            </b-form-select>
            <b-form-select v-model="selected_dong" class="w-25">
              <option
                v-for="(item, index) in dongs"
                :key="index"
                :value="item.name"
              >
                {{ item.name }}
              </option>
            </b-form-select>
          </div>
        </b-row>
      </b-container>
    </div>
  </div>
</template>
<script>
import http from "@/api/http";
import HouseListItem from "@/components/House/HouseListItem";

export default {
  name: "KakaoMap",
  components: {
    HouseListItem,
  },
  data() {
    return {
      markerPositions: [],
      marker: Object,
      house: Object,
      isColor: true,
      selected_house: Object,

      selected_sido: "시도 선택",
      selected_gugun: "구군 선택",
      selected_dong: "동 선택",
      sidos: [],
      guguns: [],
      dongs: [],

      houseinfos: [],
      housedeals: [],

      markers: [],
      infowindow: null,
      geocoder: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=0752958a730458f6ca6dedd9bc8b93b5&libraries=services&autoload=false";
      // "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0752958a730458f6ca6dedd9bc8b93b5";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map((position, index) => {
          const marker = new kakao.maps.Marker({
            map: this.map,
            position,
          });
          kakao.maps.event.addListener(marker, "click", () => {
            // 마커를 클릭했을때 sidebar가 나오도록한다.
            console.log("open", index);
            this.marker = this.houseinfos[index];
            this.openSidebar();
            this.search_deals(this.marker.aptCode);
          });
          return marker;
        });

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
    findAddress() {
      // 주소-좌표 변환 객체를 생성합니다
      this.geocoder = new kakao.maps.services.Geocoder();
      this.geocoder.addressSearch(
        "서울특별시 강남구 역삼동 테헤란로 212",
        (result, status) => {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });
            console.log(coords);

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            this.infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">서울 멀티 캠퍼스</div>',
            });
            this.infowindow.open(this.map, marker);

            // // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            this.map.setCenter(coords);
          }
        }
      );
    },
    search_deals(aptcode) {
      http
        .get("board/housedeal", { params: { aptCode: aptcode } })
        .then(({ data }) => {
          console.log("housedeal", data.list);
          this.housedeals = data.list;
        });
    },
    makeMarkerPositions() {
      this.houseinfos.forEach((element) => {
        //console.log(element.lat, element.lng);
        this.markerPositions.push([element.lat, element.lng]);
      });
      console.log(this.markerPositions);
      this.displayMarker(this.markerPositions);
    },
    openSidebar() {
      this.$root.$emit("bv::toggle::collapse", "sidebar-right");
    },
    selectHouse(house) {
      console.log("here", house);
      this.selected_house = house;
    },
  },
  watch: {
    selected_sido: {
      deep: true,
      handler() {
        // alert(this.selected_sido + "가 선택되었습니다.");

        http
          .get("board/gugun", { params: { sidoName: this.selected_sido } })
          .then(({ data }) => {
            console.log(data);
            this.guguns = data.list;
            // console.log(this.guguns);
          });
      },
    },
    selected_gugun: {
      deep: true,
      handler() {
        // alert(this.selected_gugun + "가 선택되었습니다.");
        http
          .get("board/dong", {
            params: {
              sidoName: this.selected_sido,
              gugunName: this.selected_gugun,
            },
          })
          .then(({ data }) => {
            console.log(data);
            this.dongs = data.list;
            console.log(this.dongs);
          });
      },
    },
    selected_dong: {
      deep: true,
      handler() {
        // alert(this.selected_dong + "가 선택되었습니다.");

        http
          .get("board/houseinfo", {
            params: {
              sidoName: this.selected_sido,
              gugunName: this.selected_gugun,
              dongName: this.selected_dong,
            },
          })
          .then(({ data }) => {
            console.log(data);
            this.houseinfos = data.list;
            console.log(this.houseinfos);

            alert(
              "해당 지역 아파트는" + this.houseinfos.length + " 건 입니다."
            );
            this.makeMarkerPositions();
          });
      },
    },
  },
  created() {
    // alert("create 시작");
    http.get("board/sido").then(({ data }) => {
      // alert("sido 포스트 끝");
      console.log(data);
      this.sidos = data.list;
      // console.log(typeof data.list);
    });
  },
};
</script>
<style>
.b-sidebar {
  width: 600px !important;
  height: 85%;
  margin-top: 95px;
  margin-bottom: 1000px;
}
#mapContainer {
  position: relative;
}
#buttons {
  position: absolute;
  top: 0;
  left: 0;
  padding: 5px;
  z-index: 3;
}
#detailContainer {
  position: absolute;
  padding: 5px;
  z-index: 4;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
