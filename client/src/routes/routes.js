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
        component: () => import("../views/KaKaoMaps.vue"),
      },
      {
        path: "/admin",
        name: "admin",
        component: () => import("../views/AdminPage.vue"),
        redirect: "/admin/admin_list",
        children: [
          {
            path: "admin_list",
            name: "admin_list",
            component: () => import("@/views/Admin/AdminList.vue"),
          },
          {
            path: "member_modify",
            name: "member_modify",
            component: () => import("@/views/Admin/MemberModify.vue"),
          },
          {
            path: "board_modify",
            name: "board_modify",
            component: () => import("@/views/Admin/BoardModify.vue"),
          },
          {
            path: "imjang_modify",
            name: "imjang_modify",
            component: () => import("@/views/Admin/ImjangModify.vue"),
          },
        ],
      },
      {
        path: "/icons",
        name: "icons",
        component: () => import("../views/Icons.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        component: () => import("../views/Pages/UserProfile.vue"),
        redirect: "/profile/profile_detail",
        children: [
          {
            path: "profile_detail",
            name: "profile_detail",
            component: () =>
              import("@/views/Pages/UserProfile/DetailProfile.vue"),
          },
          {
            path: "profile_modify",
            name: "profile_modify",
            component: () =>
              import("@/views/Pages/UserProfile/EditProfileForm.vue"),
          },
        ],
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
        component: () => import("../views/Pages/Register.vue"),
      },
      {
        path: "/error1",
        name: "error1",
        component: () => import("../views/ErrorPage1.vue"),
      },
      {
        path: "/error2",
        name: "error2",
        component: () => import("../views/ErrorPage2.vue"),
      },
      {
        path: "/error3",
        name: "error3",
        component: () => import("../views/ErrorPage3.vue"),
      },
      { path: "*", name: "error4", component: NotFound },
    ],
  },
];

export default routes;
