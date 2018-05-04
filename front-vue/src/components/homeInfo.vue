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
            <img src="../assets/img/em.jpg">
          </div>
          <div class="name">山青青</div>
        </div>
        <div class="meta">
                <span>
                2018.03.31 18:59 ·
                1378字
                </span>
        </div>
      </div>
    </div>
    <div class="art_content" v-html="articleData.content">
    </div>
    <br>
    <div class="comment">
      <h3>发表评论</h3>
      <textarea placeholder="请输入要评论的内容（最多评论120字）" maxlength="120"></textarea>
      <mt-button type="primary" size="large" @click="postComment">发表评论</mt-button>
      <div class="comm_list">
        <div class="comment">
          <div class="comment-top">
            <div class="avatar">
              <img
                src="//upload.jianshu.io/users/upload_avatars/11185255/20396f34-ff9f-48f3-a6e8-371685f14561.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/102/h/102">
            </div>
            <div class="name">萤火虫</div>
          </div>
        </div>
        <p>一言以蔽之：坚持沉稳地做有价值的事情，慢慢等待时间的回报。</p>
        <span class="meta">18楼 · 03.31 20:11</span>
      </div>

    </div>
  </div>
</template>
<script>
  export default {
    name: "homeInfo",
    data() {
      return {
        articleData: {}
      }
    },
    methods: {
      getArticleDetail() {
        console.log('文章详情');
        console.log(this.$route.params.id);
        var _this = this;
        this.$ajax.get('apis/article/get', {params: {articleId: this.$route.params.id}})
          .then(function (res) {
            console.log(res);
            _this.articleData = res.data;
          })
      }
    },
    mounted() {
      this.getArticleDetail()
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
      p {
        margin: 0 12px 20px;
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


