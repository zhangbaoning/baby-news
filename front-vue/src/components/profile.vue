<template>
  <div class="profile">
    <header class="mint-header">
      <div class="mint-header-button is-left">
        <a href="#/" class="router-link-active">
          <button class="mint-button mint-button--default mint-button--normal">
                <span class="mint-button-icon">
                    <i class="mintui mintui-back"></i>
                    </span>
            <label class="mint-button-text"></label>
          </button>
        </a>
      </div>
      <h1 class="mint-header-title">Profile</h1>
      <div class="mint-header-button is-right">
        <button class="mint-button mint-button--default mint-button--normal">
                <span class="mint-button-icon">
                    <i class="mintui mintui-more"></i>
                </span>
          <label class="mint-button-text"></label>
        </button>
      </div>
    </header>
    <div class="info">
      <img src="../assets/img/bg.jpg" alt="">
      <img :src="user.avatar" alt="" class="head" @click="logoutConfirm">
    </div>
    <div class="content">
      <p class="name">{{user.nickname}}</p>
      <p class="comment">评论数：{{user.commentCount}}</p>
    </div>
    <!--<div class="message">-->
    <!--<div>消息</div>-->
    <!--<div>我的讨论</div>-->
    <!--</div>-->
    <div class="detail">
      <div class="active">
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-trend"></span>发布活跃度</p>
          <p class="num">{{user.publishActivity}}</p>
        </div>
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-lamp"></span>互动活跃度</p>
          <p class="num">{{user.commentMsgActivity}}</p>
        </div>
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-gold"></span>本月积分</p>
          <p class="num pb">{{user.score}}</p>
        </div>
      </div>
      <div class="team">
        <p style="margin-top: 15px;"><span class="mui-icon mui-icon-extra mui-icon-extra-peoples"></span>用户信息</p>
        <div class="friends">
          <div class="friends"><span class="avatar mui-icon mui-icon-contact"></span></div>
          <p class="num">{{user.nickname}}</p>
        </div>
        <div class="friends">
          <div class="name"><span class="mui-icon mui-icon-phone-filled"/></div>
          <p class="num">{{user.mobile}}</p>
        </div>
        <div class="friends">
          <div class="name"><span class="mui-icon-extra mui-icon-extra-outline"/></div>
          <p class="num">{{user.dateJoined}}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import moment from 'moment'
  import mui from '../assets/mui/js/mui.min.js';

  export default {
    data() {
      return {
        user: {}
      }
    },
    methods: {
      // 用户退出
      logoutConfirm() {
        var that = this;
        console.log('退出');
        mui.confirm('确定退出吗', '退出', ['退出', '取消'], function (e) {
          console.log(e);
          if (e.index === 0) {
            that.$router.push('/person');
            sessionStorage.removeItem('user');
            window.location.reload();
          }
        });
      },
      //时间格式化
      dateFormat1: function (row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return moment(date).calendar();     // 2018 escaped 2018;
      },
      // 获取登陆者信息
      getUser() {
        var _this = this;
        console.log('sessionStorage');
        console.log(sessionStorage.getItem('user'));
        this.$ajax.get('apis/user/getInfo', {
          params: {
            userId: sessionStorage.getItem('user')
          }
        }).then(function (res) {
          console.log('获得作者信息');
          console.log(res);
          _this.user = res.data;
          _this.user.dateJoined = moment(res.data.dateJoined).format('L');
        });
      },
    },
    created() {
      this.getUser();
    }
  };

</script>
<style lang="scss" scoped>
  img {
    width: 100%;
    display: block;
  }

  p {
    margin-bottom: 0px;
  }

  .profile {
    background-color: #46557e;
    .info {
      position: relative;
      .head {
        display: block;
        width: 85px;
        border-radius: 50%;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        bottom: -30px;
      }
    }
    .content {
      p {
        text-align: center;
      }
      .name {
        color: #ffffff;
        font-size: 16px;
        font-weight: 700;
        margin-top: 45px;
      }
      .comment {
        color: #95a4c3;
        font-size: 14px;
      }
    }
    .message {
      height: 50px;
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 0 8px;
      div {
        height: 40px;
        line-height: 40px;
        flex: 1;
        text-align: center;
        color: #e2ebfc;
        border: 1px solid #96a7df;
        font-size: 14px;
        font-weight: 700;
        margin: 0 10px;
        border-radius: 6px;
      }
    }
    .detail {
      display: flex;
      margin-top: 15px;
      padding-bottom: 25px;
      .active {
        flex: 1;
        border: 1px solid #8097db;
        background-color: #8ea2e1;
        border-radius: 10px;
        text-align: left;
        margin: 0 20px;
        padding-bottom: 16px;
        p {
          color: #e2ebfc;
          padding: 20px 0 0 20px;
          span {
            margin-right: 15px;
          }
        }
        .num {
          color: #e2ebfc;
          text-align: center;
          padding: 0;
          font-size: 30px;
          font-weight: 700;
          margin-top: 10px;
        }
      }
      .team {
        flex: 1;
        border: 1px solid #8097db;
        background-color: #8ea2e1;
        border-top-left-radius: 10px;

        p {
          color: #e2ebfc;
          text-align: center;
          padding: 0px 20px 20px 20px;
        }
        .friends {
          display: flex;
          padding-bottom: 20px;

          .tou {
            flex: 1;
            img {
              width: 80%;
              border-radius: 50%;
              margin-left: 10px;
            }
          }
          .name {
            font-size: 14px;
            color: #e2ebfc;
            margin-left: 10px;
            line-height: 30px;
          }
        }
      }
    }
  }

  .avatar {
    color: white;
    padding-left: 10px;
  }
</style>


