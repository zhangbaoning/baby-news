<template>
  <div class="article">
    <div class="base_info">
      <mt-header title="資 訊 詳 情">
        <router-link to="/" slot="left">
          <mt-button icon="back">back</mt-button>
        </router-link>
        <mt-button icon="more" slot="right"></mt-button>
      </mt-header>
      <!-- 标题、作者信息 -->
      <h1 class="title">{{articleData.title}}</h1>
      <div class="article-info">
        <div class="info">
          <div class="avatar">
            <img :src="author.avatar">
          </div>
          <div class="name">{{author.nickname}}</div>
        </div>
        <div class="meta">
                <span>
                {{articleData.datePublish | dateFormat}}
                  <!--1378字-->
                </span>
        </div>
      </div>
    </div>
    <div class="art_content" v-html="articleData.content">
    </div>
    <br>
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
</template>
<script>
  export default {
    name: "homeInfo",
    data() {
      return {
        articleData: {},
        commentsData: [],
        inputComment: '',
        author: {} // 作者信息
      }
    },
    methods: {
      // 获取作者信息
      getUser() {
        var _this = this;
        this.$ajax.get('apis/article/getUser', {
          params: {
            articleId: this.$route.params.id
          }
        }).then(function (res) {
          console.log('获得作者信息');
          console.log(res);
          _this.author = res.data;
        });
      },
      // 发布评论
      postComment() {
        var id = sessionStorage.getItem('user');
        if (id !== null) {
          let comment = {
            content: this.inputComment,
            articleId: this.$route.params.id,
            userId: id
          };
          this.$ajax.post('apis/comment/add', comment);
          // 发布成功后重新获取评论集合
          this.getComments();
          this.commentsData.reverse();
        } else {
          alert('请先登录');
        }
      },
      // 获取文章详情
      getArticleDetail() {
        console.log('文章详情');
        console.log(this.$route.params.id);
        var _this = this;
        this.$ajax.get('apis/article/get', {params: {articleId: this.$route.params.id}})
          .then(function (res) {
            console.log(res);
            _this.articleData = res.data;
          })
      },
      // 获取文章评论
      getComments() {
        var _this = this;
        this.$ajax.get('apis/comment/getById', {params: {articleId: this.$route.params.id}})
          .then(function (res) {
            _this.commentsData = res.data;
          })
      }
    },
    mounted() {
      this.getArticleDetail();
      this.getComments();
      this.getUser();
    }
  };
</script>
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

  img {
    width: 100%;
  }

  .name {
    margin: 0 5px;
    font-size: 16px;
    font-weight: 700;
    color: #484848;
    display: inline-block;
    vertical-align: middle;
  }

  .article {
    background-color: #ffffff;
    .base_info {
      .title {
        margin-bottom: 15px;
        font-size: 30px;
        font-weight: 700;
        font-family: -apple-system, SF UI Display, Arial, PingFang SC,
        Hiragino Sans GB, Microsoft YaHei, WenQuanYi Micro Hei, sans-serif;
        color: #2f2f2f;
        word-break: break-word !important;
        line-height: 1.4;
        /*text-align: center;*/
      }
      .article-info {
        margin-left: 20px;
        margin-bottom: 20px;
        font-size: 13px;
        color: #b1b1b1;
        .info {
          vertical-align: middle;
          display: inline-block;
          line-height: 1;
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
        }
      }
    }
    .art_content {
      padding-left: 20px;
      padding-right: 20px;
      p {
        margin: 0 12px 20px !important;
        color: #2f2f2f;
        font-size: 16px;
        font-weight: 400;
        line-height: 1.7;
      }
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
          font-size: 16px;
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


