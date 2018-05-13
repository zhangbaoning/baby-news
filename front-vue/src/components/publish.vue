<template>
  <div class="publish">
    <mt-header title="发 表 社 区">
      <router-link to="/" slot="left">
        <mt-button icon="back">back</mt-button>
      </router-link>
      <mt-button icon="more" slot="right"></mt-button>
    </mt-header>
    <textarea v-model="content" id="" placeholder="说说你的心得吧~"></textarea>
    <button @click="postMessage" class="mint-button mint-button--primary mint-button--large"><!----> <label
      class="mint-button-text">发布</label></button>
  </div>

</template>
<script>
  export default {
    name: "publish",
    data() {
      return {
        content: ""
      }
    },
    methods: {
      postMessage() {
        var id = sessionStorage.getItem('user');
        if (id !== null) {
          let message = {
            content: this.content,
            userId: id
          };
          this.$ajax.post('apis/message/postMessage', message);
          this.$router.push('/community');
          this.content = '';
        } else {
          console.log('执行');
          alert('请先登录');
        }
      }
    }
  };
</script>
<style lang="scss" scoped>
  .publish {
    background-color: #fff;
    textarea {
      width: 100%;
      height: 300px;
      margin-top: 8px;
      padding-right: 20px;
      box-sizing: border-box;
      font-size: 15px;
    }
    button {
      width: 98%;
      margin: 0 auto;
    }
  }
</style>


