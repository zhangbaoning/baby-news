<template>
  <div class="community">
    <mt-header fixed title="育 婴 社 区"></mt-header>
    <img src="../assets/img/she.jpg" alt="" class="shequ">
    <!-- <router-link to="/community/communityInfo/1">emmm</router-link>
    <router-view></router-view> -->
    <div class="commList" v-for="messageInfo in messageList">
      <router-link class="content" :to="'/community/communityInfo/'+messageInfo.id" tag="div">
        <div class="header">
          <img :src="messageInfo.avatar" alt="">
          <div class="userInfo">
            <span>{{messageInfo.nickname}}</span><br>
            <p>{{messageInfo.publishTime | dateFormat}}</p>
            <p>{{phoneInfo}}</p>
          </div>
        </div>
        <div class="banner">
          <div class="text">
            <span>{{messageInfo.content}}</span>
          </div>

          <div class="photo">
            <img src="../assets/img/1.jpg" alt="">
            <img src="../assets/img/1.jpg" alt="">
            <img src="../assets/img/1.jpg" alt="">
            <img src="../assets/img/1.jpg" alt="">
          </div>
        </div>
      </router-link>


    </div>
  </div>
</template>
<script>
  export default {
    name: "community",
    data() {
      return {
        messageList: "",
        username: "一只读物",
        publishTime: "4月17日 19:00",
        text:
          '【他20年献8000ml熊猫血 为救人从不关机】今年40岁的俞宙，是一名动车机械师，拥有RH阴性AB型“熊猫血”。1998年首次献血后，他的手机24小时开机，随叫随到。为此，他远离烟酒，他说，"要是喝了酒突然要献血，可是人命关天的事！"20年来，他无偿献血近8000毫升，相当于成人全身血量的两倍。',
        phoneInfo: "来自垃圾堆里捡到的烂手机"
      };
    },
    methods: {
      getAllMessage() {
        var _this = this;
        this.$ajax.get('apis/message/getAllMessage').then(function (res) {
          _this.messageList = res.data;
        });
        console.log(_this.messageList)
      }
    },
    mounted() {
      this.getAllMessage();
    }
  };
</script>
<style lang="scss" scoped>
  .community {
    background-color: #fff;
    margin-top: 40px;
    .userInfo {
      span {
        font-size: 14px;
      }
      p {
        margin-bottom: 0px;
        font-size: 12px;
      }
    }
    .shequ {
      padding: 8px;
      border-radius: 4px;
      width: 100%;
    }

    .header {
      display: flex;
      img {
        padding: 10px;
        width: 60px;
        height: 60px;
        border-radius: 50%;
      }
    }
    .banner {
      font-size: 14px;
      margin: 0px 16px 10px 16px;
      .photo {
        width: 100%;
        img {
          width: 30%;
          margin-right: 6px;
          margin-top: 8px;
          border-radius: 4px;
        }
      }
    }
  }
</style>


