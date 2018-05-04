<template>
  <div class="login-container">
    <mt-header class='mint-header' title='亲，欢迎登陆'></mt-header>
    <form class="mui-input-group">
      <div class="login-way">
        <span :class="[{'active':flag}]" @click='flag=true'>短信登录</span>
        <span :class="[{'active':!flag}]" @click='flag=false'>密码登录</span>
      </div>
      <div class="mui-input-row" v-show='!flag'>
        <label>用户名：</label>
        <input type="text" placeholder="请输入用户名" name='min' v-model='uname'>
      </div>
      <div class="mui-input-row loginByphone" v-show='flag'>
        <label>手机号：</label>
        <input type="text" placeholder="请输入手机号" name='regex'>
        <span class="getCode">获取验证码</span>
      </div>
      <div class="mui-input-row" v-show='!flag'>
        <label>密码：</label>
        <input type="password" placeholder="请输入密码" name='password' v-model='password'>
      </div>

      <div class="mui-input-row" v-show='flag'>
        <label>验证码：</label>
        <input type="text" placeholder="请输入验证码" name='code'>
      </div>
      <div class="mui-button-row">
        <router-link type="submit" class="mui-btn mui-btn-primary mui-btn-block" @click.prevent='login' tag="button"
                     to="/profile">登录
        </router-link>
      </div>
    </form>

  </div>
</template>

<script>
  import {Toast} from 'mint-ui'

  export default {
    data() {
      return {
        uname: 'em',
        password: null,
        flag: true,
        msg: '用户名不少于4位',
        pswVal: '密码为6-15位数字或字符串组成',
        phoneVal: '请输入正确的手机号码',
        codeVal: '验证码为六位数字组成'
      }
    },
    methods: {
      login() {
        var obj = {uname: this.uname, password: this.password}
        this.password = parseInt(this.password)
        if (this.uname !== 'em' || this.password !== 123456) {
          Toast({
            message: '用户名或密码错误！',
            position: 'middle',
            duration: 3000
          })
          this.password = null
        } else {
          Toast({
            message: '登录成功！',
            position: 'middle',
            duration: 1000
          })
          // this.$store.commit('addLogin',obj)
          this.$router.push('/person')
        }
      }
    }
  }
</script>

<style lang="scss" scoped>

  .login-container {
    .mui-input-group .mui-input-row {
      div {
        padding: 10px;
        text-align: center;
      }
      height: auto;
    }
    height: 100%;
    width: 100%;
    background-color: #fff;
    .mint-header {
      height: 50px;
      font-size: 16px;
      background-color: #ffffff;
      color: #4a4a4a;
    }
    .mui-input-group {
      padding: 15px 5px;
      .mui-input-row {
        margin: 25px 0;
      }
    }
    .login-way {
      text-align: center;
      span {
        padding: 8px 13px;
        margin: 5px 8px;
        color: #aaa;
        &.active {
          border-bottom: 2px solid lightblue;
          color: #000;
        }
      }
    }
    .loginByphone {
      position: relative;
      .getCode {
        position: absolute;
        top: 0;
        right: 0;
        padding: 11px 15px;
      }
    }
  }
</style>




