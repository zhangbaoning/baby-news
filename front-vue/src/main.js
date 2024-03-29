// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Mint from 'mint-ui'
import 'mint-ui/lib/style.css'

import '../src/assets/mui/css/mui.css'
import '../src/assets/mui/css/icons-extra.css'
import axios from 'axios'
import moment from 'moment'

Vue.use(Mint);
Vue.prototype.$ajax = axios
Vue.config.productionTip = false
// 定义全局的过滤器
Vue.filter('dateFormat', function (dataStr, pattern = "YYYY-MM-DD HH:mm:ss") {
  return moment(dataStr).format(pattern)
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
