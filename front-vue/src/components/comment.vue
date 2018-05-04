<template>
  <div class="comment-container">
    <div class="header">
      <img src="../assets/img/20180418091612.jpg" alt="">
      <div class="commentPersonInfo">
        <span>{{commentPerson}}</span>
        <p>{{publishTime}}</p>
        <i>{{phoneInfo}}</i>
      </div>
      <div class="text">
        {{text}}
      </div>
    </div>
  </div>
</template>
<script>
  export default {

    data() {
      return {
        commentPerson: '陈独秀',
        publishTime: '38分钟前',
        phoneInfo: '泰拉石iphoneX',
        text: '图一如果不是修图了的话，就是典型的贫血相。帮助别人的同时要注意自己的身体'
      }
    }
  }
</script>
<style scoped>
  .comment-container {
    margin-top: 50px;
    border: 2px solid #e9dede;
  }

  .header {
    position: relative;
  }

  .header img {
    padding: 10px;
    width: 60px;
    height: 60px;
    border-radius: 50%;
  }

  .header .commentPersonInfo {
    position: absolute;
    left: 25%;
    top: 10%;
  }

  .header .commentPersonInfo span {
    font-size: 14px;
  }

  .header .commentPersonInfo p {
    padding-top: 7%;
    font-size: 12px;
    color: #3c3c3c;
  }

  .header .commentPersonInfo i {
    font-size: 12px;
    color: #3c3c3c;
    font-style: normal;
  }

  .header .text {
    padding: 5% 5% 5% 20%;
    font-size: 16px;
    color: #3c3c3c;
  }

</style>
