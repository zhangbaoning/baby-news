import Vue from 'vue'
import Router from 'vue-router'
import home from '../components/home.vue'
import community from '../components/communtity.vue'
import person from '../components/person.vue'
import homeInfo from '../components/homeInfo.vue'
import communityInfo from '../components/communityInfo.vue'
import profile from '../components/profile.vue'
import publish from '../components/publish.vue'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    name: 'home',
    redirect: '/home'
  }, {
    path: '/home',
    name: 'home',
    component: home
  },
    {
      path: '/community',
      name: 'community',
      component: community
    },
    {
      path: '/person',
      name: 'person',
      component: person
    },
    {
      path: '/home/homeInfo/:id',
      name: 'homeInfo',
      component: homeInfo
    },
    {
      path: "/community/communityInfo/:id",
      name: 'communityInfo',
      component: communityInfo
    },
    {
      path: "/profile",
      name: "profile",
      component: profile
    },
    {
      path: "/publish",
      name: "publish",
      component: publish
    }
  ],
  linkActiveClass: 'mui-active'
})
