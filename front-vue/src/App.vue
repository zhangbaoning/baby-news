<template>
  <div class="app-container">
    <!-- 顶部 Header 区域 -->
    <!-- <mt-header fixed title="育 婴 咨 询"></mt-header> -->
    <!-- router-view区域 -->
    <transition>
      <router-view></router-view>
    </transition>
    <!-- 底部区域 -->
    <nav class="mui-bar mui-bar-tab">
      <router-link class="mui-tab-item-llb" to="/home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">资讯</span>
      </router-link>
      <router-link class="mui-tab-item-llb" to="/community">
        <span class="mui-icon mui-icon-extra mui-icon-extra-find"></span>
        <span class="mui-tab-label">社区</span>
      </router-link>
      <router-link class="mui-tab-item-llb" :to="routerMy">
        <span class="mui-icon mui-icon-extra mui-icon-extra-addpeople"></span>
        <span class="mui-tab-label">我的</span>
      </router-link>
    </nav>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        routerMy: ''
      }
    },
    methods: {
      getRouterMy() {
        console.log('这里是判断');
        let user = sessionStorage.getItem('user');
        console.log(user);
        if (user === null) {
          this.routerMy = '/person';
          console.log('执行登陆界面');
        } else {
          this.routerMy = '/profile';
          console.log('执行简介界面');
        }
      }
    },
    mounted() {
      this.getRouterMy();
    }
  };
</script>


<style scoped>
  .app-container {
    /* padding-top: 40px; */
    padding-bottom: 50px;
    overflow-x: hidden;
  }

  .mui-bar-tab .mui-tab-item-llb.mui-active {
    color: #007aff;
  }

  .mui-bar-tab .mui-tab-item-llb {
    display: table-cell;
    overflow: hidden;
    width: 1%;
    height: 50px;
    text-align: center;
    vertical-align: middle;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: #929292;
  }

  .mui-bar-tab .mui-tab-item-llb .mui-icon {
    top: 3px;
    width: 24px;
    height: 24px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .mui-bar-tab .mui-tab-item-llb .mui-icon ~ .mui-tab-label {
    font-size: 11px;
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  img {
    width: 100%;
  }
</style>
