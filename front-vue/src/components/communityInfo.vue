<template>
  <div class="communityInfo">
    <mt-header title="社 区 詳 情">
      <router-link to="/" slot="left">
        <mt-button icon="back">back</mt-button>
      </router-link>
      <mt-button icon="more" slot="right"></mt-button>
    </mt-header>
    <div class="header">
      <img :src="messageData.avatar" alt="">
      <div class="userInfo">
        <span>{{messageData.nickname}}</span><br>
        <p>{{messageData.publishTime | dateFormat}}</p>
        <p>{{phoneInfo}}</p>
      </div>
    </div>
    <div class="banner">
      <div class="text">
        <span>{{messageData.content}}</span>
      </div>
      <!-- <div class="photo">
         <img src="../assets/img/1.jpg" alt="">
         <img src="../assets/img/1.jpg" alt="">
         <img src="../assets/img/1.jpg" alt="">
         <img src="../assets/img/1.jpg" alt="">
       </div>-->
    </div>

    <!-- <div class="footer">
       <div class="weibInfo">
         <span>赞 {{love}}</span>
         <span>评论 {{comment}}</span>
         <span>转发{{repeat}}</span>
       </div>
     </div>-->
    <div class="commentList">
      <div class="comment">
        <h3>发表评论</h3>
        <textarea v-model="inputComment" placeholder="请输入要评论的内容（最多评论120字）" maxlength="120"></textarea>
        <mt-button type="primary" size="large" @click="postComment">发表评论</mt-button>
        <div class="comm_list" v-for="(comment,index) in commentsData">
          <div class="comment">
            <div class="comment-top">
              <div class="avatar">
                <img
                  :src="comment.avatar">
              </div>
              <div class="name">{{comment.nickname}}</div>
            </div>
          </div>
          <p>{{comment.comment}}</p>
          <span class="meta">{{index+1}}楼 · {{comment.publishTime | dateFormat}}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import mui from '../assets/mui/js/mui.min.js';

  export default {
    name: "communityInfo",
    data() {
      return {
        commentsData: [],
        inputComment: "", // 评论内容
        username: "一只读物",
        publishTime: "4月17日 19:00",
        text:
          '【他20年献8000ml熊猫血 为救人从不关机】今年40岁的俞宙，是一名动车机械师，拥有RH阴性AB型“熊猫血”。1998年首次献血后，他的手机24小时开机，随叫随到。为此，他远离烟酒，他说，"要是喝了酒突然要献血，可是人命关天的事！"20年来，他无偿献血近8000毫升，相当于成人全身血量的两倍。',
        phoneInfo: "来自垃圾堆里捡到的坚果Pro",
        love: "22",
        comment: "10",
        repeat: "0",
        messageData: ''

      };
    },
    methods: {
      postComment() {
        var id = sessionStorage.getItem('user');
        if (id !== null) {
          let comment = {
            content: this.inputComment,
            messageId: this.$route.params.id,
            userId: id
          };
          this.$ajax.post('apis/comment/add', comment);
          // 发布成功后重新获取评论集合
          setTimeout(() => {
            this.getComments();
          }, 500);
          this.commentsData.reverse();
        } else {
          var that = this;
          var btnArray = ['取消', '确定'];
          mui.confirm('请先去登陆', '未登录', btnArray, function (e) {
            that.$router.push('/person');
          });
        }
      },
      getMessage() {
        var _this = this;
        this.$ajax.get('apis/message/getMessage', {
          params: {
            id: this.$route.params.id
          }
        }).then(function (res) {
          _this.messageData = res.data;
        });
      },
      getComments() {
        var _this = this;
        this.$ajax.get('apis/comment/getByMsgId', {params: {messageId: this.$route.params.id}})
          .then(function (res) {
            _this.commentsData = res.data;
          })
      },
    },
    mounted() {
      this.getMessage();
      this.getComments();
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
  .avatar {
    display: inline-block;
    img {
      width: 34px;
      height: 34px;
      border-radius: 17px;
      vertical-align: middle;
    }
  }

  .name {
    margin: 0 5px;
    font-size: 16px;
    font-weight: 700;
    color: #484848;
    display: inline-block;
    vertical-align: middle;
  }

  .userInfo {
    margin-top: 10px;
    span {
      font-size: 14px;
      margin-top: 20px;
    }
    p {
      display: inline-block;
      font-size: 12px;
      margin-bottom: 0px;
    }
  }

  .communityInfo {
    background-color: #fff;
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
          margin-right: 8px;
          margin-top: 8px;
          border-radius: 4px;
        }
      }
    }
    .weibInfo {
      display: flex;
      span {
        flex: 1;
        text-align: center;
        font-size: 15px;
      }
    }

    .footer {
      margin: 10px 0px;
      // border: 1px solid #cccccc;
      // border-bottom:  1px solid #cccccc;
    }
    .text1 {
      font-size: 14px;
      margin: 0px 20px 10px 55px;
    }

    .comment {
      margin-top: 8px;
      h3 {
        margin-left: 10px;
        font-size: 18px;
      }
      textarea {
        font-size: 14px;
        height: 85px;
        margin: 0;
      }
      .comm_list {
        background-color: #f5f5f5;
        margin-top: 8px;
        .comment-top {
          margin-left: 20px;
          padding-top: 10px;
        }
        p {
          margin: 5px 0 0 44px;
          font-size: 14px;
          color: #484848;
          line-height: 1.7;
          word-break: break-word !important;
        }
        span {
          display: block;
          padding-top: 5px;
          font-size: 12px;
          color: #969696;
          margin-left: 44px;
        }
      }
    }
  }
</style>



