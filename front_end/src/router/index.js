import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from "@/views/AppMain.vue"
// import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: AppMain
  },
  {
    path: '/user',
    name: 'user',
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "userjoin",
        component: () => import("@/components/user/UserJoin"),
      },
      {
        path: "login",
        name: "userlogin",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "modify",
        name: "usermodify",
        component: () => import("@/components/user/UserModify"),
      },
      {
        path: "detail",
        name: "userdetail",
        component: () => import("@/components/user/UserDetail"),
      },
      {
        path: "searchpwd",
        name: "usersearchpwd",
        component: () => import("@/components/user/UserSearchPassword"),
      },
    ]
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleNo",
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify/:articleNo",
        name: "boardmodify",
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleNo",
        name: "boarddelete",
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/AppQna"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnalist",
        component: () => import("@/components/qna/QnaList"),
      },
      {
        path: "write/question",
        name: "qnawrite",
        component: () => import("@/components/qna/QnaWrite"),
      },
      {
        path: "view/:questionId",
        name: "qnaview",
        component: () => import("@/components/qna/QnaView"),
      },
      {
        path: "modify/question/:questionId",
        name: "qnamodify",
        component: () => import("@/components/qna/QnaModify"),
      },
      {
        path: "delete/question/:questionId",
        name: "qnadelete",
        component: () => import("@/components/qna/QnaDelete"),
      },
      {
        path: "write/answer",
        name: "answerwrite",
        component: () => import("@/components/qna/QnaAnswerWrite"),
      },
      {
        path: "modify/answer/:questionId",
        name: "answermodify",
        component: () => import("@/components/qna/QnaAnswerModify"),
      },
      {
        path: "delete/answer/:questionId",
        name: "answerdelete",
        component: () => import("@/components/qna/QnaAnswerDelete"),
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    component: () => import("@/views/AppMap"),
    redirect: "/map/list",
    children: [
      {
        path: "list",
        name: "maplist",
        component: () => import("@/components/map/TheMap"),
      },
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
