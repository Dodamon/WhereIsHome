import DashboardLayout from "@/views/Layout/DashboardLayout.vue";
import AuthLayout from "@/views/Pages/AuthLayout.vue";

import NotFound from "@/views/NotFoundPage.vue";

const routes = [
  {
    // 메인페이지에서 바로 지도가 보여지도록 처리
    path: "/",
    redirect: "maps",
    component: DashboardLayout,
    children: [
      {
        path: "/maps",
        name: "maps",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/KaKaoMaps.vue"),
      },
      {
        path: "/icons",
        name: "icons",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Icons.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        component: () =>
          import(
            /* webpackChunkName: "demo" */ "../views/Pages/UserProfile.vue"
          ),
      },
      // {
      //   path: "/maps",
      //   name: "maps",
      //   component: () =>
      //     import(/* webpackChunkName: "demo" */ "../views/GoogleMaps.vue"),
      // },
      {
        path: "/tables",
        name: "tables",
        component: () => import("@/views/RegularTables.vue"),
        redirect: "/tables/list",
        children: [
          {
            path: "list",
            name: "list",
            component: () =>
              import("@/views/Tables/RegularTables/BoardTable.vue"),
          },
          {
            path: "write",
            name: "write",
            component: () =>
              import("@/views/Tables/RegularTables/BoardWrite.vue"),
          },
          {
            path: "detail",
            name: "detail",
            component: () =>
              import("@/views/Tables/RegularTables/BoardDetail.vue"),
          },
          {
            path: "modify",
            name: "modify",
            component: () =>
              import("@/views/Tables/RegularTables/BoardModify.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/Login.vue"),
      },
      {
        path: "/register",
        name: "register",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/Register.vue"),
      },
      { path: "*", component: NotFound },
    ],
  },
];

export default routes;
