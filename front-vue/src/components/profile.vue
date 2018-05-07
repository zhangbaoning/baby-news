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
      <img :src="user.avatar" alt="" class="head">
    </div>
    <div class="content">
      <p class="name">{{user.nickname}}</p>
      <p class="comment">评论数：{{user.commentCount}}</p>
    </div>
    <div class="message">
      <div>消息</div>
      <div>我的讨论</div>
    </div>
    <div class="detail">
      <div class="active">
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-trend"></span>活跃度</p>
          <p class="num">80</p>
        </div>
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-lamp"></span>今天活跃度</p>
          <p class="num">80</p>
        </div>
        <div>
          <p><span class="mui-icon mui-icon-extra mui-icon-extra-gold"></span>本月积分</p>
          <p class="num pb">{{user.score}}</p>
        </div>
      </div>
      <div class="team">
        <p><span class="mui-icon mui-icon-extra mui-icon-extra-peoples"></span>TEAM</p>
        <div class="friends">
          <div class="tou">
            <img src="../assets/img/head.jpeg" alt="">
          </div>
          <div class="name">LUO MEI</div>
        </div>
        <div class="friends">
          <div class="tou">
            <img src="../assets/img/head.jpeg" alt="">
          </div>
          <div class="name">LUO MEI</div>
        </div>
        <div class="friends">
          <div class="tou">
            <img src="../assets/img/head.jpeg" alt="">
          </div>
          <div class="name">LUO MEI</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        user: {}
      }
    },
    methods: {
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
        flex: 2;
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
        flex: 1.2;
        border: 1px solid #8097db;
        background-color: #8ea2e1;
        border-top-left-radius: 10px;

        p {
          color: #e2ebfc;
          padding: 20px;
          text-align: center;
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
            flex: 2;
            font-size: 14px;
            color: #e2ebfc;
            margin-left: 10px;
            line-height: 30px;
          }
        }
      }
    }
  }
</style>


