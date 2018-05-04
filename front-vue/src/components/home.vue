<template>
  <div class="information">
    <!-- 头部部分 -->
    <mt-header fixed title="育 婴 咨 询"></mt-header>
    <!-- 导航栏 -->
    <div class="banner">
      <div class="scroll">
        <a class="control-item active">
          最新
        </a>
        <a class="control-item">
          最热
        </a>
        <a class="control-item">
          早教
        </a>
        <a class="control-item">
          婴儿
        </a>
        <a class="control-item">
          儿童
        </a>
      </div>

    </div>
    <div class="main">
      <router-link class="infor_list" v-for="article in articlesData" :to="'/home/homeInfo/' + article.id" tag="div">
        <div class="img_left">
          <img :src="article.img" alt="暂无图片">
        </div>
        <div class="content_right">
          <h4>{{article.title}}</h4>
          <p>{{article.descr}}</p>
        </div>
      </router-link>
    </div>

  </div>

</template>
<script>

  export default {
    name: 'home',
    data() {
      return {
        articlesData: []
      };
    },
    methods: {
      // 获取所有的文章列表
      getAllArticle() {
        var _this = this;
        this.$ajax.get('apis/article/getAll').then(function (res) {
          _this.articlesData = res.data;
        });
      }
    },
    created() {
    },
    mounted() {
      this.getAllArticle();
    }
  };
</script>
<style lang="scss" scoped>
  .active {
    color: #26a2ff !important;
    border-bottom: 2px solid #26a2ff;
  }

  .information {
    .banner {
      margin-top: 40px;
      background-color: #fff !important;
      height: 40px;
      line-height: 40px;
      .scroll {
        display: flex;
        .control-item {
          text-align: center;
          flex: 1;
          color: #333333;
          font-size: 15px;
        }

      }
    }
    .infor_list {
      display: flex;
      background-color: #fff;
      margin-top: 8px;
      .img_left {
        flex: 1;
        padding: 10px;
        img {
          border-radius: 4%;
          width: 100%;
        }
      }
      .content_right {
        flex: 2;
        h4 {
          margin: 16px 0px 6px 0;
          font-weight: normal;
          font-size: 14px;
          color: #8f8f94;
          line-height: 24px;
          letter-spacing: 1px;
        }
        p {
          font-size: 12px;
          letter-spacing: 2px;
        }
      }
    }
  }
</style>
