<template>
  <div class="login-container">
    <mt-header class='mint-header' title='亲，欢迎登陆'></mt-header>
    <form class="mui-input-group">
      <div class="login-way">
        <span :class="[{'active':flag}]" @click='flag=true'>注册</span>
        <span :class="[{'active':!flag}]" @click='flag=false'>密码登录</span>
      </div>
      <div class="mui-input-row" v-show='!flag'>
        <label>用户名：</label>
        <input type="text" placeholder="请输入用户名" name='min' v-model='username'>
      </div>
      <div class="mui-input-row" v-show='flag'>
        <label>账号：</label>
        <input v-model="username" type="text" placeholder="请输入要注册的账号" name='regex'>
      </div>
      <div class="mui-input-row" v-show='flag'>
        <label>网名：</label>
        <input v-model="nickname" type="text" placeholder="请输入网名" name='regex'>
      </div>
      <div class="mui-input-row" v-show='flag'>
        <label>密码：</label>
        <input v-model="password" type="password" placeholder="请输入密码" name='regex'>
      </div>
      <div class="mui-input-row" v-show='flag'>
        <label>密码：</label>
        <input v-model="rePassword" type="password" placeholder="请再次输入密码" name='regex'>
      </div>
      <div class="mui-input-row" v-show='flag'>
        <label>手机号：</label>
        <input maxlength=11 v-model="mobile" type="text" placeholder="请输入手机号" name='regex'>
      </div>
      <div class="mui-input-row" v-show='!flag'>
        <label>密码：</label>
        <input type="password" placeholder="请输入密码" name='password' v-model='password'>
      </div>

      <!--<div class="mui-input-row" v-show='flag'>-->
      <!--<label>验证码：</label>-->
      <!--<input type="text" placeholder="请输入验证码" name='code'>-->
      <!--</div>-->
      <div class="mui-button-row">
        <button type="submit" v-if="!flag" class="mui-btn mui-btn-primary mui-btn-block" @click.prevent='login'
                tag="button"
        >登录
        </button>
        <button type="register" v-if="flag" class="mui-btn mui-btn-primary mui-btn-block" @click.prevent='register'
                tag="button"
        >注册
        </button>
      </div>
    </form>

  </div>
</template>

<script>
  import mui from '../assets/mui/js/mui.min.js';

  export default {
    data() {
      return {
        username: '',
        password: '',
        rePassword: '',
        nickname: '',
        mobile: '',
        flag: false,
        msg: '用户名不少于4位',
        pswVal: '密码为6-15位数字或字符串组成',
        phoneVal: '请输入正确的手机号码',
        codeVal: '验证码为六位数字组成'
      }
    },
    methods: {
      register() {
        var _this = this;
        let user = {
          username: this.username,
          password: this.password,
          nickname: this.nickname,
          mobile: this.mobile,
        };
        this.$ajax.post('apis/user/register', user);
        // 注册成功后跳转到登陆界面
        this.flag = false;
      },
      login() {
        var _this = this;
        var param = {username: this.username, password: this.password};
        this.$ajax.post('apis/user/login', param).then(function (res) {
          console.log(res);
          if (res.data.info === 'success') {
            // 登陆成功将用户信息进行保存
            sessionStorage.setItem('user', res.data.user);
            console.log('跳转');
            _this.$router.push('/profile')
            window.location.reload();
          } else if (res.data.info === 'failure') {
            console.log('不匹配');
            mui.toast('账号密码不匹配', {duration: 'long', type: 'div'})
          }
        });
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

  .mui-toast-message {
    display: block;
    position: absolute;
    left: 100px;
    top: 150px;
  }
</style>




